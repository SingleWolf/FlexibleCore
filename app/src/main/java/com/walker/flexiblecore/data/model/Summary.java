package com.walker.flexiblecore.data.model;

/**
 * @author Walker
 * @date on 2018/7/10 0010 下午 17:08
 * @email feitianwumu@163.com
 * @desc 简介
 */
public class Summary {
    private String title;
    private String componentName;
    private int actionFlag;

    public Summary() {
    }

    public Summary(String title, String componentName, int actionFlag) {
        this.title = title;
        this.componentName = componentName;
        this.actionFlag = actionFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(int actionFlag) {
        this.actionFlag = actionFlag;
    }
}
