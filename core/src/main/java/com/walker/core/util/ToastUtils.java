package com.walker.core.util;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author Walker
 * @date on 2018/3/30 0030 下午 14:13
 * @email feitianwumu@163.com
 * @desc Toast工具类
 */
public class ToastUtils {
    private static Context sContext;

    /**
     * 初始化，需再application中设置
     *
     * @param context application级别的context
     */
    public static void init(Context context) {
        if ((context instanceof Application) == false) {
            return;
        }
        if (sContext == null) {
            sContext = context;
        }
    }

    /**
     * @param msg 消息
     */
    public static void showShort(String msg) {
        if (sContext == null) {
            return;
        }
        Toast.makeText(sContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msgRes 消息id
     */
    public static void showShort(int msgRes) {
        if (sContext == null) {
            return;
        }
        Toast.makeText(sContext, msgRes, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg 消息
     */
    public static void showCenterShort(String msg) {
        if (sContext == null) {
            return;
        }
        Toast toast = Toast.makeText(sContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @param msgRes 消息id
     */
    public static void showCenterShort(int msgRes) {
        if (sContext == null) {
            return;
        }
        Toast toast = new Toast(sContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(msgRes);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * @param msg 消息
     */
    public static void showLong(String msg) {
        if (sContext == null) {
            return;
        }
        Toast.makeText(sContext, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * @param msgRes 消息id
     */
    public static void showLong(int msgRes) {
        if (sContext == null) {
            return;
        }
        Toast.makeText(sContext, msgRes, Toast.LENGTH_LONG).show();
    }
}
