package com.walker.optimize.analyzer;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Walker
 * @Date 2019-12-30 10:53
 * @Summary 耗时监视器对象，记录整个过程的耗时情况
 */
public class TimeAnalyzer {
    private final String TAG = "TimeAnalyzer";
    private int mAnalyzerId = -1;

    /**
     * 保存一个耗时统计模块的各种耗时，tag对应某一个阶段的时间
     */
    private HashMap<String, Long> mTimeTag = new HashMap<>();
    private long mStartTime = 0;

    public TimeAnalyzer(int analyzerId) {
        Log.d(TAG, "init TimeAnalyzer id: " + analyzerId);
        mAnalyzerId = analyzerId;
    }

    public int getAnalyzerId() {
        return mAnalyzerId;
    }

    public void startAnalyzer() {
        // 每次重新启动都把前面的数据清除，避免统计错误的数据
        if (mTimeTag.size() > 0) {
            mTimeTag.clear();
        }
        mStartTime = System.currentTimeMillis();
    }

    /**
     * 每打一次点，记录某个tag的耗时
     */
    public void recordingTimeTag(String tag) {
        // 若保存过相同的tag，先清除
        if (mTimeTag.get(tag) != null) {
            mTimeTag.remove(tag);
        }
        long time = System.currentTimeMillis() - mStartTime;
        Log.d(TAG, tag + ": " + time);
        mTimeTag.put(tag, time);
    }

    public void end(String tag, boolean writeLog,OnTimeAnalyzerListener listener) {
        recordingTimeTag(tag);
        end(writeLog,listener);
    }

    private void end(boolean writeLog,OnTimeAnalyzerListener listener) {
        if (writeLog) {
            //写入到本地文件
            if(listener!=null){
                StringBuilder stringBuilder=new StringBuilder();
                for(Map.Entry<String,Long> entry:mTimeTag.entrySet()){
                    stringBuilder.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
                }
                listener.onTimeInfo(stringBuilder.toString());
            }
        }
    }

    public HashMap<String, Long> getTimeTags() {
        return mTimeTag;
    }

    public interface OnTimeAnalyzerListener{
        void onTimeInfo(String info);
    }
}
