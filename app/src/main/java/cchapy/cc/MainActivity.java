package cchapy.cc;


import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get application context
        mContext = getApplicationContext();

        //Get self activity
        mActivity = MainActivity.this;
    }

    public void viewProfile(View view) {
        //Create view profile intent
        Intent intent = new Intent(this, DisplayProfileActivity.class);
        startActivity(intent);
    }
}
