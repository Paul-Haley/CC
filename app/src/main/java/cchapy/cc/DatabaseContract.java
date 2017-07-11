package cchapy.cc;

import android.provider.BaseColumns;

/**
 * Created by Hayley on 10/07/2017.
 */

public final class DatabaseContract {

    //private constructor to prevent accidental instantiation
    private DatabaseContract() {}

    //Define avatars table
    public static class AvatarTable implements BaseColumns {
        public static final String TABLE_NAME = "avatar";
        public static final String COLUMN_NAME_ID = "AvatarID";
        public static final String COLUMN_NAME_STARNUM = "StarNum";
        public static final String COLUMN_NAME_PRICE = "Price";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_IMAGE_M_MAIN = "Image_M_Main";
        public static final String COLUMN_NAME_IMAGE_M_ALT = "Image_M_Alt";
        public static final String COLUMN_NAME_IMAGE_F_MAIN = "Image_F_Main";
        public static final String COLUMN_NAME_IMAGE_F_ALT = "Image_F_Alt";
    }

    //Define avatars owned table
    public static class AvatarOwnedTable implements BaseColumns {
        public static final String TABLE_NAME = "avatars_owned";
        public static final String COLUMN_NAME_USER = "User";
        public static final String COLUMN_NAME_AVATAR = "Avatar";
    }

    //Define discounts table
    public static class DiscountsTable implements BaseColumns {
        public static final String TABLE_NAME = "discounts";
        public static final String COLUMN_NAME_ID = "DiscountID";
        public static final String COLUMN_NAME_NAME= "Name";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_TIME = "Time";
        public static final String COLUMN_NAME_IMAGE = "Image";
        public static final String COLUMN_NAME_SHOP = "Shop";
        public static final String COLUMN_NAME_PRICE = "Price";
    }

    //Define discounts owned table
    public static class DiscountsOwnedTable implements BaseColumns {
        public static final String TABLE_NAME = "discounts_owned";
        public static final String COLUMN_NAME_USER = "User";
        public static final String COLUMN_NAME_DISCOUNT= "Discount";
    }

    //Define friends table
    public static class FriendsTable implements BaseColumns {
        public static final String TABLE_NAME = "friends";
        public static final String COLUMN_NAME_USER1 = "User1";
        public static final String COLUMN_NAME_USER2= "User2";
    }

    //Define users table
    public static class UsersTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERID = "UserID";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_PASSWORD= "Password";
        public static final String COLUMN_NAME_LEAVES= "Leaves";
        public static final String COLUMN_NAME_GENDER= "Gender";
        public static final String COLUMN_NAME_CARBON= "Carbon";
        public static final String COLUMN_NAME_CITY= "City";
        public static final String COLUMN_NAME_AVATAR_EQUIPPED= "Avatar_Equipped";
        public static final String COLUMN_NAME_LEAVES_TOTAL= "Leaves_Total";
    }

}
