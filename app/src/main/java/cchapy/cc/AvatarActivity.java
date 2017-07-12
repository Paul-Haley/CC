package cchapy.cc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AvatarActivity extends AppCompatActivity {
    List<Avatar> ownedAvatars = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
    }

    public void getAvatars() {
        int userID = UserInfoHelper.getLoggedInId(getApplicationContext());


    }
}
