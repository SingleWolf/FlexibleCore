package com.walker.flexiblecore;

import android.app.Application;

import com.walker.core.exception.CrashHandler;
import com.walker.core.exception.OnCrashListener;
import com.walker.core.util.ToastUtils;

/**
 * @author Walker
 * @date on 2018/7/10 0010 下午 17:24
 * @email feitianwumu@163.com
 * @desc Application
 */
public class MyApplication extends Application {
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
    }
}
