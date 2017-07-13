package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class LoginPromptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login_prompt);
    }

    public void cancel(View view) {
        finish();
    }

    public void checkLogin(View view) {
        EditText username = (EditText)findViewById(R.id.login_username);
        EditText password = (EditText)findViewById(R.id.login_password);

        String uNameString = username.getText().toString();
        String pWordString = password.getText().toString();

        UserFetcher uFetch = new UserFetcher(view.getContext());
        if (uFetch.checkUserLogin(uNameString, pWordString)) {
            Toast.makeText(this, R.string.login_success, Toast.LENGTH_LONG).show();
            SharedPreferences userData = view.getContext().getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = userData.edit();
            int userID = uFetch.getUserIdByUsername(uNameString);
            editor.clear();
            editor.putInt(getString(R.string.saved_user_id), userID);
            editor.commit();
            finish();
        } else {
            Toast.makeText(this, R.string.login_fail, Toast.LENGTH_LONG).show();
            password.setText("");
        }
    }

}
