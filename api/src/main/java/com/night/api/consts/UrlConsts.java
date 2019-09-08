package com.night.api.consts;

public class UrlConsts {
    public static final String CIBA_GET_WORD = "https://dict-co.iciba.com/api/dictionary.php?w=%s&key=";

    public static final String KEY           = "74643EB04A8966177265CB4312E80609";

    public static String getQueryWordUrl(String wordName) {
        String url = String.format(CIBA_GET_WORD, wordName) + KEY;
        return url;
    }

}
