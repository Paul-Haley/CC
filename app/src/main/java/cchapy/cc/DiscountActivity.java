package cchapy.cc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DiscountActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putFloat("userID", UserInfoHelper.getLoggedInId(getApplicationContext()));
            VoucherListingFragment voucherListing = new VoucherListingFragment();
            voucherListing.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.discount_inventory_contents, voucherListing)
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Voucher item) {

    }
}
