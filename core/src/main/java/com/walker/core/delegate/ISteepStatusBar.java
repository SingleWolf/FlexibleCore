package com.walker.core.delegate;

/**
 * @author Walker
 * @e-mail feitianwumu@163.com
 * @date on 2018/9/13
 * @summary 沉浸状态栏接口
 */
 interface ISteepStatusBar {

    /**
     * 是否设置透明主题色
     *
     * @return boolean
     */
    boolean isTranslucentTheme();

    /**
     * 是否填充占位view
     *
     * @return boolean
     */
    boolean isFillStatusBar();

    /**
     * 设置填充view的颜色
     *
     * @return 颜色值
     */
    int setFillColor();

    /**
     * 设置状态栏透明
     */
    void setTranslucentTheme();

    /**
     * 添加状态栏占位视图
     *
     * @param color 颜色值
     */
    void addStatusViewWithColor(int color);
}
