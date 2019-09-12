package com.night.model.wrapper;

import java.util.ArrayList;
import java.util.List;

public class BookWordWrapper {
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
