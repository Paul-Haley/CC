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

    public int getAvatarAltByAvatarId(int avatarID, String gender) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Image_M_Alt, Image_F_Alt FROM avatars WHERE AvatarID = " + avatarID;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int mAvatar = -1;
        int fAvatar = -1;

        try {
            do {
                mAvatar = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_ALT));
                fAvatar = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_ALT));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }

        if (gender.equals("M")) {
            return mAvatar;
        } else {
            return fAvatar;
        }
    }

    public int getAvatarMainByAvatarId(int avatarID, String gender) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String q = "SELECT Image_M_Main, Image_F_Main FROM avatars WHERE AvatarID = " + avatarID;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();

        int mAvatar = -1;
        int fAvatar = -1;

        try {
            do {
                mAvatar = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_MAIN));
                fAvatar = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_MAIN));
            } while (mCursor.moveToNext());
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }

        if (gender.equals("M")) {
            return mAvatar;
        } else {
            return fAvatar;
        }
    }

    public List<Avatar> fetchAllAvatars() {
        List<Avatar> avatars = new ArrayList<Avatar>();

        // Setting up
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Integer> owned = getOwnedAvatars(db); // Reading owned avatars for the current user

        Cursor avatarsCursor = db.rawQuery("SELECT * FROM avatars", null);
        avatarsCursor.moveToFirst();
        do {
            int id = avatarsCursor.getInt(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_ID));

            // Getting the avatars
            avatars.add(new Avatar(id,
                    avatarsCursor.getString(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME)),
                    avatarsCursor.getInt(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_STARNUM)),
                    avatarsCursor.getInt(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_PRICE)),
                    owned.contains(id),
                    avatarsCursor.getString(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_DESCRIPTION))));
        } while(avatarsCursor.moveToNext());

        // Cleaning up
        avatarsCursor.close();
        mDbHelper.close();
        return avatars;
    }

    /**
     * @param db Database
     * @return The id of all user owned avatars
     */
    private List<Integer> getOwnedAvatars(SQLiteDatabase db) {
        Cursor ownedCursor = db.rawQuery("SELECT * FROM avatars_owned WHERE user = ?",
                new String[] {"1"}); //TODO: get userID
        List<Integer> userOwned = new ArrayList<Integer>();

        ownedCursor.moveToFirst();
        do {
            userOwned.add(ownedCursor.getInt(ownedCursor.getColumnIndex(DatabaseContract.AvatarOwnedTable.COLUMN_NAME_AVATAR)));
        } while (ownedCursor.moveToNext());

        ownedCursor.close();
        return userOwned;
    }

    public Avatar fetchAvatarById(int id) {
        // Setting up
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Integer> owned = getOwnedAvatars(db); // Reading owned avatars for the current user

        Cursor avatarsCursor = db.rawQuery("SELECT * FROM avatars WHERE " +
                DatabaseContract.AvatarTable.COLUMN_NAME_ID + " = " + String.valueOf(id) , null);

        avatarsCursor.moveToFirst();

        Avatar avatar = new Avatar(id,
            avatarsCursor.getString(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME)),
            avatarsCursor.getInt(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_STARNUM)),
            avatarsCursor.getInt(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_PRICE)),
            owned.contains(id),
            avatarsCursor.getString(avatarsCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_DESCRIPTION)));

        // Cleaning up
        avatarsCursor.close();
        mDbHelper.close();
        return avatar;
    }
}
