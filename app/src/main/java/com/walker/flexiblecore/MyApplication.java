package com.walker.flexiblecore;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.walker.core.exception.CrashHandler;
import com.walker.core.exception.OOMHelper;
import com.walker.core.exception.OnCrashListener;
import com.walker.core.util.ToastUtils;
import com.walker.optimize.analyzer.IndexAnalyzer;
import com.walker.optimize.analyzer.TimeAnalyzerMgr;

/**
 * @author Walker
 * @date on 2018/7/10 0010 下午 17:24
 * @email feitianwumu@163.com
 * @desc Application
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        TimeAnalyzerMgr.getInstance().startTimeAnalyzer(1);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(new OnCrashListener() {
            @Override
            public void onTransact(Throwable e) {
                ToastUtils.showCenterShort(e.toString());
            }
        });
        ToastUtils.init(this);
        TimeAnalyzerMgr.getInstance().getTimeAnalyzer(1).recordingTimeTag("application-onCreate");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= TRIM_MEMORY_UI_HIDDEN && level == TRIM_MEMORY_RUNNING_CRITICAL) {
            Log.i("onTrimMemory", OOMHelper.get().listStatisticsInfo(Process.myPid()));
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        String keyInfo=new IndexAnalyzer().listKeyInfo(getApplicationContext());
        Log.i("onLowMemory", keyInfo);
    }
}
