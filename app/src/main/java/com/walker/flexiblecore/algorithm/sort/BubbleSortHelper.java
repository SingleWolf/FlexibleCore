package com.walker.flexiblecore.algorithm.sort;

import android.util.Log;

import java.util.Arrays;

/**
 * @Author Walker
 * @Date 2020-03-04 16:57
 * @Summary 冒泡排序
 */
public class BubbleSortHelper {
    private static final String TAG = "BubbleSortHelper";

    private static BubbleSortHelper sInstance;

    private BubbleSortHelper() {
    }

    public static BubbleSortHelper get() {
        if (sInstance == null) {
            synchronized (BubbleSortHelper.class) {
                if (sInstance == null) {
                    sInstance = new BubbleSortHelper();
                }
            }
        }
        return sInstance;
    }

    /**
     * 普通版排序
     *
     * @param array 待排序数组
     */
    public int commonSort(int array[]) {
        int transactTime = 0;
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                transactTime++;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return transactTime;
    }

    /**
     * 优化版排序
     *
     * @param array 待排序数组
     */
    public int betterSort(int array[]) {
        int transactTime = 0;
        int temp;
        //记录最后一次交换到位置
        int lastExchangeIndex = 0;
        //无需数组的边界，每次只需比较到这里为止
        int sortBorder = array.length - 1;

        for (int i = 0; i < array.length - 1; i++) {
            //有序标识
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                transactTime++;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //有数据交换，则意味着目前数组是无序的
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            //如果数组已经是有序的，则退出排序循环
            if (isSorted) {
                break;
            }
        }
        return transactTime;
    }

    /**
     * 鸡尾酒排序
     *
     * @param array 带排序数组
     */
    public int cocktailSort(int array[]) {
        int transactTime = 0;
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            for (int j = i; j < array.length - i - 1; j++) {
                transactTime++;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，不是有序的
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            //偶数轮之前，将isSorted重置为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    transactTime++;
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    //有元素交换，不是有序的
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
        return transactTime;
    }

    /**
     * 优化版鸡尾酒排序
     *
     * @param array 带排序数组
     */
    public int betterCocktailSort(int array[]) {
        int transactTime = 0;
        int tmp = 0;
        //记录最后一次交换到位置
        int lastExchangeIndex = 0;
        //无需数组的边界，每次只需比较到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length / 2; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            for (int j = i; j < sortBorder; j++) {
                transactTime++;
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，不是有序的
                    isSorted = false;
                    lastExchangeIndex=j;
                }
            }
            sortBorder=lastExchangeIndex;
            if (isSorted) {
                break;
            }

            //偶数轮之前，将isSorted重置为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = sortBorder; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    transactTime++;
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    //有元素交换，不是有序的
                    isSorted = false;
                    lastExchangeIndex=j;
                }
            }
            sortBorder=lastExchangeIndex;

            if (isSorted) {
                break;
            }
        }
        return transactTime;
    }

    public void onTest() {
        Log.d(TAG, "####  冒泡排序  ####");
        int source_1[] = {2, 3, 4, 1, 7, 6, 5, 8, 9, 10};
        int source_2[] = {2, 3, 4, 1, 7, 6, 5, 8, 9, 10};
        int source_3[] = {2, 3, 4, 1, 7, 6, 5, 8, 9, 10};

        Log.d(TAG, "####  常规冒泡排序  ####");
        Log.d(TAG, "排序前：" + Arrays.toString(source_1));
        Log.d(TAG, "执行次数：" + commonSort(source_1));
        Log.d(TAG, "排序后：" + Arrays.toString(source_1));

        Log.d(TAG, "####  优化版冒泡排序  ####");
        Log.d(TAG, "排序前：" + Arrays.toString(source_2));
        Log.d(TAG, "执行次数：" + betterSort(source_2));
        Log.d(TAG, "排序后：" + Arrays.toString(source_2));

        Log.d(TAG, "####  鸡尾酒版冒泡排序  ####");
        Log.d(TAG, "排序前：" + Arrays.toString(source_3));
        Log.d(TAG, "执行次数：" + cocktailSort(source_3));
        Log.d(TAG, "排序后：" + Arrays.toString(source_3));

        Log.d(TAG, "####  突显鸡尾酒排序的优势  ####");
        int source_4[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
        int source_5[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
        int source_6[] = {2, 3, 4, 5, 6, 7, 8, 9, 10, 1};
        Log.d(TAG, "优化版排序前：" + Arrays.toString(source_4));
        Log.d(TAG, "优化版执行次数：" + betterSort(source_4));
        Log.d(TAG, "优化版排序后：" + Arrays.toString(source_4));
        Log.d(TAG, "鸡尾酒排序前：" + Arrays.toString(source_5));
        Log.d(TAG, "鸡尾酒执行次数：" + cocktailSort(source_5));
        Log.d(TAG, "鸡尾酒排序后：" + Arrays.toString(source_5));
        Log.d(TAG, "优化鸡尾酒排序前：" + Arrays.toString(source_6));
        Log.d(TAG, "优化鸡尾酒执行次数：" + betterCocktailSort(source_6));
        Log.d(TAG, "优化鸡尾酒排序后：" + Arrays.toString(source_6));
    }
}
