package cchapy.cc;

import android.content.Intent;
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

public class AvatarPopup extends AppCompatActivity {

    private Avatar avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_avatar_popup);

        Intent intent = getIntent();
        updatePopup(intent.getIntExtra("AVATAR", 0));
    }

    /**
     * Fills in all the fields with the selected voucher's details
     * @param id Avatar id
     */
    private void updatePopup(int id) {
        UserFetcher uFetch = new UserFetcher(getApplicationContext());
        AvatarFetcher aFetch = new AvatarFetcher((getApplicationContext()));
        avatar = aFetch.fetchAvatarById(id);

        TextView title = (TextView)findViewById(R.id.shop_pop_ava_title);
        title.setText(avatar.getName());

        TextView price = (TextView)findViewById(R.id.shop_pop_ava_leaf);
        price.setText(Integer.toString(avatar.getPrice()));

        TextView description = (TextView)findViewById(R.id.shop_pop_ava_desc);
        description.setText(avatar.getDescription());

        //Changing the image
        ImageView image = (ImageView)findViewById(R.id.shop_pop_ava_image);
        Resources res = this.getResources();
        TypedArray avatars = res.obtainTypedArray(R.array.avatars);
        image.setImageResource(avatars.getResourceId(avatar.getImageMainByGender(uFetch.getGenderByUserId(UserInfoHelper.getLoggedInId(getApplicationContext()))) - 1, 1));


        //Setting rating
        int rarity = avatar.getRarity();
        if (rarity < 3) {
            findViewById(R.id.shop_pop_ava_star3).setVisibility(View.INVISIBLE);
            if (rarity < 2) {
                findViewById(R.id.shop_pop_ava_star2).setVisibility(View.INVISIBLE);
            }
        }

        setBuyButton();
    }

    private void setBuyButton() {
        Button buy = (Button)findViewById(R.id.shop_pop_ava_purchase);

        int userId = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetcher = new UserFetcher(getApplicationContext());
        int leaves = uFetcher.getCurrentLeavesById(userId);

        if (uFetcher.getOwnedAvatarsByUserId(userId).contains(avatar)) {
            buy.setText(R.string.owned);
            buy.setEnabled(false);
        } else if (leaves < avatar.getPrice()) {
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
        UserInfoHelper.spendLeaves(getApplicationContext(), userId, avatar.getPrice());

        // Update database
        UserFetcher uFetcher = new UserFetcher(getApplicationContext());
        uFetcher.addAvatarToUserById(userId, avatar.getId());

        Toast.makeText(getApplicationContext(), getString(R.string.purchase_successful), Toast.LENGTH_SHORT).show();
        finish();
    }
}
