package com.walker.optimize.keeplive;

import android.content.Context;

import com.walker.optimize.keeplive.holder.DefaultKeepLiver;
import com.walker.optimize.keeplive.holder.HuaweiKeepLiver;
import com.walker.optimize.keeplive.holder.MeizuKeepLiver;
import com.walker.optimize.keeplive.holder.OPPOKeepLiver;
import com.walker.optimize.keeplive.holder.SamsungKeepLiver;
import com.walker.optimize.keeplive.holder.VIVOKeepLiver;
import com.walker.optimize.keeplive.holder.XiaomiKeepLiver;

/**
 * @Author Walker
 * @Date 2020-03-05 15:13
 * @Summary 保活器工厂
 */
public class KeepLiveFactory {
    public static IKeepLive create(Context context) {
        int platform = PlatformUtils.distinguishPlatform();
        IKeepLive keepLiver;
        switch (platform) {
            case PlatformUtils.ID.HUAWEI:
                keepLiver = new HuaweiKeepLiver(context);
                break;
            case PlatformUtils.ID.XIAOMI:
                keepLiver = new XiaomiKeepLiver(context);
                break;
            case PlatformUtils.ID.OPPO:
                keepLiver = new OPPOKeepLiver(context);
                break;
            case PlatformUtils.ID.VIVO:
                keepLiver = new VIVOKeepLiver(context);
                break;
            case PlatformUtils.ID.SAMSUNG:
                keepLiver = new SamsungKeepLiver(context);
                break;
            case PlatformUtils.ID.MEIZU:
                keepLiver = new MeizuKeepLiver(context);
                break;
            default:
                keepLiver = new DefaultKeepLiver(context);
                break;
        }
        return keepLiver;
    }
}
