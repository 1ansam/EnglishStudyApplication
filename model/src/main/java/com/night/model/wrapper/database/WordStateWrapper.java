package com.night.model.wrapper.database;


import java.io.Serializable;

/**
 * BookWordWrapper类使用
 * 对应book_library_name表
 */
public class WordStateWrapper implements Serializable {
    private String wordName;

    private boolean isSelected;

    public WordStateWrapper(String wordName, boolean isSelected) {
        this.wordName = wordName;
        this.isSelected = isSelected;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void reverseSelectedState(){
        setSelected(!isSelected);
    }
}
