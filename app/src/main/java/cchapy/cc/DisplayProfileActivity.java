package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        //get the intent that started this activity
        Intent intent = getIntent();

        updateProfile();

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateProfile();
    }

    public void updateProfile() {
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        Context context = getApplicationContext();

        if (userID == -1) {
            return;
        }

        UserFetcher uFetch = new UserFetcher(getApplicationContext());
        User user = uFetch.fetchUser(userID);

        TextView username = (TextView)findViewById(R.id.usernameText);
        username.setText(user.getUserName());

        ImageView avatarImage = (ImageView)findViewById(R.id.Image_Avatar);

        int avatarImageID = UserInfoHelper.getUserAvatarMain(context, userID);
        Resources res = context.getResources();
        TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
        avatarImage.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));

        TextView city = (TextView)findViewById(R.id.cityText);
        city.setText(user.getCity());

        TextView leaves = (TextView)findViewById(R.id.leavecountText);
        leaves.setText(Integer.toString(user.getLeafCount()));

        TextView co2 = (TextView)findViewById(R.id.co2Text);
        co2.setText(Integer.toString(user.getCarbon()));

        TextView friendCount = (TextView)findViewById(R.id.friendcountText);
        friendCount.setText(Integer.toString(uFetch.getFriendCountById(userID)));
    }
}
