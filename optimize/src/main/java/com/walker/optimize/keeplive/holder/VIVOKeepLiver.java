package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 22:00
 * @Summary VIVO保活器
 */
public class VIVOKeepLiver extends BaseKeepLiver {

    public VIVOKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "VIVOKeepLiver created");
    }

    @Override
    public void transact() {
        showActivity("com.iqoo.secure");
    }
}
