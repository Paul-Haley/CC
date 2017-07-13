package cchapy.cc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DiscountActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putFloat("userID", UserInfoHelper.getLoggedInId(getApplicationContext()));
            if (UserInfoHelper.getLoggedInId(getApplicationContext()) != -1) {
                VoucherListingFragment voucherListing = new VoucherListingFragment();
                voucherListing.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.discount_inventory_contents, voucherListing)
                        .commit();
            }
        }

        updateLeafCount();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLeafCount();
    }

    @Override
    public void onListFragmentInteraction(Voucher item) {

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

        TextView discountLeafCount = (TextView)findViewById(R.id.discount_leaves);

        discountLeafCount.setText("Leaves: " + leafCount);
    }
}
