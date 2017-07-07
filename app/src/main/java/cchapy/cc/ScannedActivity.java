package cchapy.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by paulhaley on 7/7/17.
 *
 * Activity for when a QR code is successfully scanned
 */
public class ScannedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned);

        TextView leaves = (TextView) findViewById(R.id.leaves);
        int leaveCount = 50;
        leaves.setText("You received " + leaveCount + " x "); //TODO: make dynamic
        //TODO: put logic for awarding on avatars
    }

    /**
     * Should only be called when there is a avatar awarded
     * @param view
     */
    public void viewNewAvatar(View view) {
        Intent intent = new Intent(this, DisplayPopUpNewAvatarActivity.class);
        startActivity(intent);
    }
}
