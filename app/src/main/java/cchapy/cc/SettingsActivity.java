package cchapy.cc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void viewDb(View view) {
        //Create view shop intent
        Intent intent = new Intent(this, checkDatabase.class);
        startActivity(intent);
    }

    public void promptLogin(View view) {
        //Create view Settings intent
        Intent intent = new Intent(this, LoginPromptActivity.class);
        startActivity(intent);
    }

    public void clearLogin(View view) {
        SharedPreferences userData = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userData.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(this, "User Data Deleted", Toast.LENGTH_LONG).show();
    }
}
