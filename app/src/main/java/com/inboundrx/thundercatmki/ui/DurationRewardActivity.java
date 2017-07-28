package com.inboundrx.thundercatmki.ui;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.estimote.sdk.SystemRequirementsChecker;
import com.inboundrx.thundercatmki.BeaconManagers.BeaconDataTrackingManager;
import com.inboundrx.thundercatmki.BeaconManagers.BeaconRangingManager;
import com.inboundrx.thundercatmki.R;
import com.inboundrx.thundercatmki.util.BeaconCallback;

public class DurationRewardActivity extends AppCompatActivity implements BeaconCallback {
    private BeaconDataTrackingManager dataTracker = new BeaconDataTrackingManager();
    public boolean inRange;
    public boolean rangeStatus;
    private TextView countTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duration_reward);
        countTimer = (TextView) findViewById(R.id.countTimer);
        dataTracker.trackBeacon(this);
        timerChecker();
    }

    public void timerChecker(){
        Handler handler = new Handler();
        int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                rangeFinder();
                Log.d("inRange status looper", String.valueOf(inRange));
                timerChecker();
            }
        }, delay);
    }

    public void beaconCallBackPositive() {
        System.out.println("I've been called back from the tracker Positive");
        inRange = true;
        rangeValue(inRange);
    }

    public void beaconCallBackNegative() {
        System.out.println("I've been called back from the tracker Negative");
        inRange = false;
        rangeValue(inRange);
    }

    public void rangeValue(final boolean inRange){
        DurationRewardActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                rangeStatus = inRange;
            }
        });
        Log.d("inRange status", String.valueOf(inRange));
    }

    public void rangeFinder(){
        DurationRewardActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                if (inRange){
                    System.out.println("I'm in range of a beacon ++++++");
                    countTimer.setTextColor(Color.parseColor("#6df319"));
                }
                if (!inRange){
                    System.out.println("I'm not in range of a beacon ------");
                    countTimer.setTextColor(Color.parseColor("#cd0037"));
                }
            }
        });
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
