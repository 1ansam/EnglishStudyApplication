package com.night.api.consts;

public class UrlConsts {
    /**
     * 获取对应单词数据url
     */
    private static final String CIBA_GET_WORD = "https://dict-co.iciba.com/api/dictionary.php?type=json&w=%s&key=";

    /**
     * 回去对应单词数据url密钥
     */
    private static final String KEY           = "74643EB04A8966177265CB4312E80609";

    public static final String  HTTPS         = "https://";

    /**
     * 获取完成获取对应单词数据api地址
     * @param wordName
     * @return
     */
    public static String getQueryWordUrl(String wordName) {
        String url = String.format(CIBA_GET_WORD, wordName) + KEY;
        return url;
    }

}
