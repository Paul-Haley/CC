package cchapy.cc;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        banner.setImages(urlList).setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setBannerStyle(BannerConfig.NOT_INDICATOR)
                .setDelayTime(6000)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setBannerAnimation(Transformer.Tablet)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
}

