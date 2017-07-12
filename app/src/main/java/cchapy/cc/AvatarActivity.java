package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AvatarActivity extends AppCompatActivity {
    List<Avatar> ownedAvatars = new ArrayList<>();
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        getAvatars();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAvatars();
    }

    public void getAvatars() {
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        if (userID != -1) {
            UserFetcher uFetch = new UserFetcher(getApplicationContext());

            ownedAvatars = uFetch.getOwnedAvatarsByUserId(userID);
            displayEquippedAvatar();
        }
    }

    public void displayEquippedAvatar() {
        Context context = getApplicationContext();
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetch = new UserFetcher(getApplicationContext());

        int equippedAvatar = uFetch.getAvatarByUserId(userID);

        Avatar displayAvatar;
        index = 0;

        displayAvatar = ownedAvatars.get(0);
        for (Avatar avatar: ownedAvatars) {
            if (avatar.getId() == equippedAvatar) {
                displayAvatar = avatar;
                break;
            }
            index++;
        }

        TextView avatarText = (TextView)findViewById(R.id.avatar_avatarName);
        avatarText.setText(displayAvatar.getName());
        ImageView avatarImage = (ImageView)findViewById(R.id.avatar_avatarImage);

        int avatarImageID = UserInfoHelper.getUserAvatarMain(context, userID);
        Resources res = context.getResources();
        TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
        avatarImage.setImageResource(avatarIndex.getResourceId(avatarImageID, -1));

        updateStars(displayAvatar);
    }

    public void displayNextAvatar(View view) {
        Context context = getApplicationContext();
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetch = new UserFetcher(getApplicationContext());
        AvatarFetcher aFetch = new AvatarFetcher(getApplicationContext());

        index++;

        if (index >= ownedAvatars.size()) {
            index = 0;
        }

        Avatar displayAvatar = ownedAvatars.get(index);
        TextView avatarText = (TextView)findViewById(R.id.avatar_avatarName);
        avatarText.setText(displayAvatar.getName());
        ImageView avatarImage = (ImageView)findViewById(R.id.avatar_avatarImage);

        String gender = uFetch.getGenderByUserId(userID);

        int avatarImageID = aFetch.getAvatarMainByAvatarId(displayAvatar.getId(), gender);

        Resources res = context.getResources();
        TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
        avatarImage.setImageResource(avatarIndex.getResourceId(avatarImageID - 1, -1));

        updateStars(displayAvatar);
    }

    public void displayPreviousAvatar(View view) {
        Context context = getApplicationContext();
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetch = new UserFetcher(getApplicationContext());
        AvatarFetcher aFetch = new AvatarFetcher(getApplicationContext());

        if (index == 0) {
            index = ownedAvatars.size() - 1;
        } else {
            index--;
        }

        Avatar displayAvatar = ownedAvatars.get(index);
        TextView avatarText = (TextView)findViewById(R.id.avatar_avatarName);
        avatarText.setText(displayAvatar.getName());
        ImageView avatarImage = (ImageView)findViewById(R.id.avatar_avatarImage);

        String gender = uFetch.getGenderByUserId(userID);

        int avatarImageID = aFetch.getAvatarMainByAvatarId(displayAvatar.getId(), gender);

        Resources res = context.getResources();
        TypedArray avatarIndex = res.obtainTypedArray(R.array.avatars);
        avatarImage.setImageResource(avatarIndex.getResourceId(avatarImageID - 1, -1));

        updateStars(displayAvatar);
    }

    public void updateStars(Avatar avatar) {
        int rarity = avatar.getRarity();

        ImageView rarity3 = (ImageView)findViewById(R.id.avatar_rarity3);
        ImageView rarity2 = (ImageView)findViewById(R.id.avatar_rarity2);

        rarity3.setVisibility(View.VISIBLE);
        rarity2.setVisibility(View.VISIBLE);

        if (rarity < 3) {
            rarity3.setVisibility(View.INVISIBLE);
        }
        if (rarity < 2) {
            rarity2.setVisibility(View.INVISIBLE);
        }
    }

    public void viewShopFromAvatar(View view) {
        //Create view shop intent
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
    }

    public void setAvatar(View view) {
        Avatar toChange = ownedAvatars.get(index);

        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());
        UserFetcher uFetch = new UserFetcher(getApplicationContext());

        uFetch.setAvatarByUserID(toChange.getId(), userID);

        Toast.makeText(this, "Avatar Updated", Toast.LENGTH_LONG).show();
    }
}
