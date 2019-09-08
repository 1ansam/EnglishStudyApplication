package com.night.basecore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StyleCheckUtil {

    /**
     * 校验身份证格式是否正确
     *
     * @param content
     * @return
     */
    public static boolean checkIdentifyId(String content) {
        Pattern pat = Pattern.compile("^[0-9X]+$");
        Matcher mat = pat.matcher(content);
        if (!mat.find()) {
            return false;
        }
        int len = content.length();
        if (len == 15 || len == 18) {
            return true;
        }
        return false;
    }
}
