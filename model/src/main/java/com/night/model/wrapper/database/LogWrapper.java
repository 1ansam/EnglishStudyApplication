package com.night.model.wrapper.database;

import java.io.Serializable;

public class LogWrapper implements Serializable {
    private String date;

    private int newNumber;

    private int reviseNumber;

    private int sign;

    public LogWrapper(String date, int newNumber, int reviseNumber, int sign) {
        this.date = date;
        this.newNumber = newNumber;
        this.reviseNumber = reviseNumber;
        this.sign = sign;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(int newNumber) {
        this.newNumber = newNumber;
    }

    public int getReviseNumber() {
        return reviseNumber;
    }

    public void setReviseNumber(int reviseNumber) {
        this.reviseNumber = reviseNumber;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
