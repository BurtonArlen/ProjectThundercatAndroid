package com.inboundrx.thundercatmki.BeaconManagers;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.inboundrx.thundercatmki.Constants;
import com.inboundrx.thundercatmki.util.Caller;

import java.util.List;
import java.util.UUID;

/**
 * Created by arlen on 7/24/17.
 */

public class BeaconDataTrackingManager{
    private BeaconManager beaconManager;
    private Caller caller;
    private Region region;
    public void trackBeacon(Context context){
        beaconManager = new BeaconManager(context);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                Log.d("logging Data tracker", Constants.BEACON_UUID);
                if (!list.isEmpty()) {
                    final String[] beacons = new String[]{"Found Beacon"};
                    Log.d("Demo App: ", Constants.BEACON_UUID);
                    Caller.trackerMain(beacons);
                }
            }
        });
        region = new Region("ranged region", UUID.fromString(Constants.BEACON_UUID), null, null);
    }

    public void beaconResume(){
        beaconManager.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady(){
                beaconManager.startRanging(region);
            }
        });
    }
    public void beaconPause(){
        beaconManager.stopRanging(region);
    }
}
