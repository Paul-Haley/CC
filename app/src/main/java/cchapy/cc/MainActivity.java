package cchapy.cc;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;
    private Banner banner;
    private List<Integer> urlList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get application context
        mContext = getApplicationContext();

        //Get self activity
        mActivity = MainActivity.this;
        //banner
        initData();
        initView();
        
        unlockOnLogin();
        getUserDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();
        unlockOnLogin();
        getUserDetails();
    }

    /**
     * Check if the user is logged in and disable the interface if not.
     */
    private void unlockOnLogin() {
        boolean enable = UserInfoHelper.getLoggedInId(mContext) != -1;
        float alpha = enable ? 1f : 0.3f;

        List<ImageButton> buttons = new ArrayList<ImageButton>();
        buttons.add((ImageButton) findViewById(R.id.Button_QR));
        buttons.add((ImageButton) findViewById(R.id.Button_Shop));
        buttons.add((ImageButton) findViewById(R.id.Button_Discounts));
        buttons.add((ImageButton) findViewById(R.id.Button_Avatar));
        buttons.add((ImageButton) findViewById(R.id.Button_Profile));
        buttons.add((ImageButton) findViewById(R.id.Button_Friends));
        buttons.add((ImageButton) findViewById(R.id.Button_Leaderboard));

        for (ImageButton button : buttons) {
            button.setEnabled(enable);
            button.setAlpha(alpha);
        }
    }

    private void getUserDetails() {
        //check for stored userID
        int userID = UserInfoHelper.getLoggedInId(mContext);

        if (userID == -1) {
            //no user logged in
            TextView userText = (TextView)findViewById(R.id.Text_Username);
            userText.setText(R.string.Login_Prompt);

            ImageView avatar = (ImageView)findViewById(R.id.Image_Avatar);
            avatar.setVisibility(View.INVISIBLE);
        } else {
            UserFetcher uFetch = new UserFetcher(mContext);
            AvatarFetcher aFetch = new AvatarFetcher(mContext);
            //user logged in
            String uNameString = uFetch.getUsernameById(userID);
            TextView userText = (TextView)findViewById(R.id.Text_Username);
            userText.setText(uNameString);
            ImageView avatar = (ImageView)findViewById(R.id.Image_Avatar);
            avatar.setVisibility(View.VISIBLE);

            int avatarImageID = UserInfoHelper.getUserAvatarMain(mContext, userID);
            Resources res = mContext.getResources();
            TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
            avatar.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));
        }
    }

    public void viewProfile(View view) {
        //Create view profile intent
        Intent intent = new Intent(this, DisplayProfileActivity.class);
        startActivity(intent);
    }

    public void viewShop(View view) {
        //Create view shop intent
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void viewQR(View view) {
        //Create view QR intent
        Intent intent = new Intent(this, QRActivity.class);
        startActivity(intent);
    }

    public void viewFriends(View view) {
        //Create view profile intent
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }

    public void viewLeaderboard(View view) {
        //Create view QR intent
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    public void viewSettings(View view) {
        //Create view Settings intent
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void viewDiscounts(View view) {
        //Create view Settings intent
        Intent intent = new Intent(this, DiscountActivity.class);
        startActivity(intent);
    }

    public void viewAvatar(View view) {
        //Create view Settings intent
        Intent intent = new Intent(this, AvatarActivity.class);
        startActivity(intent);
    }
    //banner
    private void initData() {
        urlList.add(R.drawable.discount_0);
        urlList.add(R.drawable.discount_1);
        urlList.add(R.drawable.discount_2);
        titles.add("discount_0");
        titles.add("discount_1");
        titles.add("discount_2");
    }

    /**
     * Call when code is successfully scanned
     */
    public void viewScanned(View view) {//TODO: delete this when finished
        //Create view for when QR code is scanned intent
        Intent intent = new Intent(this, ScannedActivity.class);
        startActivity(intent);
    }


    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        banner.setImages(urlList)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(6000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setBannerAnimation(Transformer.Default)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}

