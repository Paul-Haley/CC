package cchapy.cc;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DiscountPopup extends AppCompatActivity {

    private Voucher voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_discount_popup);

        updatePopup(getIntent().getIntExtra("DISCOUNT", 0));
    }

    /**
     * Fills in all the fields with the selected voucher's details
     * @param id
     */
    private void updatePopup(int id) {
        VoucherFetcher vFetch = new VoucherFetcher(getApplicationContext());
        voucher = vFetch.fetchVoucherByVoucherId(id);

        TextView title = (TextView)findViewById(R.id.shop_pop_disc_title);
        title.setText(voucher.getName());

        TextView price = (TextView)findViewById(R.id.shop_pop_disc_leaf);
        price.setText(Integer.toString(voucher.getPrice()));

        TextView description = (TextView)findViewById(R.id.shop_pop_disc_desc);
        description.setText(voucher.getDescription());

        TextView time = (TextView)findViewById(R.id.shop_pop_disc_time);
        time.setText(getString(R.string.expires) + " " + voucher.getTime());

        TextView location = (TextView)findViewById(R.id.shop_pop_disc_loc);
        location.setText(getString(R.string.location) + " " + voucher.getShop());

        //Changing the image
        ImageView image = (ImageView)findViewById(R.id.shop_pop_disc_image);
        Resources res = this.getResources();
        TypedArray vouchers = res.obtainTypedArray(R.array.vouchers);
        image.setImageResource(vouchers.getResourceId(voucher.getImage() - 1, -1));

        setBuyButton();
    }

    private void setBuyButton() {
        Button buy = (Button)findViewById(R.id.shop_pop_disc_purchase);

        int userId = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetcher = new UserFetcher(getApplicationContext());
        int leaves = uFetcher.getCurrentLeavesById(userId);

        VoucherFetcher vFetcher = new VoucherFetcher(getApplicationContext());

        if (vFetcher.fetchVouchersByUserId(userId).contains(voucher)) {
            buy.setText(R.string.owned);
            buy.setEnabled(false);
        } else if (leaves < voucher.getPrice()) {
            buy.setText(R.string.insufficient_funds);
            buy.setEnabled(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setBuyButton();
    }

    public void buy(View view) {
        int userId = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserInfoHelper.spendLeaves(getApplicationContext(), userId, voucher.getPrice());

        // Update database
        UserFetcher uFetcher = new UserFetcher(getApplicationContext());
        uFetcher.giveUserVoucherbyId(userId, voucher.getId());


        Toast.makeText(getApplicationContext(), getString(R.string.purchase_successful), Toast.LENGTH_SHORT);
        finish();
    }
}
