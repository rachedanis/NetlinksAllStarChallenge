package com.yalantis.guillotine.sample.utls;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by WiKi on 21/02/2016.
 */
public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent serviceIntent = new Intent(context, service.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
        context.startService(serviceIntent);

    }


}

