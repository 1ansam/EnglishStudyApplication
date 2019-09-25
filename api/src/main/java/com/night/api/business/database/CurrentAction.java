package com.night.api.business.database;

import com.night.model.wrapper.database.CurrentWrapper;

import java.util.List;

public interface CurrentAction {
    /**
     * 向数据表中插入选中的单词数据
     * @param wordNameList
     * @return
     */
    void insertIntoCurrent(List<String> wordNameList);

    /**
     * 从数据表中得到指定数量的需要背记的单词
     * @param targetNumber
     * @return
     */
    List<CurrentWrapper> getCurrentRecite(int targetNumber);

    /**
     * 更新单词的状态nextDate
     */
    void updateCurrentNextDate(String wordName,int expectedNextDate,int state);

    /**
     * 更新单词状态到结束状态
     * @param wordName
     */
    void updateCurrentEnd(String wordName);

    /**
     * 更新单词状态
     * @param wordName
     * @param state
     */
    void updateCurrentState(String wordName, int state);

    /**
     * 更新单词数据
     * @param wrapper
     */
    void updateCurrent(CurrentWrapper wrapper);

    /**
     * 单词集合是否在表中存在
     * @param wordNameList
     * @return
     */
    List<Boolean> isContainedInTable(List<String> wordNameList);

    /**
     * 单个单词是否在表中存在
     * @param wordName
     * @return
     */
    boolean isContainedInTable(String wordName);

}
