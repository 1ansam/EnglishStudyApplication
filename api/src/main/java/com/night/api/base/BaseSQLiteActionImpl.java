package com.night.api.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.night.api.database.DBOpenHelper;

public class BaseSQLiteActionImpl {
    public Context        context;

    public DBOpenHelper openHelper;

    public SQLiteDatabase database;

    public Cursor cursor;

    public BaseSQLiteActionImpl(Context context) {
        this.context = context;
        openHelper = new DBOpenHelper(context);
    }

    public void close() {
        database.setTransactionSuccessful();
        database.endTransaction();

        if (cursor != null) {
            cursor.close();
        }

        database.close();
        openHelper.close();
    }

    public int getFieldIndex(String fieldName){
        return cursor.getColumnIndex(fieldName);
    }
}
