package com.night.basecore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleUtil {
    /**
     * 表示连续多个空格
     */
    private static final Pattern p = Pattern.compile("\\s{2,}|\t");

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

    /**
     * 格式化要查询的单词的字符串,使其符合要求更容易查询到单词
     * @param text
     * @return
     */
    public static String getFormatQueryText(String text){
        Matcher m = p.matcher(text);
        return m.replaceAll(" ");
    }
}
