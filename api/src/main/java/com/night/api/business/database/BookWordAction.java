package com.night.api.business.database;

import com.night.model.wrapper.BookWordWrapper;

import java.util.List;
import java.util.Map;

public interface BookWordAction {
    BookWordWrapper getBookWordWrapper(String bookLibraryName);

    void updateBookWordState(List<String> wordNameList, int state);

    Map<String,Integer> getWordExistInBookNumber(List<String> wordNameList);

}
