package com.night.business.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.night.business.consts.DBConsts;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(Context context) {
        super(context, DBConsts.DBNAME, null, DBConsts.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
