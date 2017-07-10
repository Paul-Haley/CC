package cchapy.cc;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get application context
        mContext = getApplicationContext();

        //Get self activity
        mActivity = MainActivity.this;
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
        Intent intent = new Intent(this, leaderboardActivity.class);
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
}
