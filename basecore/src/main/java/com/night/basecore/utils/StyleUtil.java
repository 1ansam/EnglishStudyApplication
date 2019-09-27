package com.night.basecore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StyleUtil {
    /**
     * 表示连续多个空格
     */
    public static final Pattern CONTINUOUS_SPACE = Pattern.compile("\\s{2,}|\t");

    /**
     * 全部为字母
     */
    public static final Pattern ALL_LETTER=Pattern.compile("^[a-zA-Z]+$");

    private static final String AM="am";

    private static final String EN="en";

    /**
     * 格式化单词音标
     * @param wordPh
     * @param country EN英国 AM美国
     * @return
     */
    public static String getWordPh(String wordPh, String country) {
        if (EN.equals(country)) {
            return "英/" + wordPh + "/";
        }
        if (AM.equals(country)) {
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
        Matcher m = CONTINUOUS_SPACE.matcher(text);
        return m.replaceAll(" ");
    }

    /**
     * 判断是否除了空格以外都是字母
     * @param str
     * @return
     */
    public static boolean isAllLetter(String str){
        String tempStr=str;
        tempStr=tempStr.replace(" ","");
        Matcher matcher =ALL_LETTER.matcher(tempStr);
        return matcher.matches();
    }

}
