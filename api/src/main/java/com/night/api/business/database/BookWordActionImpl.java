package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.model.wrapper.database.BookWordWrapper;
import com.night.model.wrapper.database.WordStateWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookWordActionImpl extends BaseSQLiteActionImpl implements BookWordAction {
    private List<String>        tableNameList;

    private static final String FIELD_WORD_NAME        = "word_name";

    private static final String FIELD_WORD_STATE       = "word_state";

    public static final int     WORD_STATE_SELECYED    = 1;

    public static final int     WORD_STATE_UN_SELECTED = 0;

    private static final String QUERY_BOOK_WORD_NAME   = "select " + FIELD_WORD_NAME + " from %s ";

    private static final String QUERY_BOOK_WORD_LIST   = "select * from %s";

    private static final String UPDATE_BOOK_WORD_STATE = "update %s set " + FIELD_WORD_STATE + " = ? where "
            + FIELD_WORD_NAME + " = ?";

    public BookWordActionImpl(Context context) {
        super(context);
        tableNameList = new BookActionImpl(context).getBookLibraryName();
    }

    @Override
    public BookWordWrapper getBookWordWrapper(String bookLibraryName) {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        BookWordWrapper bookWordWrapper = new BookWordWrapper();
        String sql = String.format(QUERY_BOOK_WORD_LIST, bookLibraryName);
        cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int wordState = cursor.getInt(getFieldIndex(FIELD_WORD_STATE));
            if (wordState == WORD_STATE_UN_SELECTED) {
                bookWordWrapper.getUnSelectedWordList()
                        .add(new WordStateWrapper(cursor.getString(getFieldIndex(FIELD_WORD_NAME)), false));
            } else {
                bookWordWrapper.getSelectedWordList()
                        .add(new WordStateWrapper(cursor.getString(getFieldIndex(FIELD_WORD_NAME)), false));
            }
        }
        close();
        return bookWordWrapper;
    }

    @Override
    public void updateBookWordState(List<String> wordNameList, int state) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        for (int i = 0; i < tableNameList.size(); i++) {
            String bookLibraryName = tableNameList.get(i);
            for (int t = 0; t < wordNameList.size(); t++) {
                database.execSQL(String.format(UPDATE_BOOK_WORD_STATE, bookLibraryName),
                        new Object[] { state, wordNameList.get(t) });
            }
        }
        close();
    }

    @Override
    public Map<String, Integer> getWordExistInBookNumber(List<String> wordNameList) {
        Map<String, Integer> map = new HashMap<>();

        for(int i=0;i<tableNameList.size();i++){
            String bookLibraryName=tableNameList.get(i);
            map.put(bookLibraryName,getWordExistInBookNumber(bookLibraryName,wordNameList));
        }

        return map;
    }

    public int getWordExistInBookNumber(String bookLibraryName, List<String> wordNameList) {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();

        int number = 0;
        cursor = database.rawQuery(String.format(QUERY_BOOK_WORD_NAME, bookLibraryName), null);

        while (cursor.moveToNext()) {
            if (wordNameList.contains(cursor.getString(getFieldIndex(FIELD_WORD_NAME)))) {
                number++;
            }
        }
        close();
        return number;
    }
}
