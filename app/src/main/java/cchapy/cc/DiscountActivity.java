package cchapy.cc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cchapy.cc.dummy.DummyContent;

public class DiscountActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.discount_inventory_contents, new VoucherListingFragment())
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
