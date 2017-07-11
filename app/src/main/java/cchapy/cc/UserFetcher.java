package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hayley on 11/07/2017.
 */

public class UserFetcher {

    private Context context;


    public UserFetcher(Context context){
        this.context = context;
    }

    public List<User> fetchAllUsers(){
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<User> userList = new ArrayList<>();

        String q = "SELECT * FROM users";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        do {
            int userID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERID));
            String username = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME));
            int leaves = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES));
            String gender = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_GENDER));
            int carbon = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CARBON));
            String city = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CITY));
            int avatarEquipped = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_AVATAR_EQUIPPED));
            int leavesTotal = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES_TOTAL));

            User user = new User(userID, username, leaves, gender, carbon, city, avatarEquipped, leavesTotal);
            userList.add(user);

        } while (mCursor.moveToNext());

        mCursor.close();
        mDbHelper.close();
        return userList;
    }

}


