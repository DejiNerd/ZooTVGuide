package dejimarquis.umasstvguide;

//Created by DejiNerd

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;


public class Main2Activity extends AppCompatActivity {

    private final String URL = "http://tvlistings.zap2it.com/tvlistings/ZCGrid.do?method=decideFwdForLineup&zipcode=01003&setMyPreference=false&lineupId=MA69873:-";
    private ListView listViewTwo;
    private String[] string;
    private Context context;
    private int channelIndex;
    private String movieTitles;
    private ArrayAdapter adapter;
    private int[] mapping;
    private SwipeRefreshLayout swipeRefresh;
    private ListingDownload listingDownload;
    private AdView mAdView;
    private java.sql.Time initTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        channelIndex = getIntent().getExtras().getInt("channelIndex");
        setTitle(getIntent().getExtras().getString("channelName"));

        //initialize ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7394787713757498~4045127165");
        mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        listViewTwo = (ListView) findViewById(R.id.listViews);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        context = this;
        listingDownload = new ListingDownload();
        listingDownload.execute(URL);

        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i("TAGGED", "onRefresh called from SwipeRefreshLayout");
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        listingDownload = new ListingDownload();
                        listingDownload.execute(URL);
                    }
                }
        );
    }

    public class ListingDownload extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            movieTitles = "";
            if (mapping == null) {
                ChannelData temp = new ChannelData();
                mapping = temp.getChannelMapping();
            }
            if (mapping[channelIndex] == -1) return "N/A";
            try {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm a"); //if 24 hour format

                final Document document = Jsoup.connect(strings[0]).get();
                if (document != null) {
                    Element row = document.select("div#zc-grid tr").get(mapping[channelIndex]);
                    Elements initialTime = document.select("div.zc-tn");
                    try {
                        java.util.Date d1 = format.parse(initialTime.select("div.zc-tn-c").first().select("div.zc-tn-t").first().text());
                        initTime = new java.sql.Time(d1.getTime());
                    } catch (Exception e) {
                        Log.e("Exception is ", e.toString());
                    }
                    Elements timeVariable = row.select(".zc-pg");
                    double[] converter = new double[row.select(".zc-pg").size() - 1];
                    for (int i = 0; i < row.select(".zc-pg").size(); i++) {
                        if (i + 1 != row.select(".zc-pg").size()) {
                            converter[i] = Double.parseDouble(timeVariable.get(i).attr("style").substring(6, 10));
                        }
                        if (row.select(".zc-pg").get(i) != null) {
                            if (i == 0) {
                                movieTitles += "Now               " + row.select(".zc-pg").get(i).select(".zc-pg-t").text() + "\n" + "                        " + row.select(".zc-pg").get(i).select(".zc-pg-y").text()
                                        + row.select(".zc-pg").get(i).select(".zc-pg-e").text() + "*";
                            } else {
                                long addedTime = (long) (converter[i - 1] * (60 / 30.7) * 60000);
                                initTime.setTime(initTime.getTime() + addedTime);
                                String time = format.format(initTime);
                                movieTitles += time + "        " + row.select(".zc-pg").get(i).select(".zc-pg-t").text() + "\n" + "                         " + row.select(".zc-pg").get(i).select(".zc-pg-y").text()
                                        + row.select(".zc-pg").get(i).select(".zc-pg-e").text() + "*";
                            }
                        }
                    }
                } else movieTitles = "Not available";
            } catch (IOException e) {
                movieTitles = "Not available";
                e.printStackTrace();
            }
            return movieTitles;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ((s.compareTo("Not available") == 0) || (s == null)) {
                Toast.makeText(context, "Connection Lost. Swipe to refresh", Toast.LENGTH_LONG).show();
                swipeRefresh.setRefreshing(false);
            } else {
                string = s.split("\\*");
                adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, string);
                listViewTwo.setAdapter(adapter);
                swipeRefresh.setRefreshing(false);
                //an hr is 300
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * Listen for option item selections so that we receive a notification
     * when the user requests a refresh by selecting the refresh action bar item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:

                // Signal SwipeRefreshLayout to start the progress indicator
                swipeRefresh.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                listingDownload = new ListingDownload();
                listingDownload.execute(URL);

                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }
}
