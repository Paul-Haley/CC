package cchapy.cc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class leaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //set up tabs
        TabHost tabs = (TabHost)findViewById(R.id.leaderboardTabHost);
        tabs.setup();

        //friend tab
        TabHost.TabSpec spec = tabs.newTabSpec("friends");
        spec.setContent(R.id.friendsLeaderboardTab);
        spec.setIndicator("Friends");
        tabs.addTab(spec);

        //leaderboard tab
        spec = tabs.newTabSpec("local");
        spec.setContent(R.id.localLeaderboardTab);
        spec.setIndicator("Local");
        tabs.addTab(spec);

        //city tab
        spec = tabs.newTabSpec("cities");
        spec.setContent(R.id.citiesLeaderboardTab);
        spec.setIndicator("Cities");
        tabs.addTab(spec);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.friendsLeaderboardTab, new UserFragment())
                    .commit();
        }
    }
}
