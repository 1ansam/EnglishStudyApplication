package com.night.api.business.okhttp;

import java.util.List;

public interface WordApiAction {
    void getWordEntityList(List<String> wordNameList);
}
