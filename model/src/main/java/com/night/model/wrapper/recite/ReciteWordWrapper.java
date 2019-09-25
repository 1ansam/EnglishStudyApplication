package com.night.model.wrapper.recite;

import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.common.WordWrapper;

import java.io.Serializable;

public class ReciteWordWrapper implements Serializable {
    /**
     * 原始单词数据
     */
    private WordWrapper wordWrapper;

    /**
     * 背记表中的单词状态
     */
    private CurrentWrapper currentWrapper;

    /**
     * 背记单词页 单词翻译可见状态
     */
    private int         translationVisibilityState;

    /**
     * 确定/不确定点击后标志状态  WordEnums
     */
    private int         wordReciteState;


    public ReciteWordWrapper(WordWrapper wordWrapper, CurrentWrapper currentWrapper, int translationVisibilityState, int wordReciteState) {
        this.wordWrapper = wordWrapper;
        this.currentWrapper = currentWrapper;
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

    public CurrentWrapper getCurrentWrapper() {
        return currentWrapper;
    }

    public void setCurrentWrapper(CurrentWrapper currentWrapper) {
        this.currentWrapper = currentWrapper;
    }
}
