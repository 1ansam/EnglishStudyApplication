package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.model.wrapper.BookWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BookActionImpl extends BaseSQLiteActionImpl implements BookAction {
    private static final String TABLE_NAME                   = "book";

    private static final String FIELD_BOOK_LIBRARY_NAME      = "book_library_name";

    private static final String FIELD_BOOK_CHINESE_NAME      = "book_chinese_name";

    private static final String FIELD_WORD_TOTAL_NUMBER      = "word_total_number";

    private static final String FIELD_WORD_SELECTED_NUMBER   = "word_selected_number";

    private static final String QUERY_BOOK_LIST              = "select * from " + TABLE_NAME;

    private static final String QUERY_BOOK_LIBRARY_NAME_LIST = "select " + FIELD_BOOK_LIBRARY_NAME + " from "
            + TABLE_NAME;

    private static final String QUERY_WORD_SELECTED_NUMBER_LIST = "select " + FIELD_BOOK_LIBRARY_NAME+","+FIELD_WORD_SELECTED_NUMBER + " from "
            + TABLE_NAME;

    private static final String UPDATE_WORD_SELECTED_NUMBER  = "update " + TABLE_NAME + " set "
            + FIELD_WORD_SELECTED_NUMBER + " =? where " + FIELD_BOOK_LIBRARY_NAME + " =?";

    public BookActionImpl(Context context) {
        super(context);
    }

    @Override
    public List<BookWrapper> getBookList() {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        List<BookWrapper> list = new ArrayList<>();
        cursor = database.rawQuery(QUERY_BOOK_LIST, null);
        while (cursor.moveToNext()) {
            list.add(new BookWrapper(cursor.getString(getFieldIndex(FIELD_BOOK_LIBRARY_NAME)),
                    cursor.getString(getFieldIndex(FIELD_BOOK_CHINESE_NAME)),
                    String.valueOf(cursor.getInt(getFieldIndex(FIELD_WORD_TOTAL_NUMBER))),
                    String.valueOf(cursor.getInt(getFieldIndex(FIELD_WORD_SELECTED_NUMBER)))));
        }
        close();
        return list;
    }

    @Override
    public void updateWordSelectedNumber(Map<String, Integer> map) {
        Map<String,Integer> wordSelectedNumberMap = getWordSelectedNumber();
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        Iterator<String> keySetIterator = map.keySet().iterator();
        while (keySetIterator.hasNext()) {
            String wordLibraryName = keySetIterator.next();
            database.execSQL(UPDATE_WORD_SELECTED_NUMBER, new Object[] { map.get(wordLibraryName)+wordSelectedNumberMap.get(wordLibraryName), wordLibraryName });
        }
        close();
    }

    @Override
    public List<String> getBookLibraryName() {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        List<String> list = new ArrayList<>();
        cursor = database.rawQuery(QUERY_BOOK_LIBRARY_NAME_LIST, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(getFieldIndex(FIELD_BOOK_LIBRARY_NAME)));
        }
        close();
        return list;
    }

    public Map<String,Integer> getWordSelectedNumber(){
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        Map<String,Integer> map = new HashMap<>();
        cursor =database.rawQuery(QUERY_WORD_SELECTED_NUMBER_LIST,null);
        while(cursor.moveToNext()){
            map.put(cursor.getString(getFieldIndex(FIELD_BOOK_LIBRARY_NAME)),cursor.getInt(getFieldIndex(FIELD_WORD_SELECTED_NUMBER)));
        }
        close();
        return map;
    }

}
