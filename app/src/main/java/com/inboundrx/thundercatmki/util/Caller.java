package com.inboundrx.thundercatmki.util;

import com.inboundrx.thundercatmki.ui.DurationRewardActivity;
import com.inboundrx.thundercatmki.MainActivity;

/**
 * Created by arlen on 7/24/17.
 */

public class Caller {
    private void registerIn(BeaconCallback callback) {
        callback.beaconCallBackPositive();
    }
    private void registerOut(BeaconCallback callback) {
        callback.beaconCallBackNegative();
    }
    public static void main(String args) {
        Caller caller = new Caller();
        BeaconCallback callBack = new MainActivity();
        caller.registerIn(callBack);
    }
    public static void trackerMain(String args) {
        Caller caller = new Caller();
        BeaconCallback callBack = new DurationRewardActivity();
        if (args.equals("Positive")){
            caller.registerIn(callBack);
        } else {
            caller.registerOut(callBack);
        }
    }
}
