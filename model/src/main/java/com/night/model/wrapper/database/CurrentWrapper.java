package com.night.model.wrapper.database;

import java.io.Serializable;

public class CurrentWrapper implements Serializable {
    private String wordName;

    private int firstDate;

    private Integer nextDate;

    private Integer endDate;

    private int state;

    public CurrentWrapper(String wordName, int firstDate, Integer nextDate) {
        this.wordName = wordName;
        this.firstDate = firstDate;
        this.nextDate = nextDate;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public int getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(int firstDate) {
        this.firstDate = firstDate;
    }

    public Integer getNextDate() {
        return nextDate;
    }

    public void setNextDate(Integer nextDate) {
        this.nextDate = nextDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
