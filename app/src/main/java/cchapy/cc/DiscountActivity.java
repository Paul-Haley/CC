package cchapy.cc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DiscountActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener,
        AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        Spinner spin = (Spinner) findViewById(R.id.discount_sort_select);
        spin.setOnItemSelectedListener(this);

        if (savedInstanceState == null) {
            displayDiscounts();
        }

        updateLeafCount();
    }

    public void triggerUpdate(View view) {
        displayDiscounts();
    }

    public void displayDiscounts() {
        ToggleButton toggle = (ToggleButton)findViewById(R.id.discount_toggle_sort);
        Spinner sortSelect = (Spinner)findViewById(R.id.discount_sort_select);

        Bundle arguments = new Bundle();
        if (!toggle.isChecked()) {
            //asc
            arguments.putString("sorting", "ASC");
        } else {
            //desc
            arguments.putString("sorting", "DESC");
        }

        String sortSelection = sortSelect.getSelectedItem().toString();
        switch (sortSelection) {
            case "NEW":
                arguments.putString("sortMethod", "DiscountID");
                break;
            case "A-Z":
                arguments.putString("sortMethod", "Name");
                break;
            case "TIME":
                arguments.putString("sortMethod", "Time");
                break;
            case "PRICE":
                arguments.putString("sortMethod", "Price");
                break;
            case "新品":
                arguments.putString("sortMethod", "DiscountID");
                break;
            case "使用期限":
                arguments.putString("sortMethod", "Time");
                break;
            case "价格":
                arguments.putString("sortMethod", "Price");
                break;
        }

        arguments.putFloat("userID", UserInfoHelper.getLoggedInId(getApplicationContext()));
        if (UserInfoHelper.getLoggedInId(getApplicationContext()) != -1) {
            VoucherListingFragment voucherListing = new VoucherListingFragment();
            voucherListing.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.discount_inventory_contents, voucherListing)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLeafCount();
        displayDiscounts();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        displayDiscounts();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
