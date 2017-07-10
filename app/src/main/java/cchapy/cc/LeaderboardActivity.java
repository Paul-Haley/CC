package cchapy.cc;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cchapy.cc.dummy.UserContent;

public class LeaderboardActivity extends AppCompatActivity
    implements UserFragment.OnListFragmentInteractionListener {

    DatabaseHelper mDbHelper = new DatabaseHelper(this);

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

    public void viewPopUpProfile(UserContent.User user) {
        //Create view QR intent
        //TODO: Build user into intent to display appropiate profile
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        startActivity(intent);
    }


    public void addDummyUser(View view){

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

    /*public void addDummyUser(View view){
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME, "from the database");
        values.put(DatabaseContract.UsersTable.COLUMN_NAME_CITY, "Cityyy");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseContract.UsersTable.TABLE_NAME, null, values);

        readDatabase();
    }*/

    @Override
    public void onListFragmentInteraction(UserContent.User user) {
        viewPopUpProfile(user);
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

    }
}

