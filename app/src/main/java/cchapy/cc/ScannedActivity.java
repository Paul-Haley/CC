package cchapy.cc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        leaves.setText("You received 50 Leaves!"); //TODO: make dynamic
    }
}
