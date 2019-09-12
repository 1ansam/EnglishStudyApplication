package com.night.model.wrapper;

import java.util.List;

public class WordWrapper {
    private String wordName;

    private String wordPhEn;

    private String wordPhEnMp3;

    private String wordPhAm;

    private String wordPhAmMp3;

    private List<WordTranslationWrapper> wordTranslationWrapperList;

    public WordWrapper(String wordName, String wordPhEn, String wordPhEnMp3, String wordPhAm, String wordPhAmMp3, List<WordTranslationWrapper> wordTranslationWrapperList) {
        this.wordName = wordName;
        this.wordPhEn = wordPhEn;
        this.wordPhEnMp3 = wordPhEnMp3;
        this.wordPhAm = wordPhAm;
        this.wordPhAmMp3 = wordPhAmMp3;
        this.wordTranslationWrapperList = wordTranslationWrapperList;
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

    public List<WordTranslationWrapper> getWordTranslationWrapperList() {
        return wordTranslationWrapperList;
    }

    public void setWordTranslationWrapperList(List<WordTranslationWrapper> wordTranslationWrapperList) {
        this.wordTranslationWrapperList = wordTranslationWrapperList;
    }
}
