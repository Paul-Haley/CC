package cchapy.cc;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by Hayley on 10/07/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 19;
    public static final String DATABASE_NAME = "cc.db";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void clear() {
        context.deleteDatabase("cc.db");
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_AVATAR_TABLE);
        db.execSQL(SQL_POPULATE_AVATAR_TABLE);
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_POPULATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_DISCOUNT_TABLE);
        db.execSQL(SQL_POPULATE_DISCOUNT_TABLE);
        db.execSQL(SQL_CREATE_DISCOUNTS_OWNED_TABLE);
        db.execSQL(SQL_POPULATE_DISCOUNTS_OWNED_TABLE);
        db.execSQL(SQL_CREATE_FRIENDS_TABLE);
        db.execSQL(SQL_POPULATE_FRIENDS_TABLE);
        db.execSQL(SQL_CREATE_AVATAR_OWNED_TABLE);
        db.execSQL(SQL_POPULATE_AVATAR_OWNED_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        context.deleteDatabase("cc.db");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_CREATE_AVATAR_TABLE =
            "CREATE TABLE `avatars` (" +
                    "  `AvatarID` int(11) PRIMARY KEY NOT NULL," +
                    "  `StarNum` int(11) NOT NULL," +
                    "  `Price` int(11) NOT NULL," +
                    "  `Name` varchar(100) NOT NULL," +
                    "  `Description` text NOT NULL," +
                    "  `Image_M_Main` int(11) NOT NULL," +
                    "  `Image_M_Alt` int(11) NOT NULL," +
                    "  `Image_F_Main` int(11) NOT NULL," +
                    "  `Image_F_Alt` int(11) NOT NULL" +
                    ");";

    private static final String SQL_POPULATE_AVATAR_TABLE =
            "INSERT INTO `avatars` (`AvatarID`, `StarNum`, `Price`, `Name`, `Description`, `Image_M_Main`, `Image_M_Alt`, `Image_F_Main`, `Image_F_Alt`) VALUES " +
                    "(1, 1, 0, 'Starter', 'Default starting Avatar', 1, 2, 3, 4)," +
                    "(2, 1, 300, 'Cat', 'Purr!', 5, 6, 7, 8)," +
                    "(3, 3, 888, 'Lucky Cat', 'The golden Lucky Cat of everyone''s dreams, all will be jealous of your fortune!', 9, 10, 11, 12);";

    private static final String SQL_CREATE_AVATAR_OWNED_TABLE =
            "CREATE TABLE `avatars_owned` (" +
                    "  `User` int(11) NOT NULL," +
                    "  `Avatar` int(11) NOT NULL," +
                    "PRIMARY KEY (`User`,`Avatar`)" +
                    "FOREIGN KEY (`Avatar`) REFERENCES `avatars` (`AvatarID`)," +
                    "FOREIGN KEY (`User`) REFERENCES `users` (`UserID`)" +
                    ");";

    private static final String SQL_POPULATE_AVATAR_OWNED_TABLE =
            "INSERT INTO `avatars_owned` (`User`, `Avatar`) VALUES" +
                    "(1, 1)," +
                    "(2, 1)," +
                    "(2, 2)," +
                    "(3, 1)," +
                    "(3, 3)," +
                    "(4, 1)," +
                    "(5, 1)," +
                    "(5, 2)," +
                    "(5, 3);";

    private static final String SQL_CREATE_DISCOUNT_TABLE =
            "CREATE TABLE `discounts` (" +
                    "  `DiscountID` int(11) PRIMARY KEY NOT NULL," +
                    "  `Name` varchar(100) NOT NULL," +
                    "  `Description` text NOT NULL," +
                    "  `Time` datetime NOT NULL," +
                    "  `Image` int(11) NOT NULL," +
                    "  `Shop` varchar(100) NOT NULL," +
                    "  `Price` int(11) NOT NULL" +
                    ");";

    private static final String SQL_POPULATE_DISCOUNT_TABLE =
            "INSERT INTO `discounts` (`DiscountID`, `Name`, `Description`, `Time`, `Image`, `Shop`, `Price`) VALUES" +
                    "(1, 'Starbucks 60% OFF (Dalian)', '60% off of ALL Starbucks drinks! This offer is only available to purchase for today (14/07/2017), and can be redeemed until 14/08/17. Only available at Starbucks in Dalian.', '2017-07-15 00:00:00', 1, 'Starbucks', 100)," +
                    "(2, 'HALF PRICE Big Macs!', 'Big Macs at all Liaoning McDonald''s restaurants are half price for one week! This voucher can be purchased until 19/07/17 and can be redeemed until 31/08/17.', '2017-09-01 00:00:00', 2, 'McDonald''s', 200)," +
                    "(3, 'New Red Rooster Store Opening Sale! (Dalian)', 'We are opening our first Chinese store in Dalian, Lianoning, and want you to come try our delicious chicken! For all of August you can redeem this voucher once to receive a free Red Rooster ''Try It'' box. This voucher is only available to be purchased before 16/07/17.', '2017-09-01 00:00:00', 3, 'Red Rooster', 300);";

    private static final String SQL_CREATE_DISCOUNTS_OWNED_TABLE =
            "CREATE TABLE `discounts_owned` (" +
                    "  `User` int(11) NOT NULL," +
                    "  `Discount` int(11) NOT NULL," +
                    "PRIMARY KEY (`User`,`Discount`)" +
                    "FOREIGN KEY (`User`) REFERENCES `users` (`UserID`)," +
                    "FOREIGN KEY (`Discount`) REFERENCES `discounts` (`DiscountID`)" +
                    ");";

    private static final String SQL_POPULATE_DISCOUNTS_OWNED_TABLE =
            "INSERT INTO `discounts_owned` (`User`, `Discount`) VALUES" +
                    "(1, 2)," +
                    "(3, 2)," +
                    "(5, 1)," +
                    "(5, 3);";

    private static final String SQL_CREATE_FRIENDS_TABLE =
            "CREATE TABLE `friends` (" +
                    "  `User1` int(11) NOT NULL," +
                    "  `User2` int(11) NOT NULL," +
                    "PRIMARY KEY (`User1`,`User2`)" +
                    "FOREIGN KEY (`User1`) REFERENCES `users` (`UserID`)," +
                    "FOREIGN KEY (`User2`) REFERENCES `users` (`UserID`)" +
                    ")";

    private static final String SQL_POPULATE_FRIENDS_TABLE =
            "INSERT INTO `friends` (`User1`, `User2`) VALUES" +
                    "(1, 2)," +
                    "(1, 4)," +
                    "(1, 5)," +
                    "(2, 1)," +
                    "(2, 5)," +
                    "(3, 4)," +
                    "(4, 1)," +
                    "(4, 3)," +
                    "(5, 1)," +
                    "(5, 2);";

    private static final String SQL_CREATE_USERS_TABLE =
            "CREATE TABLE `users` (" +
                    "  `UserID` int(11) PRIMARY KEY NOT NULL," +
                    "  `Username` varchar(30) NOT NULL," +
                    "  `Password` varchar(32) NOT NULL," +
                    "  `Leaves` int(11) NOT NULL," +
                    "  `Gender` varchar(1) NOT NULL," +
                    "  `Carbon` int(11) NOT NULL," +
                    "  `City` varchar(30) NOT NULL," +
                    "  `Avatar_Equipped` int(11) NOT NULL," +
                    "  `Leaves_Total` int(11) NOT NULL," +
                    "FOREIGN KEY (`Avatar_Equipped`) REFERENCES `avatars` (`AvatarID`)" +
                    ");";

    private static final String SQL_POPULATE_USERS_TABLE =
            "INSERT INTO `users` (`UserID`, `Username`, `Password`, `Leaves`, `Gender`, `Carbon`, `City`, `Avatar_Equipped`, `Leaves_Total`) VALUES " +
                    "(1, 'Kelly88', 'MoreLeaves', 650, 'F', 1640, 'Dalian', 1, 650)," +
                    "(2, 'Abbey66', 'GreenThumb', 210, 'F', 3205, 'Dalian', 2, 800)," +
                    "(3, 'Bus6', 'takethebus', 1100, 'M', 2100, 'Dalian', 3, 1760)," +
                    "(4, 'FallenLeaves', 'leaves868', 50, 'M', 60, 'Beijing', 1, 150)," +
                    "(5, 'CarbonDioxide', 'CO2', 350, 'F', 603, 'Shanghai', 2, 1530);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DatabaseContract.AvatarOwnedTable.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + DatabaseContract.DiscountsOwnedTable.TABLE_NAME + ";"+
            "DROP TABLE IF EXISTS " + DatabaseContract.FriendsTable.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + DatabaseContract.DiscountsTable.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + DatabaseContract.UsersTable.TABLE_NAME + ";" +
            "DROP TABLE IF EXISTS " + DatabaseContract.AvatarTable.TABLE_NAME + ";";
}