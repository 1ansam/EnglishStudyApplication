package com.night.api.business.database;

import com.night.model.wrapper.database.LogWrapper;

import java.util.List;

public interface LogAction {
    /**
     * 得到某个日期的学习日志数据,也可用来判断是否存在该条数据
     * @param date
     * @return
     */
    LogWrapper getLogWrapper(String date);

    /**
     * 得到所有的学习日志数据
     * @return
     */
    List<LogWrapper> getLogWrapperList();

    /**
     * 插入一条学习日志数据到数据表
     * @param logWrapper
     */
    void insertIntoLog(LogWrapper logWrapper);

    /**
     * 更新对应date的当天学习新单词数,当天复习单词数,当天是否签到
     * @param date
     * @param number
     * @param field  当前改变的领域,使用LogActionImpl中静态变量
     */
    void update(String date,int number,String field);

    /**
     *更新对应数据
     * @param wrapper
     */
    void updateLogWrapper(LogWrapper wrapper);


}
