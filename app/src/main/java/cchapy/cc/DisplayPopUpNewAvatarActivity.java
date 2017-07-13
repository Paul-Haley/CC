package cchapy.cc;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class DisplayPopUpNewAvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_pop_up_new_avatar);
        ((Vibrator) this.getSystemService(VIBRATOR_SERVICE)).vibrate(500);

        getNewAvatar();

        //get the intent that started this activity
        Intent intent = getIntent();

        final ImageView box = (ImageView) findViewById(R.id.rewardedBox);
        final ImageView avatar = (ImageView) findViewById(R.id.rewardedAvatar);

        box.setImageResource(R.drawable.box_closed);
        avatar.setVisibility(View.INVISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                box.setImageResource(R.drawable.box_open);
            }
        }, 500);

        MediaPlayer.create(this, R.raw.crate1).start();
        //avatar
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                avatar.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }

    /**
     * Method to be called to get the awarded avatar's details and set them in the display
     */
    private void getNewAvatar() {
        AvatarFetcher aFetch = new AvatarFetcher(getApplicationContext());
        UserFetcher uFetch = new UserFetcher(getApplicationContext());
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());

        List<Avatar> allAvatars = aFetch.fetchAllAvatars();
        List<Avatar> ownedAvatars = uFetch.getOwnedAvatarsByUserId(userID);

        int randIndex = (int)(Math.random() * allAvatars.size());

        Avatar awardedAvatar = allAvatars.get(randIndex);

        // Get elements
        TextView name = (TextView) findViewById(R.id.rewardAvatarName);
        ImageView star0 = (ImageView) findViewById(R.id.rewardStar0);
        ImageView star1 = (ImageView) findViewById(R.id.rewardStar1);
        ImageView star2 = (ImageView) findViewById(R.id.rewardStar2);
        ImageView rewardedAvatar = (ImageView)findViewById(R.id.rewardedAvatar);

        //get values
        int rarity = awardedAvatar.getRarity();
        String avatarName = awardedAvatar.getName();
        int avatarImage = aFetch.getAvatarMainByAvatarId(awardedAvatar.getId(), uFetch.getGenderByUserId(userID));

        if (rarity < 3) { // sophisticated means of making stars invisible
            star2.setVisibility(ImageView.INVISIBLE);
            if (rarity < 2) {
                star1.setVisibility(ImageView.INVISIBLE);
                if (rarity < 1) {
                    star0.setVisibility(ImageView.INVISIBLE);
                }
            }
        }

        UserInfoHelper.addLeaves(getApplicationContext(), userID, 50);

        name.setText(avatarName);
        Resources res = getApplicationContext().getResources();
        TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
        rewardedAvatar.setImageResource(avatarIndex.getResourceId(avatarImage - 1, -1));

        //check if avatar is already owned
        if (ownedAvatars.contains(awardedAvatar)) {
            Toast.makeText(this, "Avatar Already Owned, +50 Leaves", Toast.LENGTH_LONG).show();
            UserInfoHelper.addLeaves(getApplicationContext(), userID, 50);
        } else {
            uFetch.addAvatarToUserById(userID, awardedAvatar.getId());
        }


    }

    @Override
    public void onBackPressed() {
        navigateUpFromSameTask(this);
    }
}
