package com.walker.core.util;

import android.app.Activity;
import android.view.WindowManager;

/**
 * @author walker zheng
 * @date 2018/12/21
 * @desc 设备工具类
 */
public class DeviceUtils {
    /**
     * 是否允许屏幕常亮，
     *
     * @param context activity上下文
     * @param isKeep  是否保持常亮
     */
    public static void keepScreenOn(Activity context, boolean isKeep) {
        if (isKeep) {
            //添加标致，从而允许屏幕常亮
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            //清除标致，从而允许屏幕熄灭
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
}
