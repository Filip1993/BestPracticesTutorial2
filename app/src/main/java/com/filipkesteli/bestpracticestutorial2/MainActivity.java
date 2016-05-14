package com.filipkesteli.bestpracticestutorial2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText ed1, ed2;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button);
    }

    private void setupListeners() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = registerReceiver(null, intentFilter);

                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL);

                int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                boolean usbCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_USB);
                boolean acCharge = (chargePlug == BatteryManager.BATTERY_PLUGGED_AC);

                if (usbCharge) {
                    Toast.makeText(getApplicationContext(), "Mobile is charging on USB", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mobile is charging on AC", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}



