package com.night.api.business.database;

import com.night.model.wrapper.database.CurrentWrapper;

import java.util.List;

public interface CurrentAction {
    boolean insertIntoCurrent(List<String> wordNameList);

    List<CurrentWrapper> getCurrentRecite(int targetNumber);

    int getReviseNumber();

    boolean updateNextDate(String wordName);

    boolean updateNextDate(List<String> wordNameList);

    boolean updateEndDate(String wordName);
}
