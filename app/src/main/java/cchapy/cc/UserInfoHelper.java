package cchapy.cc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;

/**
 * Created by callu on 12/07/2017.
 */

public class UserInfoHelper {

    public static void addLeaves(Context context, int userID, int leaves) {
        UserFetcher uFetch = new UserFetcher(context);

        //update current count
        int currentLeafCount = uFetch.getCurrentLeavesById(userID);
        int newLeafCount = currentLeafCount + leaves;
        uFetch.setCurrentLeaves(newLeafCount, userID);

        //update historical total
        int totalLeafCount = uFetch.getTotalLeavesById(userID);
        int newTotalLeafCount = totalLeafCount + leaves;
        uFetch.setTotalLeaves(newTotalLeafCount, userID);
    }

    public static void spendLeaves(Context context, int userID, int leaves) {
        UserFetcher uFetch = new UserFetcher(context);

        //update current count
        int currentLeafCount = uFetch.getCurrentLeavesById(userID);
        int newLeafCount = currentLeafCount - leaves;
        uFetch.setCurrentLeaves(newLeafCount, userID);
    }

    public static int getLoggedInId(Context context) {
        SharedPreferences userData = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        int userID = userData.getInt(context.getString(R.string.saved_user_id), -1);

        return userID;
    }

    /**
     * Returns the index of the relevant image to display as the MAIN avatar of the user.
     *
     * @param context context of the activity running this method
     * @param userID userID of the user requiring an avatar
     * @return index for avatars.xml to get the avatar resource image
     */
    public static int getUserAvatarMain(Context context, int userID) {
        UserFetcher uFetch = new UserFetcher(context);
        AvatarFetcher aFetch = new AvatarFetcher(context);

        int avatarID = uFetch.getAvatarByUserId(userID);
        String gender = uFetch.getGenderByUserId(userID);
        int avatarImageID = aFetch.getAvatarMainByAvatarId(avatarID, gender);

        return avatarImageID - 1;
    }

    /**
     * Returns the index of the relevant image to display as the ALT avatar of the user.
     *
     * @param context context of the activity running this method
     * @param userID userID of the user requiring an avatar
     * @return index for avatars.xml to get the avatar resource image
     */
    public static int getUserAvatarAlt(Context context, int userID) {
        UserFetcher uFetch = new UserFetcher(context);
        AvatarFetcher aFetch = new AvatarFetcher(context);

        int avatarID = uFetch.getAvatarByUserId(userID);
        String gender = uFetch.getGenderByUserId(userID);
        int avatarImageID = aFetch.getAvatarAltByAvatarId(avatarID, gender);

        return avatarImageID - 1;
    }
}
