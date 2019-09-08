package com.night.business.database;

import com.night.business.wrapper.database.BookWrapper;

import java.util.List;

public interface BookAction {

    List<BookWrapper> getBookList();

}
