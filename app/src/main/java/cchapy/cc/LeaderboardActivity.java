package cchapy.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class LeaderboardActivity extends AppCompatActivity
    implements UserFragment.OnListFragmentInteractionListener, LocalUserFragment.OnListFragmentInteractionListener,
    CityFragment.OnListFragmentInteractionListener {

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
        spec.setIndicator(getString(R.string.friends));
        tabs.addTab(spec);

        //leaderboard tab
        spec = tabs.newTabSpec("local");
        spec.setContent(R.id.localLeaderboardTab);
        spec.setIndicator(getString(R.string.local));
        tabs.addTab(spec);

        //city tab
        spec = tabs.newTabSpec("cities");
        spec.setContent(R.id.citiesLeaderboardTab);
        spec.setIndicator(getString(R.string.cities));
        tabs.addTab(spec);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.friendsLeaderboardRecycler, new UserFragment())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.localLeaderboardRecycler, new LocalUserFragment())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.cityLeaderboardRecycler, new CityFragment())
                    .commit();
        }
    }

    public void viewPopUpProfile(User user) {
        //Create view QR intent
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }


    @Override
    public void onListFragmentInteraction(User user) {
        viewPopUpProfile(user);
    }

    @Override
    public void onListFragmentInteraction(City city) {

    }
}

