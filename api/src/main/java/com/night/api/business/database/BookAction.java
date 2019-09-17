package com.night.api.business.database;

import com.night.model.wrapper.database.BookWrapper;

import java.util.List;
import java.util.Map;

public interface BookAction {

    List<BookWrapper> getBookList();

    void updateWordSelectedNumber(Map<String,Integer> map);

    List<String> getBookLibraryName();

}
