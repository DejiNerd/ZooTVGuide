package dejimarquis.umasstvguide;

//Created by DejiNerd


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ChannelAdapter channelAdapter;
    private AdView mAdView;
    private ArrayList<Channel> channels;

    //TODO create a custom listview for main2activity, fix back arrow button
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize ads
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7394787713757498~4045127165");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //initialize listView
        listView = (ListView) findViewById(R.id.listView);
        ChannelData temp = new ChannelData();
        channels = temp.getChannels();
        channelAdapter = new ChannelAdapter(getApplicationContext(), R.layout.row, channels);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("e.g cnn");
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

    //TODO: purpose of this function?
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        final int abTitleId = getResources().getIdentifier("action_bar_title", "id", "android");
//        findViewById(abTitleId).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listView.smoothScrollToPosition(0);
//            }
//        });
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
//        super.onPause();
//    }

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


