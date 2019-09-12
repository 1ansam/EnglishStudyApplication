package com.night.model.wrapper;

public class BookWrapper {
    private String bookLibraryName;

    private String bookChineseName;

    private String wordTotalNumber;

    private String wordSelectedNumber;

    public BookWrapper(String bookLibraryName, String bookChineseName, String wordTotalNumber,
            String wordSelectedNumber) {
        this.bookLibraryName = bookLibraryName;
        this.bookChineseName = bookChineseName;
        this.wordTotalNumber = wordTotalNumber;
        this.wordSelectedNumber = wordSelectedNumber;
    }

    public String getBookLibraryName() {
        return bookLibraryName;
    }

    public void setBookLibraryName(String bookLibraryName) {
        this.bookLibraryName = bookLibraryName;
    }

    public String getBookChineseName() {
        return bookChineseName;
    }

    public void setBookChineseName(String bookChineseName) {
        this.bookChineseName = bookChineseName;
    }

    public String getWordTotalNumber() {
        return wordTotalNumber;
    }

    public void setWordTotalNumber(String wordTotalNumber) {
        this.wordTotalNumber = wordTotalNumber;
    }

    public String getWordSelectedNumber() {
        return wordSelectedNumber;
    }

    public void setWordSelectedNumber(String wordSelectedNumber) {
        this.wordSelectedNumber = wordSelectedNumber;
    }
}
