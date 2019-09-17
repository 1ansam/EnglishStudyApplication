package com.night.api.business.database;

import com.night.model.entity.WordEntity;
import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.database.WordWrapper;

import java.util.List;

public interface WordAction {
    void insertIntoWord(List<WordEntity> wordEntityList);

    boolean isContainedInTable(String wordName);

    List<Boolean> isContainedInTable(List<String> wordNameList);

    List<WordWrapper> getWordByName(List<String> wordNameList);

    List<WordWrapper> getWordByCurrent(List<CurrentWrapper> currentWrapperList);

    void updateWordCollectState(String wordName,int wordCollectState);

}
