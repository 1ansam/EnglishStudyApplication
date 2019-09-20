package com.night.model.wrapper.baseui;


import java.io.Serializable;

/**
 * UI界面基本类
 */
public class CommonFunctionWrapper implements Serializable {
    /**功能图标字母id*/
    private int titleIconId;

    /**功能名称id*/
    private int titleNameId;

    public CommonFunctionWrapper(int titleIconId, int titleNameId) {
        this.titleIconId = titleIconId;
        this.titleNameId = titleNameId;
    }

    public int getTitleIconId() {
        return titleIconId;
    }

    public void setTitleIconId(int titleIconId) {
        this.titleIconId = titleIconId;
    }

    public int getTitleNameId() {
        return titleNameId;
    }

    public void setTitleNameId(int titleNameId) {
        this.titleNameId = titleNameId;
    }
}
