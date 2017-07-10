package cchapy.cc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

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
        spec.setIndicator("Discounts");
        tabs.addTab(spec);

        //avatars
        spec = tabs.newTabSpec("avatars");
        spec.setContent(R.id.AvatarTab);
        spec.setIndicator("Avatars");
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
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
