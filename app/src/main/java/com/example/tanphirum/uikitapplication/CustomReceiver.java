package com.example.tanphirum.uikitapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    public static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = null;
        String intentAction = intent.getAction();
        if (intentAction == null) return;

        switch (intentAction) {
            case Intent.ACTION_POWER_CONNECTED:
                message = context.getString(R.string.power_connected);
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                message = context.getString(R.string.power_disconnected);
                break;
            case Intent.ACTION_BATTERY_CHANGED:
                message = context.getString(R.string.power_changed);
                break;
            case ACTION_CUSTOM_BROADCAST:
                message = context.getString(R.string.local_broadcast_call);
                break;
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
