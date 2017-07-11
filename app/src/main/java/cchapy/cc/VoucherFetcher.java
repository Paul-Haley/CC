package cchapy.cc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hayley on 11/07/2017.
 */

public class VoucherFetcher {

    private Context context;


    public VoucherFetcher(Context context){
        this.context = context;
    }

    public List<Voucher> fetchAllVouchers(){
        DatabaseHelper mDbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        List<Voucher> voucherList = new ArrayList<>();

        String q = "SELECT * FROM discounts";
        Cursor mCursor = db.rawQuery(q, null);

        mCursor.moveToFirst();
        do {
            int voucherID = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_ID));
            String voucherName = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_NAME));
            int price = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_PRICE));
            String description = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_DESCRIPTION));
            String time = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_TIME));
            int image = mCursor.getInt(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_IMAGE));
            String shop = mCursor.getString(mCursor.getColumnIndex(DatabaseContract.DiscountsTable.COLUMN_NAME_SHOP));


            Voucher voucher = new Voucher(voucherID, voucherName, price, description, time, image, shop);
            voucherList.add(voucher);

        } while (mCursor.moveToNext());

        mCursor.close();
        mDbHelper.close();
        return voucherList;
    }

}


