package com.inboundrx.thundercatmki.util;

import com.inboundrx.thundercatmki.MainActivity;

/**
 * Created by arlen on 7/24/17.
 */

public class Caller {
    private void register(BeaconCallback callback) {
        callback.beaconCallBack();
    }
    public static void main(String[] args) {
        Caller caller = new Caller();
        BeaconCallback callBack = new MainActivity();
        caller.register(callBack);
    }
}
