package com.inboundrx.thundercatmki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.estimote.sdk.SystemRequirementsChecker;
import com.inboundrx.thundercatmki.BeaconManagers.BeaconDataTrackingManager;
import com.inboundrx.thundercatmki.BeaconManagers.BeaconRangingManager;
import com.inboundrx.thundercatmki.util.BeaconCallback;

public class DurationRewardActivity extends AppCompatActivity implements BeaconCallback {
    private BeaconDataTrackingManager dataTracker = new BeaconDataTrackingManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration_reward);
        dataTracker.trackBeacon(this);
    }

    public void beaconCallBack() {
        System.out.println("I've been called back from the tracker");
    }

    @Override
    protected void onPause(){
        dataTracker.beaconPause();
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataTracker.beaconResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}
