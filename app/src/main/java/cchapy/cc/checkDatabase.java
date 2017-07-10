package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class checkDatabase extends AppCompatActivity {

    DatabaseHelper mDbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_database);
        readUserTable();
        readAvatarTable();
        mDbHelper.close();
    }

    public void readUserTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT * FROM users";
        Cursor mCursor = db.rawQuery(q, null);

        String mTextView = "USER TABLE: userID | username | passowrd | leaves | gender | carbon | city | avatarE | leavesTot\n";

        mCursor.moveToFirst();
        do {
            String userID = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERID));
            String username = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_USERNAME));
            String password = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_PASSWORD));
            String leaves = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES));
            String gender = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_GENDER));
            String carbon = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CARBON));
            String city = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_CITY));
            String avatarEquipped = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_AVATAR_EQUIPPED));
            String leavesTotal = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.UsersTable.COLUMN_NAME_LEAVES_TOTAL));

            String userDisplay = userID + "|" + username + "|" + password + "|" + leaves + "|" + gender +
                carbon + "|" + city + "|" + avatarEquipped + "|" + leavesTotal + "\n";

            mTextView += userDisplay;

        } while (mCursor.moveToNext());

        TextView dbDisplay = (TextView)findViewById(R.id.userTableDisplay);
        dbDisplay.setText(mTextView);

        mCursor.close();
        mDbHelper.close();
    }

    public void readAvatarTable() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT * FROM avatars";
        Cursor mCursor = db.rawQuery(q, null);

        String mTextView = "AVATAR TABLE: avatarID | StarNum | Name | ImageMMain | ImageMAlt | ImageFMain | ImageFAlt\n\n";

        mCursor.moveToFirst();
        do {
            String avatarID = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_ID));
            String starNum = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_STARNUM));
            String name = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME));
            String imageMMain = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_MAIN));
            String imageMAlt = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_ALT));
            String imageFMain = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_MAIN));
            String imageFAlt = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_ALT));

            String userDisplay = avatarID + "|" + starNum + "|" + name + "|" + imageMMain + "|" + imageMAlt + "|" +
                    imageFMain + "|" + imageFAlt + "\n";

            mTextView += userDisplay;

        } while (mCursor.moveToNext());

        TextView dbDisplay = (TextView)findViewById(R.id.avatarTableDisplay);
        dbDisplay.setText(mTextView);

        mCursor.close();
        mDbHelper.close();
    }
}
