package fi.codeboy.myremotecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.util.Log;

// IR codes
// https://github.com/iqqmuT/irdb/blob/master/codes/Harman%20Kardon/Stereo%20Receiver/134%2C118.csv
// https://github.com/probonopd/irdb/blob/master/codes/Epson/Video%20Projector/131%2C85.csv
//
// To get the frequency and pattern, use IRScrutinizer tool:
// https://github.com/bengtmartensson/IrScrutinizer
// 1) Options > Output Text Format > Raw Without Signs
// 2) Render > Set the values and "Render"

public class MainActivity extends AppCompatActivity {
    int FREQUENCY = 38400;
    int[] IR_EPSON_POWER_OFF = {9024,4512,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,40884};
    int [] IR_EPSON_POWER_ON = {9024,4512,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,40884};
    int[] IR_HK_POWER_OFF = {9024,4512,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,39756,9024,2256,564,96156};
    int[] IR_HK_POWER_ON = {9024,4512,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,1692,564,1692,564,1692,564,1692,564,564,564,1692,564,39756,9024,2256,564,96156};
    int[] IR_VOL_DOWN = {9024,4512,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,1692,564,1692,564,564,564,564,564,1692,564,564,564,1692,564,564,564,564,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,39756,9024,2256,564,96156};
    int[] IR_VOL_UP = {9024,4512,564,564,564,1692,564,1692,564,564,564,564,564,564,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,1692,564,1692,564,564,564,564,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,1692,564,564,564,1692,564,564,564,1692,564,564,564,1692,564,39756,9024,2256,564,96156};

    private ConsumerIrManager irManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        findViewById(R.id.btn_epson_power_off).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_EPSON_POWER_OFF));
        findViewById(R.id.btn_epson_power_on).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_EPSON_POWER_ON));
        findViewById(R.id.btn_hk_power_off).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_HK_POWER_OFF));
        findViewById(R.id.btn_hk_power_on).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_HK_POWER_ON));
        findViewById(R.id.btn_vol_dn).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_VOL_DOWN));
        findViewById(R.id.btn_vol_up).setOnClickListener(v -> sendIRSignal(FREQUENCY, IR_VOL_UP));
    }

    private void sendIRSignal(int frequency, int[] pattern) {
        if (!irManager.hasIrEmitter()) {
            Log.e("IR", "No IR emitter found on this device");
            return;
        }
        irManager.transmit(frequency, pattern);
    }
}