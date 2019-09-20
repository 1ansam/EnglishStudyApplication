package com.night.model.wrapper.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 对应book_library_name本地数据库表
 */
public class BookWordWrapper implements Serializable {
    private List<WordStateWrapper> unSelectedWordList = new ArrayList<>();

    private List<WordStateWrapper> selectedWordList=new ArrayList<>();

    public List<WordStateWrapper> getUnSelectedWordList() {
        return unSelectedWordList;
    }

    public void setUnSelectedWordList(List<WordStateWrapper> unSelectedWordList) {
        this.unSelectedWordList = unSelectedWordList;
    }

    public List<WordStateWrapper> getSelectedWordList() {
        return selectedWordList;
    }

    public void setSelectedWordList(List<WordStateWrapper> selectedWordList) {
        this.selectedWordList = selectedWordList;
    }
}
