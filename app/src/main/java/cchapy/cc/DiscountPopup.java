package cchapy.cc;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscountPopup extends AppCompatActivity {

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
        Voucher voucher = vFetch.fetchVoucherById(id);

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
    }

    private void setBuyButton() {
        
    }
}
