package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ShopActivity extends AppCompatActivity
        implements VoucherListingFragment.OnListFragmentInteractionListener,
        AvatarListingFragment.OnListFragmentInteractionListener,
        AdapterView.OnItemSelectedListener{
    TabHost tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Spinner spin1 = (Spinner) findViewById(R.id.sort_avatar_select);
        spin1.setOnItemSelectedListener(this);

        Spinner spin2 = (Spinner) findViewById(R.id.sort_discount_select);
        spin2.setOnItemSelectedListener(this);

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
        }

        updateLeafCount();
        displayAvatars();
        displayDiscounts();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLeafCount();
        displayAvatars();
        displayDiscounts();
    }

    public void displayDiscounts() {
        ToggleButton toggle = (ToggleButton)findViewById(R.id.discount_sort_toggle);
        Spinner sortSelect = (Spinner)findViewById(R.id.sort_discount_select);

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

        arguments.putFloat("userID", -1);
        VoucherListingFragment voucherListing = new VoucherListingFragment();
        voucherListing.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.voucher_shoplist, voucherListing)
                .commit();
    }

    public void updateAvatars(View view) {
        displayAvatars();
    }

    public void updateDiscounts(View view) {
        displayDiscounts();
    }

    public void displayAvatars() {
        ToggleButton toggle = (ToggleButton)findViewById(R.id.avatar_sort_toggle);
        Spinner sortSelect = (Spinner)findViewById(R.id.sort_avatar_select);

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
                arguments.putString("sortMethod", "AvatarID");
                break;
            case "A-Z":
                arguments.putString("sortMethod", "Name");
                break;
            case "STARS":
                arguments.putString("sortMethod", "StarNum");
                break;
            case "PRICE":
                arguments.putString("sortMethod", "Price");
                break;
            case "新品":
                arguments.putString("sortMethod", "AvatarID");
                break;
            case "星级":
                arguments.putString("sortMethod", "StarNum");
                break;
            case "价格":
                arguments.putString("sortMethod", "Price");
                break;
        }

        AvatarListingFragment avatarListing = new AvatarListingFragment();
        avatarListing.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.avatar_shoplist, avatarListing)
                .commit();
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

        avatarLeafCount.setText(Integer.toString(leafCount));
        discountLeafCount.setText(Integer.toString(leafCount));
    }

    private void viewPopUpDiscount(Voucher voucher) {
        Intent intent = new Intent(this, DiscountPopup.class);
        intent.putExtra("DISCOUNT", voucher.getId());
        startActivity(intent);
    }

    private void viewPopUpAvatar(Avatar avatar) {
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        intent.putExtra("AVATAR", avatar.getId());
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(Voucher item) {
        viewPopUpDiscount(item);
    }

    @Override
    public void onListFragmentInteraction(Avatar item) {
        viewPopUpAvatar(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        displayAvatars();
        displayDiscounts();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
