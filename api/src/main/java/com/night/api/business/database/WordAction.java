package com.night.api.business.database;

import com.night.model.wrapper.Common.WordWrapper;
import com.night.model.wrapper.database.WordDataBaseWrapper;

import java.util.List;

public interface WordAction {
    /**
     * 将单词集合数据写入到数据表
     * @param wordList
     */
    void insertIntoWord(List<WordDataBaseWrapper> wordList);

    /**
     * 将单个单词数据写入到数据表
     * @param wordDataBaseWrapper
     */
    void insertIntoWord(WordDataBaseWrapper wordDataBaseWrapper);

    /**
     * 查询单个单词是否已经存在于数据表中
     * @param wordName
     * @return
     */
    boolean isContainedInTable(String wordName);

    /**
     * 查询单词集合是否已经存在于数据表中
     * @param wordNameList
     * @return
     */
    List<Boolean> isContainedInTable(List<String> wordNameList);

    /**
     * 通过单词名集合查找单词本地数据
     * @param wordNameList
     * @return
     */
    List<WordWrapper> getWordByName(List<String> wordNameList);

    /**
     * 查询单个单词本地数据
     * @param wordName
     * @return
     */
    WordWrapper getWordByName(String wordName);


    /**
     * 更新单词收藏状态
     * @param wordName
     * @param wordCollectState
     */
    void updateWordCollectState(String wordName,int wordCollectState);

}
