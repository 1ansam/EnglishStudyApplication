package com.night.basecore.utils;

public class StyleUtil {
    /**
     * 格式化单词音标
     * @param wordPh
     * @param country 1英国 2美国
     * @return
     */
    public static String getWordPh(String wordPh, int country) {
        if (country == 1) {
            return "英/" + wordPh + "/";
        }
        if (country == 2) {
            return "美/" + wordPh + "/";
        }
        return "/" + wordPh + "/";
    }
}
