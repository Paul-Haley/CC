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

    /**
     *
     * @param avatarID
     * @param gender M for male or F for Female
     * @return Resource for full size avatar
     */
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

    /**
     *
     * @param avatarID
     * @param gender M for male or F for Female
     * @return Resource for full size avatar
     */
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

    /**
     *
     * @return The list of all avatars
     * @param sorting
     * @param sortMethod
     */
    public List<Avatar> fetchAllAvatars(String sorting, String sortMethod) {
        List<Avatar> avatars = new ArrayList<Avatar>();

        // Setting up
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Integer> owned = getOwnedAvatars(db); // Reading owned avatars for the current user

        Cursor mCursor = db.rawQuery("SELECT * FROM avatars ORDER BY " + sortMethod + " " + sorting,
                null);
        mCursor.moveToFirst();
        do {

            // Getting the avatars
            int AvatarID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_ID));
            int StarNum = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_STARNUM));
            int Price = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_PRICE));
            String Name = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME));
            String Desc = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_DESCRIPTION));
            int IMM = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_MAIN));
            int IMA = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_ALT));
            int IFM = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_MAIN));
            int IFA = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_ALT));

            Avatar avatar = new Avatar(AvatarID, StarNum, Price, Name, Desc, IMM, IMA, IFM, IFA);
            avatars.add(avatar);

        } while(mCursor.moveToNext());

        // Cleaning up
        mCursor.close();
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

    /**
     *
     * @param id
     * @return The avatar object with matching ID
     */
    public Avatar fetchAvatarById(int id) {
        // Setting up
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Integer> owned = getOwnedAvatars(db); // Reading owned avatars for the current user

        Cursor mCursor = db.rawQuery("SELECT * FROM avatars WHERE " +
                DatabaseContract.AvatarTable.COLUMN_NAME_ID + " = " + String.valueOf(id) , null);

        mCursor.moveToFirst();

        int AvatarID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_ID));
        int StarNum = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_STARNUM));
        int Price = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_PRICE));
        String Name = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_NAME));
        String Desc = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_DESCRIPTION));
        int IMM = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_MAIN));
        int IMA = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_M_ALT));
        int IFM = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_MAIN));
        int IFA = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.AvatarTable.COLUMN_NAME_IMAGE_F_ALT));

        Avatar avatar = new Avatar(AvatarID, StarNum, Price, Name, Desc, IMM, IMA, IFM, IFA);

        // Cleaning up
        mCursor.close();
        mDbHelper.close();
        return avatar;
    }
}
