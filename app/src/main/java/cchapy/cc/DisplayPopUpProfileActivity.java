package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayPopUpProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_display_pop_up_profile);

        UserFetcher fetcher = new UserFetcher(this);

        //get the intent that started this activity
        Intent intent = getIntent();
        int id = intent.getIntExtra("USER_ID", 0);

        User user = fetcher.fetchUserById(id);

        TextView unText = (TextView)findViewById(R.id.usernameText);
        unText.setText(user.getUserName());

        TextView cityText = (TextView)findViewById(R.id.cityText);
        cityText.setText(user.getCity());

        TextView leaveCountText = (TextView)findViewById(R.id.leavecountText);
        leaveCountText.setText(String.valueOf(user.getTotalLeafCount()));

        TextView co2Text = (TextView)findViewById(R.id.co2Text);
        co2Text.setText(String.valueOf(user.getCarbon()));

        int avatarImageID = UserInfoHelper.getUserAvatarMain(this, user.getId());

        //Obtain avatar resources
        Resources res = this.getResources();
        TypedArray avatars = res.obtainTypedArray(R.array.avatars);
        ImageView avatarView = (ImageView)findViewById(R.id.Image_Avatar);
        avatarView.setImageResource(avatars.getResourceId(avatarImageID, -1));

        TextView friendCount = (TextView)findViewById(R.id.friendcountText);
        friendCount.setText(Integer.toString(fetcher.getFriendCountById(user.getId())));
    }
}
