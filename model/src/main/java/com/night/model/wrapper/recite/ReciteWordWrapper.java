package com.night.model.wrapper.recite;

import com.night.model.wrapper.database.WordWrapper;

public class ReciteWordWrapper {
    /**
     * 原始单词数据
     */
    private WordWrapper wordWrapper;

    /**
     * 背记单词页 单词翻译可见状态
     */
    private int         translationVisibilityState;

    /**
     * 确定/不确定点击后标志状态
     */
    private int         wordReciteState;

    public ReciteWordWrapper(WordWrapper wordWrapper, int translationVisibilityState, int wordReciteState) {
        this.wordWrapper = wordWrapper;
        this.translationVisibilityState = translationVisibilityState;
        this.wordReciteState = wordReciteState;
    }

    public WordWrapper getWordWrapper() {
        return wordWrapper;
    }

    public void setWordWrapper(WordWrapper wordWrapper) {
        this.wordWrapper = wordWrapper;
    }

    public int getTranslationVisibilityState() {
        return translationVisibilityState;
    }

    public void setTranslationVisibilityState(int translationVisibilityState) {
        this.translationVisibilityState = translationVisibilityState;
    }

    public int getWordReciteState() {
        return wordReciteState;
    }

    public void setWordReciteState(int wordReciteState) {
        this.wordReciteState = wordReciteState;
    }

}
