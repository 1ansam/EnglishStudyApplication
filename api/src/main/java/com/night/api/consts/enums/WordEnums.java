package com.night.api.consts.enums;

public class WordEnums {
    /**
     * 单词既没有被点击确定，也没有被点击不确定
     */
    public static final int NULL_SURE = 0;

    /**
     * 单词被点击确定
     */
    public static final int SURE = 1;

    /**
     * 单词被点击不确定
     */
    public static final int NOT_SURE = 2;

    /**
     * 单词没有被收藏
     */
    public static final int NOT_COLLECTED = 0;

    /**
     * 单词被收藏了
     */
    public static final int COLLECTED = 1;

    /**
     * 只显示英式发音
     */
    public static final int WORD_PH_EN=0;

    /**
     * 只显示美式发音
     */
    public static final int WORD_PH_AM=1;

    /**
     * 单词第一次被写入数据库表且没有被背记过
     */
    public static final int     STATE_FIRST              = 0;

    /**
     * 单词还在背记周期过程中
     */
    public static final int     STATE_ING                = 1;

    /**
     * 单词在背记结束状态
     */
    public static final int     STATE_END                = 2;

}
