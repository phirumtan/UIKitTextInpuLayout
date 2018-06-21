package com.example.tanphirum.uikitapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class BroadcastActivity extends AppCompatActivity {

    private ToggleButton mTgbOnOff;
    private Button mBtnSend;

    private CustomReceiver mReceiver;

    private ComponentName mReceiverConComponentName;
    private PackageManager mPackageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mTgbOnOff = findViewById(R.id.tgb_on_off);
        mBtnSend = findViewById(R.id.btn_send_broad);

        mReceiver = new CustomReceiver();

        mReceiverConComponentName = new ComponentName(this, CustomReceiver.class);
        mPackageManager = getPackageManager();

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(CustomReceiver.ACTION_CUSTOM_BROADCAST));
        registerGlobalReceiver(true);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomReceiver.ACTION_CUSTOM_BROADCAST);
                LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(intent);
            }
        });

        mTgbOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                registerGlobalReceiver(isChecked);
            }
        });
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void registerGlobalReceiver(boolean isRegister) {
        if (isRegister) {
            mPackageManager.setComponentEnabledSetting(mReceiverConComponentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        } else {
            mPackageManager.setComponentEnabledSetting(mReceiverConComponentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
    }
}
