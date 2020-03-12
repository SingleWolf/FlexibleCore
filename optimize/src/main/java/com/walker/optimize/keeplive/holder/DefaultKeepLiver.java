package com.walker.optimize.keeplive.holder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.walker.optimize.keeplive.BaseKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 15:17
 * @Summary 默认保活器，没有匹配到厂商时申请系统白名单
 */
public class DefaultKeepLiver extends BaseKeepLiver {

    public DefaultKeepLiver(Context context) {
        setContext(context);
        Log.d(TAG, "DefaultKeepLiver created");
    }

    @Override
    public void transact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isIgnoringBatteryOptimizations()) {
                requestIgnoreBatteryOptimizations();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        if (mContextReference.get() != null) {
            PowerManager powerManager = (PowerManager) mContextReference.get().getSystemService(Context.POWER_SERVICE);
            if (powerManager != null) {
                isIgnoring = powerManager.isIgnoringBatteryOptimizations(mContextReference.get().getPackageName());
            }
        }
        return isIgnoring;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        if (mContextReference.get() != null) {
            try {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + mContextReference.get().getPackageName()));
                mContextReference.get().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
