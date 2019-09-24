package com.night.api.business.okhttp;

import java.util.List;

public interface WordApiAction {

    /**
     * 从金山词霸Api获取所有单词数据并写入到数据表中
     * @param wordNameList
     */
    void getWordEntityList(List<String> wordNameList);

    void setWordApiListener(WordApiListener wordApiListener);

    void getWordEntity(String wordName);

    public interface WordApiListener<T>{
        /**
         * 当所有数据都写入到数据表中后的处理动作
         */
        void doAfterSuccess(T data);

        /**
         * 网络无连接的处理动作
         */
        void doAfterFailed(T data);

    }
}
