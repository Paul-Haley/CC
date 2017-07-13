package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import cchapy.cc.dummy.DummyContent;

public class ShopActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener,
        AvatarListingFragment.OnListFragmentInteractionListener {
    TabHost tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        //set up tabs
        TabHost tabs = (TabHost)findViewById(R.id.tabHost);
        tabs.setup();

        //discounts
        TabHost.TabSpec spec = tabs.newTabSpec("discounts");
        spec.setContent(R.id.DiscountTab);
        spec.setIndicator(getString(R.string.discounts));
        tabs.addTab(spec);

        //avatars
        spec = tabs.newTabSpec("avatars");
        spec.setContent(R.id.AvatarTab);
        spec.setIndicator(getString(R.string.avatars));
        tabs.addTab(spec);

        //get the intent that started this activity
        Intent intent = getIntent();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.voucher_shoplist, new VoucherListingFragment())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.avatar_shoplist, new AvatarListingFragment())
                    .commit();
        }

        updateLeafCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLeafCount();
    }

    public void updateLeafCount() {
        Context context = getApplicationContext();
        int userID = UserInfoHelper.getLoggedInId(context);

        int leafCount;
        if (userID == -1) {
            leafCount = 0;
        } else {
            //get leaf count
            UserFetcher uFetch = new UserFetcher(context);
            leafCount = uFetch.getCurrentLeavesById(userID);
        }

        TextView avatarLeafCount = (TextView)findViewById(R.id.shop_leaves_avatar);
        TextView discountLeafCount = (TextView)findViewById(R.id.shop_leaves_discount);

        avatarLeafCount.setText("Leaves: " + leafCount);
        discountLeafCount.setText("Leaves: " + leafCount);
    }

    @Override
    public void onListFragmentInteraction(Voucher item) {

    }

    @Override
    public void onListFragmentInteraction(Avatar item) {

    }
}
