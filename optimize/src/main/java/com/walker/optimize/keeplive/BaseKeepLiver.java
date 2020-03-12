package com.walker.optimize.keeplive;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * @Author Walker
 * @Date 2020-03-05 15:29
 * @Summary 保活器基类
 */
public abstract class BaseKeepLiver implements IKeepLive {
    public static final String TAG = "KeepLive";
    public WeakReference<Context> mContextReference;

    public void setContext(Context context) {
        mContextReference = new WeakReference<>(context);
    }

    /**
     * 跳转到指定应用的首页
     */
    public void showActivity(@NonNull String packageName) {
        if (mContextReference.get() != null) {
            Intent intent = mContextReference.get().getPackageManager().getLaunchIntentForPackage(packageName);
            mContextReference.get().startActivity(intent);
        }
    }

    /**
     * 跳转到指定应用的指定页面
     */
    public void showActivity(@NonNull String packageName, @NonNull String activityDir) {
        if (mContextReference.get() != null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, activityDir));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContextReference.get().startActivity(intent);
        }
    }
}
