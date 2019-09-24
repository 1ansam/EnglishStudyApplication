package com.night.api.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.night.api.database.DBOpenHelper;

public class BaseSQLiteActionImpl {
    protected Context        context;

    protected DBOpenHelper openHelper;

    protected SQLiteDatabase database;

    protected Cursor cursor;

    public BaseSQLiteActionImpl(Context context) {
        this.context = context;
        openHelper = new DBOpenHelper(context);

    }

    protected void close() {
        database.setTransactionSuccessful();
        database.endTransaction();

        if (cursor != null) {
            cursor.close();
        }

        database.close();
        openHelper.close();
    }

    protected int getFieldIndex(String fieldName){
        return cursor.getColumnIndex(fieldName);
    }
}
