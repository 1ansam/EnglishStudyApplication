package com.night.api.business.database;

import com.night.model.entity.WordEntity;
import com.night.model.wrapper.WordWrapper;

import java.util.List;

public interface WordAction {
    void insertIntoWord(List<WordEntity> wordEntityList);

    boolean isContainedInTable(String wordName);

    List<Boolean> isContainedInTable(List<String> wordNameList);

    List<WordWrapper> getWord(List<String> wordNameList);

}
