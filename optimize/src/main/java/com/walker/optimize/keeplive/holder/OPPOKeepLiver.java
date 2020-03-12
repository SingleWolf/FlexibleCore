package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

public class OPPOKeepLiver extends BaseKeepLiver {

    public OPPOKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "OPPOKeepLiver created");
    }

    @Override
    public void transact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e) {
                Log.d(TAG, "OPPOKeepLiver :" + e.toString());
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception ex) {
                    Log.d(TAG, "OPPOKeepLiver :" + ex.toString());
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }
}
