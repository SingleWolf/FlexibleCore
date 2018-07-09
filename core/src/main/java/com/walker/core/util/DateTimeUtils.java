package com.walker.core.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Walker
 * @date on 2018/4/17 0017 上午 10:53
 * @email feitianwumu@163.com
 * @desc 时间日期辅助类
 */
public class DateTimeUtils {
    private static final String TAG = "DateTimeUtils";

    /**
     * 获取通用的时间（精确到秒）
     *
     * @return 通用的时间
     */
    public static String getNormalDate() {
        String time = "";
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            time = sf.format(date);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return time;
    }

    /**
     * 获取当前时间点
     *
     * @return 当前时间的long型值
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
