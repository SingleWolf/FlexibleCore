package com.walker.core.util;

/**
 * @author walker
 * @date 2019/1/25
 * @desc 点击辅助器
 */
public class ClickUtils {

    public static final long INTERVAL = 600L;

    private static long sLastClickTime = 0L;

    /**
     * 是否为快速点击
     *
     * @return 快速点击的标识
     */
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - sLastClickTime > 0 && time - sLastClickTime < INTERVAL) {
            return true;
        } else {
            sLastClickTime = time;
            return false;
        }
    }
}
