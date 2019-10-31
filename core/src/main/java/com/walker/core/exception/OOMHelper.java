package com.walker.core.exception;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Walker
 * @Date 2019-10-31 14:26
 * @Summary OOM场景观察辅助器
 */
public class OOMHelper {
    private static final String TAG = "OOMHelper";
    public static final float UNIT_M = 1024 * 1024;
    private static OOMHelper sInstance;
    private List<byte[]> mHeap = new ArrayList<>();
    private Runnable increaseFDRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/status"));
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable emptyRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private OOMHelper() {
    }

    public static OOMHelper get() {
        if (sInstance == null) {
            synchronized (OOMHelper.class) {
                if (sInstance == null) {
                    sInstance = new OOMHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取OOM相关统计详细信息
     *
     * @param pid 进程id
     * @return 详细信息
     */
    public String listStatisticsInfo(int pid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^^^^^ OOMHelper Statistics Start ^^^^^\r\n");
        stringBuilder.append(" fd count: ").append(getFdCount(pid)).append("\r\n");
        stringBuilder.append(getMemoryInfo());
        stringBuilder.append("# proc status #\r\n");
        stringBuilder.append(getProcStatus(pid)).append("\r\n");
        stringBuilder.append("# proc limit #\r\n");
        stringBuilder.append(getProcLimit(pid));
        stringBuilder.append("^^^^^  OOMHelper Statistics End  ^^^^^\r\n");
        return stringBuilder.toString();
    }

    /**
     * 获取OOM相关统计的大概信息
     *
     * @param pid 进程id
     * @return 大概信息
     */
    public String listStatisticsSummary(int pid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^^^^^ OOMHelper Statistics Start ^^^^^\r\n");
        stringBuilder.append(" fd count: ").append(getFdCount(pid)).append("\r\n");
        stringBuilder.append(getMemoryInfo());
        stringBuilder.append("^^^^^^  OOMHelper Statistics End  ^^^^^\r\n");
        return stringBuilder.toString();
    }


    /**
     * 获取文件描述符(fd)数目
     *
     * @param pid 进程id
     * @return 文件fd数
     */
    private int getFdCount(int pid) {
        String filePath = String.format("/proc/%d/fd", pid);
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
     * 获取内存使用信息
     *
     * @return 内存使用详情
     */
    public String getMemoryInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MaxJavaHeap : ").append(Runtime.getRuntime().maxMemory() / UNIT_M).append(" MB\r\n");
        stringBuilder.append("UsedJavaHeap  : ").append((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / UNIT_M).append(" MB\r\n");
        return stringBuilder.toString();
    }

    /**
     * 获取进程相关限制信息
     *
     * @param pid 进程id
     * @return 限制信息
     */
    private String getProcLimit(int pid) {
        String filePath = String.format("/proc/%d/limits", pid);
        return getFileContent(filePath);
    }

    /**
     * 获取进程当前状态信息
     *
     * @param pid 进程id
     * @return 当前状态信息
     */
    private String getProcStatus(int pid) {
        String filePath = String.format("/proc/%d/status", pid);
        return getFileContent(filePath);
    }

    /**
     * 获取文件内容
     *
     * @param path 文件路径
     * @return 文件内容
     */
    private String getFileContent(String path) {
        if (TextUtils.isEmpty(path)) {
            return "path is null";
        }
        String content;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
            StringBuilder stringBuilder = new StringBuilder();
            String s;
            while ((s = randomAccessFile.readLine()) != null) {
                stringBuilder.append(s).append("\r\n");
            }
            content = stringBuilder.toString();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
            content = e.getMessage();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            content = e.getMessage();
        }
        return content;
    }

    /**
     * 添加FD数目模拟测试
     *
     * @param count FD数
     */
    public void testIncreaseFD(int count) {
        if (count < 1) {
            return;
        }
        for (int i = 0; i < count; i++) {
            new Thread(increaseFDRunnable).start();
        }
    }

    /**
     * 增加线程数测试
     *
     * @param count 线程数
     */
    public void testIncreaseThread(int count) {
        if (count < 1) {
            return;
        }
        for (int i = 0; i < count; i++) {
            new Thread(emptyRunnable).start();
        }
    }

    /**
     * 占用堆内存测试
     *
     * @param count 数量
     */
    public void testAllocateJavaHeap(int count) {
        if (count < 1) {
            return;
        }
        byte[] bytes = new byte[count];
        mHeap.add(bytes);
    }

    /**
     * GC和释放堆内存测试
     */
    public void testGCAndDeallocate() {
        mHeap.clear();
        mHeap = new ArrayList<>();
        System.gc();
    }
}
