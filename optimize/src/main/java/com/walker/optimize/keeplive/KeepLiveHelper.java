package com.walker.optimize.keeplive;

import android.app.Activity;
import android.util.Log;

import com.walker.optimize.keeplive.holder.DefaultKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 15:54
 * @Summary 保活辅助类
 */
public class KeepLiveHelper {
    private static final String TAG = "KeepLiveHelper";

    private static KeepLiveHelper sInstance;

    private KeepLiveHelper() {
    }

    public static KeepLiveHelper get() {
        if (sInstance == null) {
            synchronized (KeepLiveHelper.class) {
                if (sInstance == null) {
                    sInstance = new KeepLiveHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 申请加入厂商系统的后台管理白名单
     */
    public boolean transactPlatfromWhiteList(Activity context) {
        boolean transact_ok;
        if (context == null) {
            Log.e(TAG, "context is null");
            return false;
        }
        try {
            KeepLiveFactory.create(context).transact();
            transact_ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            transact_ok = false;
        }
        return transact_ok;
    }

    /**
     * 申请加入android系统后台运行白名单
     */
    public boolean transactSystemWhiteList(Activity context) {
        boolean transact_ok;
        if (context == null) {
            Log.e(TAG, "context is null");
            return false;
        }
        try {
            new DefaultKeepLiver(context).transact();
            transact_ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            transact_ok = false;
        }
        return transact_ok;
    }
}
