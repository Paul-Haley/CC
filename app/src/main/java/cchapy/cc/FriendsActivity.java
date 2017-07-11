package cchapy.cc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

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
    }

    @Override
    public void onListFragmentInteraction(UserContent.User user) {
        viewPopUpProfile(user);
    }

    public void viewPopUpProfile(UserContent.User user) {
        //Create view QR intent
        //TODO: Build user into intent to display appropiate profile
        Intent intent = new Intent(this, DisplayPopUpProfileActivity.class);
        //Always fetch user with id 1 to avoid crashing until friends activity works with user fetcher
        intent.putExtra("USER_ID", 1);
        startActivity(intent);
    }
}
