package com.inboundrx.thundercatmki.BeaconManagers;

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

public class BeaconRangingManager {
    private BeaconManager beaconManager;
    private Caller caller;
    private Region region;
    public void findBeacon(Context context){
        beaconManager = new BeaconManager(context);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Log.d("logging range", Constants.BEACON_UUID);
                    Log.d("Demo App: ", Constants.BEACON_UUID);
                    final String beacons = "Positive";
                    Caller.main(beacons);
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

