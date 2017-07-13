package cchapy.cc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRActivity extends AppCompatActivity {

    private final String QR_PREFIX = "CCHAPY_";

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
                Toast.makeText(this, getString(R.string.cancelled), Toast.LENGTH_LONG).show();
                finish(); // Go back to the last activity
            } else {
                //verification of QR code
                String scannedResult = result.getContents();
                if (scannedResult.startsWith(QR_PREFIX)) {//TODO: MAKE A BETTER VERIFICATION CODE
                    //valid TODO: add check for numbers after CCHAPY_
                    Intent intent = new Intent(this, ScannedActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, getString(R.string.invalid_QR), Toast.LENGTH_LONG).show();
                    finish(); // Go back to the last activity
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
