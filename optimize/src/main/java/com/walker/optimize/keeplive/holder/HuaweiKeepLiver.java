package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 15:26
 * @Summary 华为保活器
 */
public class HuaweiKeepLiver extends BaseKeepLiver {

    public HuaweiKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "HuaweiKeepLiver created");
    }

    @Override
    public void transact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                showActivity("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
            } catch (Exception e) {
                Log.d(TAG, "HuaweiKeepLiver :"+e.toString());
                showActivity("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
            }
        }
    }
}
