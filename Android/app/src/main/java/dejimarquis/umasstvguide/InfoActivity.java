package dejimarquis.umasstvguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView information = (TextView) findViewById(R.id.inform);
        information.setText("Hey you! Welcome to ZooTV. ZooTV is a TV guide for UMass Amherst. Use the search bar to search for channels. " +
                "\n\nMade with ❤️ by Deji, Timi, and Peter. The iOS app was developed and is maintained by Peter. " +
                "The Android app was developed and is maintained by Deji. The idea was Timi's. Slide into our DMs if you have any questions, comments, or concerns. " +
                "\n\nEmail\nDeji: amarquis@umass.edu\nTimi: oiwayemi@umass.edu\nPeter: ptao@umass.edu\n\nIG\nPeter: https://www.instagram.com/poeticpete/");
        Linkify.addLinks(information, Linkify.ALL);

    }
}
