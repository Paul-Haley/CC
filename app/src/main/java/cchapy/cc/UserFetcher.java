package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
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

    public String getGenderByUserId(int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Gender FROM users WHERE UserID = '" + userID + "'";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        String avatar = "";

        try {
            do {
                avatar = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_GENDER));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return "";
        }

        return avatar;
    }

    public int getAvatarByUserId(int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Avatar_Equipped FROM users WHERE UserID = '" + userID + "'";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int avatar = -1;

        try {
            do {
                avatar = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_AVATAR_EQUIPPED));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }

        return avatar;
    }

    public int getUserIdByUsername(String username) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT UserID FROM users WHERE Username = '" + username + "'";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int userID = -1;

        try {
            do {
                userID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERID));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }

        return userID;
    }

    public String getUsernameById(int UserID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Username FROM users WHERE UserID = " + UserID;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        String username = "NULL";

        try {
            do {
                username = mCursor.getString((mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME)));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return "NULL";
        }

        return username;
    }

    public boolean checkUserLogin(String username, String password) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Password FROM users WHERE Username = '" + username + "' COLLATE NOCASE";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        String returnedPassword = "";

        try {
            do {
                returnedPassword = mCursor.getString((mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_PASSWORD)));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return (returnedPassword.equals(password));
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

    public User fetchUserbyId(int id){
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT * FROM users WHERE " + DatabaseContract.UsersTable.COLUMN_NAME_USERID
                + " = " + String.valueOf(id);

        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        int userID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERID));
        String username = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME));
        int leaves = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES));
        String gender = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_GENDER));
        int carbon = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CARBON));
        String city = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CITY));
        int avatarEquipped = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_AVATAR_EQUIPPED));
        int leavesTotal = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES_TOTAL));

        User user = new User(userID, username, leaves, gender, carbon, city, avatarEquipped, leavesTotal);

        mCursor.close();
        mDbHelper.close();
        return user;
    }

    public int getCurrentLeavesById(int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Leaves FROM users WHERE UserID = " + userID;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int leaves = 0;

        try {
            do {
                leaves = mCursor.getInt((mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES)));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }

        return leaves;
    }

    public void setCurrentLeaves(int currentLeaves, int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "UPDATE users SET Leaves = " + currentLeaves + " WHERE UserID = " + userID;

        Cursor mCursor = db.rawQuery(q, null);

        mCursor.close();
        mDbHelper.close();
    }

    public int getTotalLeavesById(int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Leaves_Total FROM users WHERE UserID = " + userID;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int leaves = 0;

        try {
            do {
                leaves = mCursor.getInt((mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES_TOTAL)));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }

        return leaves;
    }

    public void setTotalLeaves(int totalLeaves, int userID) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "UPDATE users SET Leaves_Total = " + totalLeaves + " WHERE UserID = " + userID;

        Cursor mCursor = db.rawQuery(q, null);

        mCursor.close();
        mDbHelper.close();
    }
}


