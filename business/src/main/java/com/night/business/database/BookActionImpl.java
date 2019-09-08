package com.night.business.database;

import android.content.Context;
import android.database.Cursor;

import com.night.business.base.BaseActionImpl;
import com.night.business.wrapper.database.BookWrapper;

import java.util.ArrayList;
import java.util.List;

public class BookActionImpl extends BaseActionImpl implements BookAction {
    private static final String BOOK_LIBRARY_NAME    = "book_library_name";

    private static final String BOOK_CHINESE_NAME    = "book_chinese_name";

    private static final String WORD_TOTAL_NUMBER    = "word_total_number";

    private static final String WORD_SELECTED_NUMBER = "word_selected_number";

    private int                 BOOK_LIBRARY_NAME_INDEX;

    private int                 BOOK_CHINESE_NAME_INDEX;

    private int                 WORD_TOTAL_NUMBER_INDEX;

    private int                 WORD_SELECTED_NUMBER_INDEX;

    private static final String QUERY_BOOK_LIST      = "select * from book";

    public BookActionImpl(Context context) {
        super(context);
        initIndex();
    }

    @Override
    public List<BookWrapper> getBookList() {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        List<BookWrapper> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(QUERY_BOOK_LIST, null);
        while (cursor.moveToNext()) {
            list.add(new BookWrapper(cursor.getString(BOOK_LIBRARY_NAME_INDEX),
                    cursor.getString(BOOK_CHINESE_NAME_INDEX), String.valueOf(cursor.getInt(WORD_TOTAL_NUMBER_INDEX)),
                    String.valueOf(cursor.getInt(WORD_SELECTED_NUMBER_INDEX))));
        }
        close(cursor,database,openHelper);
        return list;
    }

    private void initIndex() {
        database=openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(QUERY_BOOK_LIST, null);
        BOOK_LIBRARY_NAME_INDEX = cursor.getColumnIndex(BOOK_LIBRARY_NAME);
        BOOK_CHINESE_NAME_INDEX = cursor.getColumnIndex(BOOK_CHINESE_NAME);
        WORD_TOTAL_NUMBER_INDEX = cursor.getColumnIndex(WORD_TOTAL_NUMBER);
        WORD_SELECTED_NUMBER_INDEX = cursor.getColumnIndex(WORD_SELECTED_NUMBER);
        cursor.close();
        database.close();
        openHelper.close();
    }
}
