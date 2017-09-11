import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ZooTvSvc {
    private final String URL = "http://tvlistings.zap2it.com/tvlistings/ZCGrid.do?method=decideFwdForLineup&zipcode=01003&setMyPreference=false&lineupId=MA69873:-";
    private String movieTitles, temp;
    private java.sql.Time initTime;
    private int[] mapping;
    private HashMap<Double, ChannelListings> hmap;

    public String json () throws IOException {
        hmap = new HashMap<>();
        if (mapping == null) {
            ChannelData temp = new ChannelData();
            mapping = temp.getChannelMapping();
        }
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format
        final Document document = Jsoup.connect(URL).get();
        if (document != null) {
            for (int i = 0; i < 85; i++){
                movieTitles = "";
                temp = "Now*";
                if (mapping[i]== -1){
                    hmap.put(i+2.1, new ChannelListings());
                } else {
                    Element row = document.select("div#zc-grid tr").get(mapping[i]);
                    Elements initialTime = document.select("div.zc-tn");
                    try {
                        java.util.Date d1 = format.parse(initialTime.select("div.zc-tn-c").first().select("div.zc-tn-t").first().text());
                        initTime = new java.sql.Time(d1.getTime());
                    } catch (Exception e) {

                    }
                    Elements timeVariable = row.select(".zc-pg");
                    double[] converter = new double[row.select(".zc-pg").size() - 1];
                    for (int j = 0; j < row.select(".zc-pg").size(); j++) {
                        if (j + 1 != row.select(".zc-pg").size()) {
                            if (timeVariable.get(j).attr("style").length() == 11){
                                converter[j] = Double.parseDouble(timeVariable.get(j).attr("style").substring(6, 9));
                            } else  converter[j] = Double.parseDouble(timeVariable.get(j).attr("style").substring(6, 8));

                        }
                        if (row.select(".zc-pg").get(j) != null) {
                            if (j == 0) {
                                movieTitles += row.select(".zc-pg").get(j).select(".zc-pg-t").text() + ": "
                                        + row.select(".zc-pg").get(j).select(".zc-pg-y").text()
                                        + row.select(".zc-pg").get(j).select(".zc-pg-e").text() + "*";
                            } else {
                                long addedTime = (long) (converter[j - 1] * (60 / 30.7) * 60000);
                                initTime.setTime(initTime.getTime() + addedTime);
                                temp += format.format(initTime) + "*";
                                movieTitles += row.select(".zc-pg").get(j).select(".zc-pg-t").text() + ": "
                                        + row.select(".zc-pg").get(j).select(".zc-pg-y").text()
                                        + row.select(".zc-pg").get(j).select(".zc-pg-e").text() + "*";
                            }
                        }
                    }
                    String[] listing = movieTitles.split("\\*");
                    String[] time = temp.split("\\*");
                    hmap.put(i+2.1, new ChannelListings(listing, time));
                }
            }
        }
        Map<Double, ChannelListings> map = new TreeMap<>(hmap);
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        return gson.toJson(map);
    }
}