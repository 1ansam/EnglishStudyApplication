package com.night.model.wrapper.database;

import java.io.Serializable;

public class CurrentWrapper implements Serializable {
    private String wordName;

    private int    firstDate;

    private int    nextDate;

    private int    endDate;

    private int    state;

    public CurrentWrapper(String wordName, int firstDate, int nextDate, int state) {
        this.wordName = wordName;
        this.firstDate = firstDate;
        this.nextDate = nextDate;
        this.state = state;
    }

    public CurrentWrapper(String wordName, int firstDate, int nextDate, int endDate, int state) {
        this.wordName = wordName;
        this.firstDate = firstDate;
        this.nextDate = nextDate;
        this.endDate = endDate;
        this.state = state;
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

    public int getNextDate() {
        return nextDate;
    }

    public void setNextDate(int nextDate) {
        this.nextDate = nextDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
