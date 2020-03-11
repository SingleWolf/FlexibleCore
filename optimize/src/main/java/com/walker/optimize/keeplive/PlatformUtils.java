package com.walker.optimize.keeplive;

import android.os.Build;

/**
 * @Author Walker
 * @Date 2020-03-05 15:10
 * @Summary 厂商平台管理
 */
public class PlatformUtils {
    public static class ID {
        /**
         * 默认
         */
        public static final int DEFAULT = 1;
        /**
         * 华为
         */
        public static final int HUAWEI = 2;
        /**
         * 小米
         */
        public static final int XIAOMI = 3;
        /**
         * OPPO
         */
        public static final int OPPO = 4;
        /**
         * VIVO
         */
        public static final int VIVO = 5;
        /**
         * 三星
         */
        public static final int SAMSUNG = 6;
        /**
         * 魅族
         */
        public static final int MEIZU = 7;
    }

    public static int distinguishPlatform() {
        if (isHuawei()) {
            return ID.HUAWEI;
        } else if (isXiaomi()) {
            return ID.XIAOMI;
        } else if (isOPPO()) {
            return ID.OPPO;
        } else if (isVIVO()) {
            return ID.VIVO;
        } else if (isSamsung()) {
            return ID.SAMSUNG;
        } else if (isMeizu()) {
            return ID.MEIZU;
        } else {
            return ID.DEFAULT;
        }
    }

    public static boolean isHuawei() {
        if (Build.BRAND == null) {
            return false;
        } else {
            return Build.BRAND.toLowerCase().equals("huawei") || Build.BRAND.toLowerCase().equals("honor");
        }
    }

    public static boolean isXiaomi() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("xiaomi");
    }

    public static boolean isOPPO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("oppo");
    }

    public static boolean isVIVO() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("vivo");
    }

    public static boolean isSamsung() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("samsung");
    }

    public static boolean isMeizu() {
        return Build.BRAND != null && Build.BRAND.toLowerCase().equals("meizu");
    }
}
