package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulhaley on 11/7/17.
 */
public class AvatarFetcher {

    private Context context;


    public AvatarFetcher(Context context){
        this.context = context;
    }

    public List<Avatar> fetchAllAvatars() {
        List<Avatar> avatars = new ArrayList<Avatar>();

        // Setting up
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM avatars", null);
        mCursor.moveToFirst();

        do {
            avatars.add(new Avatar(mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_ID)),
                    mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME)),
                    1,
                    1,
                    false,
                    ""));
        } while(mCursor.moveToNext());

        // Cleaning up
        mCursor.close();
        mDbHelper.close();
        return avatars;
    }
}
