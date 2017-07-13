package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hayley on 11/07/2017.
 * Edited by Paul on 13/07/2017
 */

public class VoucherFetcher {

    private Context context;


    public VoucherFetcher(Context context) {
        this.context = context;
    }

    /**
     *
     * @param id Voucher id
     * @return single voucher by id or TODO: null if not found
     */
    public Voucher fetchVoucherByVoucherId(int id) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String idString = Integer.toString(id);
        Cursor mCursor = db.rawQuery("SELECT * FROM discounts WHERE DiscountID = " + idString, null);
        mCursor.moveToFirst();

        //TODO: Assumes that there is exactly one result
        Voucher voucher = createVoucherFromDatabase(mCursor);

        //Cleaning up
        mCursor.close();
        mDbHelper.close();

        return voucher;
    }

    public List<Voucher> fetchAllVouchers(String sorting, String sortMethod){
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Voucher> voucherList = new ArrayList<>();

        String q = "SELECT * FROM discounts ORDER BY " + sortMethod + " " + sorting;
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        do {
            voucherList.add(createVoucherFromDatabase(mCursor));
        } while (mCursor.moveToNext());

        mCursor.close();
        mDbHelper.close();
        return voucherList;
    }

    public List<Voucher> fetchVouchersByUserId(int userId) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Voucher> voucherList = new ArrayList<>();

        String q = "SELECT * FROM discounts WHERE DiscountID IN (SELECT discount FROM discounts_owned" +
                " WHERE User = " + userId + ")";
        System.out.println(q);
        Cursor mCursor = db.rawQuery(q, null);

        if (mCursor.getCount() != 0) {
            mCursor.moveToFirst();
            do {
                voucherList.add(createVoucherFromDatabase(mCursor));
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        mDbHelper.close();
        return voucherList;
    }

    public List<Voucher> fetchVouchersByUserId(int userId, String sorting, String sortingMethod) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Voucher> voucherList = new ArrayList<>();

        String q = "SELECT * FROM discounts WHERE DiscountID IN (SELECT discount FROM discounts_owned" +
                " WHERE User = " + userId + ") ORDER BY " + sortingMethod + " " + sorting;
        System.out.println(q);
        Cursor mCursor = db.rawQuery(q, null);

        if (mCursor.getCount() != 0) {
            mCursor.moveToFirst();
            do {
                voucherList.add(createVoucherFromDatabase(mCursor));
            } while (mCursor.moveToNext());
        }

        mCursor.close();
        mDbHelper.close();
        return voucherList;
    }

    /**
     * TODO: fault tolerance
     * @param mCursor Cursor being used in database query
     * @return the Voucher described by the database
     */
    private Voucher createVoucherFromDatabase(Cursor mCursor) {
        int voucherID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_ID));
        String voucherName = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_NAME));
        int price = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_PRICE));
        String description = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_DESCRIPTION));
        String time = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_TIME));
        int image = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_IMAGE));
        String shop = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_SHOP));

        return new Voucher(voucherID, voucherName, price, description, time, image, shop);
    }
}


