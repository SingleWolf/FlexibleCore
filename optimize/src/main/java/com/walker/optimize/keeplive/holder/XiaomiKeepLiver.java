package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 22:00
 * @Summary 小米保活器
 */
public class XiaomiKeepLiver extends BaseKeepLiver {

    public XiaomiKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "XiaomiKeepLiver created");
    }

    @Override
    public void transact() {
        showActivity("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }
}
