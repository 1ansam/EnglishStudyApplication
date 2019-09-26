package com.night.model.wrapper.database;

public class TestWrapper {
    private String wordName;

    private String aTranslation;

    private String bTranslation;

    private String cTranslation;

    private String dTranslation;

    private int    score;

    private String answer;

    public TestWrapper(String wordName, String aTranslation, String bTranslation, String cTranslation,
            String dTranslation, int score, String answer) {
        this.wordName = wordName;
        this.aTranslation = aTranslation;
        this.bTranslation = bTranslation;
        this.cTranslation = cTranslation;
        this.dTranslation = dTranslation;
        this.score = score;
        this.answer = answer;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getaTranslation() {
        return aTranslation;
    }

    public void setaTranslation(String aTranslation) {
        this.aTranslation = aTranslation;
    }

    public String getbTranslation() {
        return bTranslation;
    }

    public void setbTranslation(String bTranslation) {
        this.bTranslation = bTranslation;
    }

    public String getcTranslation() {
        return cTranslation;
    }

    public void setcTranslation(String cTranslation) {
        this.cTranslation = cTranslation;
    }

    public String getdTranslation() {
        return dTranslation;
    }

    public void setdTranslation(String dTranslation) {
        this.dTranslation = dTranslation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
