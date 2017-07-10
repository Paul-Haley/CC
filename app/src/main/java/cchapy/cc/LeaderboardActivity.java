package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import cchapy.cc.dummy.UserContent;

public class LeaderboardActivity extends AppCompatActivity
    implements UserFragment.OnListFragmentInteractionListener {

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
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.localLeaderboardTab, new UserFragment())
                    .commit();
        }
    }

    public static void viewPopUpProfile(UserContent.User user) {
        //Create view QR intent
        //TODO: Build user into intent to display appropiate profile
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(UserContent.User user) {
        viewPopUpProfile(user);
    }
}
