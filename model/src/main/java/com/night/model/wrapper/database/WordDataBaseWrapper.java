package com.night.model.wrapper.database;

import java.io.Serializable;

public class WordDataBaseWrapper implements Serializable {
    private String wordName;

    private String wordPhEn;

    private String wordPhEnMp3;

    private String wordPhAm;

    private String wordPhAmMp3;

    private String wordParts;

    private String wordMeans;

    private int    wordCollectState;

    public WordDataBaseWrapper(String wordName, String wordPhEn, String wordPhEnMp3, String wordPhAm, String wordPhAmMp3, String wordParts, String wordMeans, int wordCollectState) {
        this.wordName = wordName;
        this.wordPhEn = wordPhEn;
        this.wordPhEnMp3 = wordPhEnMp3;
        this.wordPhAm = wordPhAm;
        this.wordPhAmMp3 = wordPhAmMp3;
        this.wordParts = wordParts;
        this.wordMeans = wordMeans;
        this.wordCollectState = wordCollectState;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordPhEn() {
        return wordPhEn;
    }

    public void setWordPhEn(String wordPhEn) {
        this.wordPhEn = wordPhEn;
    }

    public String getWordPhEnMp3() {
        return wordPhEnMp3;
    }

    public void setWordPhEnMp3(String wordPhEnMp3) {
        this.wordPhEnMp3 = wordPhEnMp3;
    }

    public String getWordPhAm() {
        return wordPhAm;
    }

    public void setWordPhAm(String wordPhAm) {
        this.wordPhAm = wordPhAm;
    }

    public String getWordPhAmMp3() {
        return wordPhAmMp3;
    }

    public void setWordPhAmMp3(String wordPhAmMp3) {
        this.wordPhAmMp3 = wordPhAmMp3;
    }

    public String getWordParts() {
        return wordParts;
    }

    public void setWordParts(String wordParts) {
        this.wordParts = wordParts;
    }

    public String getWordMeans() {
        return wordMeans;
    }

    public void setWordMeans(String wordMeans) {
        this.wordMeans = wordMeans;
    }

    public int getWordCollectState() {
        return wordCollectState;
    }

    public void setWordCollectState(int wordCollectState) {
        this.wordCollectState = wordCollectState;
    }
}
