package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cchapy.cc.dummy.UserContent;

public class FriendsActivity extends AppCompatActivity
        implements FriendFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.friends_fraglist, new FriendFragment())
                    .commit();
        }
        displayUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUser();
    }

    public void displayUser() {
        Context context = getApplicationContext();

        int userID = UserInfoHelper.getLoggedInId(context);

        if (userID == -1) {
            //no user logged in
            TextView userText = (TextView)findViewById(R.id.usernamestring);
            userText.setText("LOG IN");

            ImageView avatar = (ImageView)findViewById(R.id.userProfileImg);
            avatar.setVisibility(View.INVISIBLE);
        } else {
            UserFetcher uFetch = new UserFetcher(context);

            //user logged in
            String uNameString = uFetch.getUsernameById(userID);
            TextView userText = (TextView)findViewById(R.id.usernamestring);
            userText.setText(uNameString);
            ImageView avatar = (ImageView)findViewById(R.id.userProfileImg);
            avatar.setVisibility(View.VISIBLE);

            int avatarImageID = UserInfoHelper.getUserAvatarAlt(context, userID);
            Resources res = context.getResources();
            TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
            avatar.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));
        }
    }

    @Override
    public void onListFragmentInteraction(User user) {
        viewPopUpProfile(user);
    }

    public void viewPopUpProfile(User user) {
        if (UserInfoHelper.getLoggedInId(getApplicationContext()) == -1) {
            return;
        }
        //Create view QR intent
        //TODO: Build user into intent to display appropiate profile
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        //Always fetch user with id 1 to avoid crashing until friends activity works with user fetcher
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }

    public void viewPopUpProfile(View view) {
        if (UserInfoHelper.getLoggedInId(getApplicationContext()) == -1) {
            return;
        }
        //Create view QR intent
        //TODO: Build user into intent to display appropiate profile
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        //Always fetch user with id 1 to avoid crashing until friends activity works with user fetcher
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        intent.putExtra("USER_ID", userID);
        startActivity(intent);
    }
}
