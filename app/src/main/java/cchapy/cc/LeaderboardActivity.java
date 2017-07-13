package cchapy.cc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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

public class LeaderboardActivity extends AppCompatActivity
        implements UserFragment.OnListFragmentInteractionListener, LocalUserFragment.OnListFragmentInteractionListener,
        CityFragment.OnListFragmentInteractionListener {

    DatabaseHelper mDbHelper = new DatabaseHelper(this);

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


    public void addDummyUser(View view) {

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME, "from the database");
        values.put(DatabaseContract.UsersTable.COLUMN_NAME_CITY, "Cityyy");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseContract.UsersTable.TABLE_NAME, null, values);

        Context context = getApplicationContext();
        CharSequence text = "Added to db!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        readDatabase();
    }

    @Override
    public void onListFragmentInteraction(User user) {
        viewPopUpProfile(user);
    }

    @Override
    public void onListFragmentInteraction(City city) {

    }


    public void readDatabase() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT * FROM users";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        int i = 0;
        do {
            String username = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME));
            Context context = getApplicationContext();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, Integer.toString(i) + username, duration);
            toast.show();

            i++;
        } while (mCursor.moveToNext());
        mCursor.close();

        mDbHelper.close();

    }
}

