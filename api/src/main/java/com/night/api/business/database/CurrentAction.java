package com.night.api.business.database;

import java.util.List;

public interface CurrentAction {
    boolean insertIntoCurrent(List<String> wordNameList);

    List<String> getCurrentRecite();

    boolean updateNextDate(String wordName);

    boolean updateNextDate(List<String> wordNameList);

    boolean updateEndDate(String wordName);
}
