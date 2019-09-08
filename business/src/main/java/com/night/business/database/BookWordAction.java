package com.night.business.database;

import com.night.business.wrapper.database.BookWordWrapper;

public interface BookWordAction {
    BookWordWrapper getBookWordWrapper(String bookLibraryName);

}
