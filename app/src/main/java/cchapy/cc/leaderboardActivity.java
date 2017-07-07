package cchapy.cc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class leaderboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.leaderboardContainer, new UserFragment())
                    .commit();
        }
    }
}
