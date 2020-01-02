package com.walker.optimize.analyzer;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;

import com.facebook.device.yearclass.YearClass;

import java.io.File;

/**
 * @Author Walker
 * @Date 2019-12-18 20:40
 * @Summary 指标分析
 */
public class IndexAnalyzer {
    private static final float UNIT_M = 1024 * 1024;

    /**
     * 获取关键信息
     */
    public String listKeyInfo(Context context) {
        StringBuilder info = new StringBuilder();
        info.append(" \n");
        info.append(" -------------------- Device Run Info --------------------\n");
        info.append("|\n");
        info.append(String.format("|    CPU Count   :%d\n",getCPUCount()));
        info.append("|\n");
        info.append(String.format("|    Year Level  :%d\n",getDeviceYear(context)));
        info.append("|\n");
        info.append(String.format("|    Max  Memory :%d M\n",getMaxMemory()));
        info.append(String.format("|    Used Memory :%d M\n",getUsedMemory()));
        info.append("|\n");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            info.append(String.format("|    RuntimeStats:%s\n", Debug.getRuntimeStats().toString()));
            info.append("|\n");
        }
        info.append(String.format("|    PSS Used    :%d\n", Debug.getPss()));
        info.append("|\n");
        info.append(String.format("|    File FD     :%d\n", getFdCount()));
        info.append("|\n");
        info.append(" ---------------------------------------------------------\n");
        return info.toString();
    }

    /**
     * 获取CPU数量
     *
     * @return CPU数量
     */
    private int getCPUCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取文件描述符(fd)数目
     *
     * @return 文件fd数
     */
    private int getFdCount() {
        String filePath = String.format("/proc/%d/fd", Process.myPid());
        File fdFile = new File(filePath);
        File[] files = fdFile.listFiles();
        int fdCount;
        if (files == null) {
            fdCount = -1;
        } else {
            fdCount = files.length;
        }
        return fdCount;
    }

    /**
     * 获取最大可用内存
     * @return 最大可用内存
     */
    private int getMaxMemory() {
        int size = (int) (Runtime.getRuntime().maxMemory() / UNIT_M);
        return size;
    }

    /**
     * 获取已用内存大小
     * @return 已用内存大小
     */
    private int getUsedMemory() {
        int size = (int) ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / UNIT_M);
        return size;
    }

    /**
     * 获取机器年份
     * @return 机器年份
     */
    private int getDeviceYear(Context context){
        int year = YearClass.get(context);
        return year;
    }
}
