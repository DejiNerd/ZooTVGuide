package dejimarquis.zootv;

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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

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
import java.util.ArrayList;
import java.util.HashMap;


public class Main2Activity extends AppCompatActivity {

    private final String URL = "https://damp-depths-69812.herokuapp.com/schedule";
    private ExpandableListView expandableListView;
    private Context context;
    private int channelIndex;
    private HashMap<Integer, ArrayList<String>> listings;
    private ArrayList<String> listingsTitle;
    private String channelName;
    private String jsonData = "";
    private SwipeRefreshLayout swipeRefresh;
    private ListingDownload listingDownload;
    private AdView mAdView;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private HashMap<Integer, String> showTimes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        channelIndex = getIntent().getExtras().getInt("channelIndex");
        channelName = getIntent().getExtras().getString("channelName");
        setTitle(channelName);

        //initialize ads
        MobileAds.initialize(this, "ca-app-pub-7394787713757498~4045127165");
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        expandableListView = findViewById(R.id.expandableListView);
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

    public class ListingDownload extends AsyncTask<String, Integer, HashMap<Integer, ArrayList<String>>> {
        @Override
        protected HashMap<Integer, ArrayList<String>> doInBackground(String... strings) {
            JSONObject channelJson;
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

            while (line != null) {
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
                JSONArray showings = channelsArray.getJSONObject(channelIndex).getJSONArray("showings");
                listings = new HashMap<>();
                listingsTitle = new ArrayList<>();
                for (int i = 0; i < showings.length(); i++) {
                    ArrayList<String> listingsDescription = new ArrayList<>();
                    if (showings.getJSONObject(i).getString("year") != null && showings.getJSONObject(i).getString("year") != ""
                            && showings.getJSONObject(i).getString("year").length() != 0) {
                        listingsDescription.add("Year: " + showings.getJSONObject(i).getString("year"));
                    }
                    if (showings.getJSONObject(i).getString("subtitle") != null && showings.getJSONObject(i).getString("subtitle") != ""
                            && showings.getJSONObject(i).getString("subtitle").length() != 0) {
                        listingsDescription.add("Subtitle: " + showings.getJSONObject(i).getString("subtitle"));
                    }
                    if (showings.getJSONObject(i).getString("description") != null && showings.getJSONObject(i).getString("description") != ""
                            && showings.getJSONObject(i).getString("description").length() != 0) {
                        listingsDescription.add("Description: " + showings.getJSONObject(i).getString("description"));
                    }
                    showTimes.put(i, showings.getJSONObject(i).get("startTime").toString());
                    listingsTitle.add(showings.getJSONObject(i).getString("title"));
                    listings.put(i, listingsDescription);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listings;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(HashMap<Integer, ArrayList<String>> s) {
            if (s == null) {
                Toast.makeText(context, "Connection Lost. Swipe to refresh", Toast.LENGTH_LONG).show();
                swipeRefresh.setRefreshing(false);
            } else {
                CustomExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(context, listingsTitle, listings, showTimes);
                expandableListView.setAdapter(expandableListAdapter);
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
