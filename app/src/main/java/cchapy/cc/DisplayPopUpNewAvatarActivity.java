package cchapy.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayPopUpNewAvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pop_up_new_avatar);

        //get the intent that started this activity
        Intent intent = getIntent();
    }

    /**
     * Method to be called to get the awarded avatar's details and set them in the display
     */
    private void getNewAvatar() {
        //TODO: Request random avatar that is not already collected by the user

        // Get elements
        TextView name = (TextView) findViewById(R.id.rewardAvatarName);
        ImageView avatar = (ImageView) findViewById(R.id.rewardedAvatar);
        int rarity = 2;
        ImageView star0 = (ImageView) findViewById(R.id.rewardStar0);
        ImageView star1 = (ImageView) findViewById(R.id.rewardStar1);
        ImageView star2 = (ImageView) findViewById(R.id.rewardStar2);

        if (rarity < 3) { // sophisticated means of making stars invisible
            star2.setVisibility(ImageView.INVISIBLE);
            if (rarity < 2) {
                star1.setVisibility(ImageView.INVISIBLE);
                if (rarity < 1) {
                    star0.setVisibility(ImageView.INVISIBLE);
                }
            }
        }
    }
}
