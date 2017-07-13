package cchapy.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt(getString(R.string.qrPrompt));
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.initiateScan(); // cause qr code scanner to start

        //get the intent that started this activity
        Intent intent = getIntent();
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                finish(); // Go back to the last activity
            } else {
                //TODO: Delete this later
                //verification of QR code
                String scannedResult = result.getContents();
                if (scannedResult.startsWith("CCHAPY_")) {
                    //valid
                    //TODO: add check for numbers after CCHAPY_
                    Toast.makeText(this, "REMEMBER TO DELETE THIS\nScanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ScannedActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid QR Code", Toast.LENGTH_LONG).show();
                    finish(); // Go back to the last activity
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
