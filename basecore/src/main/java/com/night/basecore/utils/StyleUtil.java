package com.night.basecore.utils;

public class StyleUtil {
    /**
     * 格式化单词音标
     * @param wordPh
     * @param country en英国 am美国
     * @return
     */
    public static String getWordPh(String wordPh, String country) {
        if ("en".equals(country)) {
            return "英/" + wordPh + "/";
        }
        if ("am".equals(country)) {
            return "美/" + wordPh + "/";
        }
        return "/" + wordPh + "/";
    }
}
