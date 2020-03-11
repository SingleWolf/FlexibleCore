package com.walker.flexiblecore.algorithm.tree;

import android.util.Log;

/**
 * @Author Walker
 * @Date 2020-03-03 15:11
 * @Summary 优先队列辅助类
 */
public class PriorityQueueHelper {
    private static final String TAG = "PriorityQueueHelper";

    private static PriorityQueueHelper sInstance;

    private PriorityQueueHelper() {
    }

    public static PriorityQueueHelper get() {
        if (sInstance == null) {
            synchronized (PriorityQueueHelper.class) {
                if (sInstance == null) {
                    sInstance = new PriorityQueueHelper();
                }
            }
        }
        return sInstance;
    }

    public void onTest() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        priorityQueue.enQueue(1);
        Log.d(TAG, "#### 优先队列 ####");
        try {
            Log.d(TAG, "出队元素：" + priorityQueue.deQueue());
            Log.d(TAG, "出队元素：" + priorityQueue.deQueue());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }

    }
}
