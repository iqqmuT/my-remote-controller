package fi.codeboy.myremotecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    int FREQUENCY = 38000;
    int[] IR_VOL_UP = {4512, 4512, 564, 564, 564, 1692, 564, 1692, 564, 564, 564, 564, 564, 564, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 1692, 564, 564, 564, 1692, 564, 1692, 564, 1692, 564, 564, 564, 564, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 1692, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 564, 564, 1692, 564, 43992, 4512, 4512, 564, 1692, 564, 95880};

    private ConsumerIrManager irManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
    }

    private void sendIRSignal(int frequency, int[] pattern) {
        if (!irManager.hasIrEmitter()) {
            Log.e("IR", "No IR emitter found on this device");
            return;
        }
        irManager.transmit(frequency, pattern);
    }
}