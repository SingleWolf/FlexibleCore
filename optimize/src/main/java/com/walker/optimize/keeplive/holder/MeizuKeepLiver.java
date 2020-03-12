package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 22:00
 * @Summary 魅族保活器
 */
public class MeizuKeepLiver extends BaseKeepLiver {

    public MeizuKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "MeizuKeepLiver created");
    }

    @Override
    public void transact() {
        showActivity("com.meizu.safe");
    }
}
