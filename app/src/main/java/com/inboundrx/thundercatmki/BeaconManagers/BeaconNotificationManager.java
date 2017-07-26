package com.inboundrx.thundercatmki.BeaconManagers;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.repackaged.retrofit_v1_9_0.retrofit.RestAdapter;
import com.inboundrx.thundercatmki.Constants;
import com.inboundrx.thundercatmki.MainActivity;
import com.inboundrx.thundercatmki.util.Caller;

import java.util.List;
import java.util.UUID;

/**
 * Created by arlen on 7/24/17.
 */

public class BeaconNotificationManager extends Application {
    private BeaconManager beaconManager;

    public void showNotification(String title, String message){
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0, new Intent[] {notifyIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        beaconManager = new BeaconManager(getApplicationContext());

        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {
                Log.d("logging region", Constants.BEACON_UUID);
                showNotification(
                        "Welcome to the Byteshift Demo", "Tap the notification to continue");
            }
            @Override
            public void onExitedRegion(Region region) {
                final String[] beacons = new String[]{"Exited Region"};
                Caller.main(beacons);
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady(){
                beaconManager.startMonitoring(
                        new Region("monitored region", UUID.fromString
                                (Constants.BEACON_UUID), null, null));
            }
        });
    }
}
