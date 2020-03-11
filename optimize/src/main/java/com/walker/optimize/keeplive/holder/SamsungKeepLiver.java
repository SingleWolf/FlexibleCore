package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 22:00
 * @Summary 三星保活器
 */
public class SamsungKeepLiver extends BaseKeepLiver {

    public SamsungKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "SamsungKeepLiver created");
    }

    @Override
    public void transact() {
        try {
            showActivity("com.samsung.android.sm_cn");
        }catch (Exception e){
            Log.d(TAG, "SamsungKeepLiver :" + e.toString());
            showActivity("com.samsung.android.sm");
        }
    }
}
