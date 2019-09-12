package com.night.model.wrapper;

public class WordTranslationWrapper {
    private String wordPart;

    private String wordMean;

    public WordTranslationWrapper(String wordPart, String wordMean) {
        this.wordPart = wordPart;
        this.wordMean = wordMean;
    }

    public String getWordPart() {
        return wordPart;
    }

    public void setWordPart(String wordPart) {
        this.wordPart = wordPart;
    }

    public String getWordMean() {
        return wordMean;
    }

    public void setWordMean(String wordMean) {
        this.wordMean = wordMean;
    }
}
