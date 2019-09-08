package com.night.business.database;

import android.content.Context;
import android.database.Cursor;

import com.night.business.base.BaseActionImpl;
import com.night.business.wrapper.database.BookWordWrapper;
import com.night.business.wrapper.database.WordStateWrapper;

public class BookWordActionImpl extends BaseActionImpl implements BookWordAction {
    private static final String WORD_NAME ="word_name";
    private static final String WORD_STATE="word_state";

    private static final int WORD_NAME_INDEX=0;
    private static final int WORD_STATE_INDEX=1;

    private static final String QUERY_BOOK_WORD_LIST = "select * from ";

    public BookWordActionImpl(Context context) {
        super(context);
    }

    @Override
    public BookWordWrapper getBookWordWrapper(String bookLibraryName) {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        BookWordWrapper bookWordWrapper = new BookWordWrapper();
        String sql = QUERY_BOOK_WORD_LIST + bookLibraryName;
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            int wordState=cursor.getInt(WORD_STATE_INDEX);
            if(wordState==0){
                bookWordWrapper.getUnSelectedWordList().add(new WordStateWrapper(cursor.getString(WORD_NAME_INDEX),false));
            }else{
                bookWordWrapper.getSelectedWordList().add(new WordStateWrapper(cursor.getString(WORD_NAME_INDEX),false));
            }
        }
        close(cursor,database,openHelper);
        return bookWordWrapper;
    }

}
