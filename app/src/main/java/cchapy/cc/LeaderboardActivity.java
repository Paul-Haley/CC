package cchapy.cc;

import android.content.Intent;
<<<<<<< HEAD
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cchapy.cc.User;
=======
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;
>>>>>>> 13edf7a939798aa0a10c02ff8c4545236bebe7e3

public class LeaderboardActivity extends AppCompatActivity
        implements UserFragment.OnListFragmentInteractionListener, LocalUserFragment.OnListFragmentInteractionListener,
        CityFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //set up tabs
        TabHost tabs = (TabHost) findViewById(R.id.leaderboardTabHost);
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

        if (savedInstanceState == null) {
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

        fillTopFriendsUser(this.findViewById(android.R.id.content));

        LinearLayout topUserLayout = (LinearLayout) findViewById(R.id.topLeaderboardRow);
        topUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewTopPopUpProfile();
            }
        });
    }

    public void viewPopUpProfile(User user) {
        //Create view QR intent
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }

    public void viewTopPopUpProfile() {

        int loggedInId = UserInfoHelper.getLoggedInId(this);
        if (loggedInId > 0) {
            UserFetcher fetcher = new UserFetcher(this);
            List<User> friends = fetcher.fetchUserFriendsById(loggedInId);

            User topUser = friends.get(0);
            viewPopUpProfile(topUser);
        }
    }

    private void fillTopFriendsUser(View view) {

        int loggedInId = UserInfoHelper.getLoggedInId(this);
        if (loggedInId > 0) {
            UserFetcher fetcher = new UserFetcher(this);
            List<User> friends = fetcher.fetchUserFriendsById(loggedInId);

            User topUser = friends.get(0);

            TextView mTopUsername = view.findViewById(R.id.TopUsername);
            TextView mTopUserCity = view.findViewById(R.id.TopUserCity);
            TextView mTopUserLeaves = view.findViewById(R.id.TopUserLeaves);
            ImageView mTopUserAvatar = view.findViewById(R.id.TopUserAvatar);

            mTopUsername.setText(topUser.getUserName());
            mTopUserCity.setText(topUser.getCity());
            mTopUserLeaves.setText(String.valueOf(topUser.getTotalLeafCount()));

            int avatarImageID = UserInfoHelper.getUserAvatarAlt(this, topUser.getId());

            Resources res = this.getResources();
            TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
            mTopUserAvatar.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));
        }
    }

    @Override
    public void onListFragmentInteraction(User user) {
        viewPopUpProfile(user);
    }

    @Override
    public void onListFragmentInteraction(City city) {

    }
}

