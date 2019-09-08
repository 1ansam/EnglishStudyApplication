package com.night.business.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.night.business.database.DBOpenHelper;

public class BaseActionImpl {
    public Context        context;

    public DBOpenHelper   openHelper;

    public SQLiteDatabase database;

    public BaseActionImpl(Context context) {
        this.context = context;
        openHelper = new DBOpenHelper(context);
    }

    public void close(Cursor cursor, SQLiteDatabase database, DBOpenHelper openHelper) {
        database.setTransactionSuccessful();
        database.endTransaction();
        cursor.close();
        database.close();
        openHelper.close();
    }
}
