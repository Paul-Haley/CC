package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cchapy.cc.User;


/**
 * Created by Hayley on 12/07/2017.
 */

public class CityFetcher {

    private Context context;

    public CityFetcher(Context context){
        this.context = context;
    }

    public List<City> fetchAllCities(){

        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        UserFetcher userFetcher = new UserFetcher(context);

        List<City> cityList = new ArrayList<>();

        //Obtain list of city names first
        List<String> cityNameList = new ArrayList<>();

        String q = "SELECT DISTINCT `City` FROM users";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        do {
            String city = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CITY));
            cityNameList.add(city);
        } while (mCursor.moveToNext());

        //Iterate through cities to generate city stats
        for (String cityName:cityNameList){

            //UserCount
            int userCount = 0;
            //Total Leaves
            int leafCount = 0;

            //Fetch users in city
            List<User> cityUsers = userFetcher.fetchUsersByCityName(cityName);

            for (User user:cityUsers){
                userCount++;
                leafCount = leafCount + user.getTotalLeafCount();
            }

            City city = new City(cityName, userCount, leafCount);
            cityList.add(city);
        }

        //Sort cities
        Collections.sort(cityList,new CityComparator());

        mCursor.close();
        mDbHelper.close();
        return cityList;
    }


}


