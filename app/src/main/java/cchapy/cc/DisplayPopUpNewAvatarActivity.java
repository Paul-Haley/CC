package cchapy.cc;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class DisplayPopUpNewAvatarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_pop_up_new_avatar);
        ((Vibrator) this.getSystemService(VIBRATOR_SERVICE)).vibrate(500);

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
        //TODO: Request random avatar that is not already collected by the user

        // Get elements
        TextView name = (TextView) findViewById(R.id.rewardAvatarName);
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

    @Override
    public void onBackPressed() {
        navigateUpFromSameTask(this);
    }
}
