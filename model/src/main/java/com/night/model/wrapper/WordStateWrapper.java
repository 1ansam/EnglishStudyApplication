package com.night.model.wrapper;

public class WordStateWrapper {
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
