import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ZooTvSvc {
    private final String URL = "http://tvlistings.zap2it.com/tvlistings/ZCGrid.do?method=decideFwdForLineup&zipcode=01003&setMyPreference=false&lineupId=MA69873:-";
    private String jsonData = "";
    HashMap <String, HashMap<String,String>> listings;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;

    public String json () throws IOException {
        JSONObject channelJson = null;
        URL url = null;
        try {
            url = new URL("https://damp-depths-69812.herokuapp.com/schedule");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";

        while(line != null) {
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonData = jsonData + line;
        }

        JSONArray channelsArray;
        try {
            channelJson = new JSONObject(jsonData);
            channelsArray = channelJson.getJSONArray("channels");
            JSONArray showings = channelsArray.getJSONObject(0).getJSONArray("showings");
            listings = new HashMap<>();
            HashMap<String, String> listingsDescription = new HashMap<>();
            for (int i = 0; i < showings.length(); i++){
                listingsDescription.put("length", showings.getJSONObject(i).getInt("length")+"");
                listingsDescription.put("year", showings.getJSONObject(i).getString("year"));
                listingsDescription.put("subtitle", showings.getJSONObject(i).getString("subtitle"));
                listingsDescription.put("description", showings.getJSONObject(i).getString("description"));

                listings.put(showings.getJSONObject(i).get("title").toString(),listingsDescription);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //TODO: Make time more interpretable and third showing doesn't show length
        return listings.toString();
    }
}

