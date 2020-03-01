package com.walker.optimize.analyzer;

import android.util.SparseArray;

/**
 * @Author Walker
 * @Date 2019-12-30 10:58
 * @Summary 耗时统计管理类
 */
public class TimeAnalyzerMgr {
    private static TimeAnalyzerMgr sInstance = null;
    private SparseArray<TimeAnalyzer> mTimeAnalyzerMap;

    public synchronized static TimeAnalyzerMgr getInstance() {
        if (sInstance == null) {
            synchronized (TimeAnalyzerMgr.class) {
                if (sInstance == null) {
                    sInstance = new TimeAnalyzerMgr();
                }
            }
        }
        return sInstance;
    }

    public TimeAnalyzerMgr() {
        this.mTimeAnalyzerMap = new SparseArray<>();
    }

    /**
     * 初始化打点模块
     */
    public void startTimeAnalyzer(int id) {
        if (mTimeAnalyzerMap.get(id) != null) {
            mTimeAnalyzerMap.remove(id);
        }
        getTimeAnalyzer(id).startAnalyzer();
    }

    /**
     * 获取打点器
     */
    public TimeAnalyzer getTimeAnalyzer(int id) {
        TimeAnalyzer analyzer = mTimeAnalyzerMap.get(id);
        if (analyzer == null) {
            analyzer = new TimeAnalyzer(id);
            mTimeAnalyzerMap.put(id, analyzer);
        }
        return analyzer;
    }
}
