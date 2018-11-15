package dejimarquis.zootv;

//Created by DejiNerd


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ChannelAdapter channelAdapter;
    private AdView mAdView;
    private final String URL = "https://damp-depths-69812.herokuapp.com/schedule";
    private ArrayList<String> listingsTitle;
    private String jsonData = "";
    private ListingDownload listingDownload;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private ChannelData temp;


    //TODO create a custom listview for main2activity, fix back arrow button
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listingDownload = new ListingDownload();
        listingDownload.execute(URL);
        //initialize ads
        MobileAds.initialize(this, "ca-app-pub-7394787713757498~4045127165");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public class ListingDownload extends AsyncTask<String, Integer, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(String... strings) {
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
                listingsTitle = new ArrayList<>();
                for (int i = 0; i < 84; i++) {
                    JSONArray showings = channelsArray.getJSONObject(i).getJSONArray("showings");
                    listingsTitle.add(showings.getJSONObject(0).getString("title"));
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            return listingsTitle;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> titles) {

            //initialize listView
            temp = new ChannelData(titles);
            listView = findViewById(R.id.listView);
            channelAdapter = new ChannelAdapter(getApplicationContext(), R.layout.row, temp.getChannels());
            listView.setAdapter(channelAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(view.getContext(), Main2Activity.class);
                    if (channelAdapter.getItem(i) != null && channelAdapter.getItem(i).index != -1) {
                        intent.putExtra("channelIndex", channelAdapter.getItem(i).index);
                        intent.putExtra("channelName", channelAdapter.getItem(i).channelName);
                    } else {
                        intent.putExtra("channelIndex", i);
                        intent.putExtra("channelName", channelAdapter.getItem(i).channelName);
                    }
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("e.g cnn or nba");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                channelAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered info:
            case R.id.information:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}


