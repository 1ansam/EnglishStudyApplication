package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.model.wrapper.database.LogWrapper;

import java.util.ArrayList;
import java.util.List;

public class LogActionImpl extends BaseSQLiteActionImpl implements LogAction {
    private static final String TABLE_NAME               = "log";

    private static final String FIELD_LOG_DATE           = "date";

    public static final String  FIELD_LOG_NEW_NUMBER     = "new_number";

    public static final String  FIELD_LOG_REVISE_NUMBER  = "revise_number";

    public static final String  FIELD_LOG_SIGN           = "sign";

    private static final String INSERT_INTO_LOG          = "insert into " + TABLE_NAME + " values(?,?,?,?)";

    private static final String QUERY_LOG                = "select * from " + TABLE_NAME;

    private static final String QUERY_LOG_BY_DATE        = "select * from " + TABLE_NAME + " where " + FIELD_LOG_DATE
            + "=?";

    private static final String UPDATE_LOG_NEW_NUMBER    = "update " + TABLE_NAME + " set " + FIELD_LOG_NEW_NUMBER
            + "=? where " + FIELD_LOG_DATE + "=?";

    private static final String UPDATE_LOG_REVISE_NUMBER = "update " + TABLE_NAME + " set " + FIELD_LOG_REVISE_NUMBER
            + "=? where " + FIELD_LOG_DATE + "=?";

    private static final String UPDATE_LOG_SIGN          = "update " + TABLE_NAME + " set " + FIELD_LOG_SIGN
            + "=? where " + FIELD_LOG_DATE + "=?";

    private static final String UPDATE_LOG               = "update " + TABLE_NAME + " set " + FIELD_LOG_NEW_NUMBER
            + "=? ," + FIELD_LOG_REVISE_NUMBER + "=?," + FIELD_LOG_SIGN + "=? where " + FIELD_LOG_DATE + "=?";

    public LogActionImpl(Context context) {
        super(context);
    }

    @Override
    public LogWrapper getLogWrapper(String date) {
        LogWrapper logWrapper = null;
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_LOG_BY_DATE, new String[] { date });
        if (cursor.moveToNext()) {
            logWrapper = new LogWrapper(cursor.getString(getFieldIndex(FIELD_LOG_DATE)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_NEW_NUMBER)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_REVISE_NUMBER)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_SIGN)));
        }
        close();
        return logWrapper;
    }

    @Override
    public List<LogWrapper> getLogWrapperList() {
        List<LogWrapper> logWrapperList = new ArrayList<>();

        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_LOG, null);
        while (cursor.moveToNext()) {
            logWrapperList.add(new LogWrapper(cursor.getString(getFieldIndex(FIELD_LOG_DATE)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_NEW_NUMBER)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_REVISE_NUMBER)),
                    cursor.getInt(getFieldIndex(FIELD_LOG_SIGN))));
        }
        close();
        return logWrapperList;
    }

    @Override
    public void insertIntoLog(LogWrapper logWrapper) {
        LogWrapper tempWrapper = getLogWrapper(logWrapper.getDate());
        if (tempWrapper == null) {
            database = openHelper.getWritableDatabase();
            database.beginTransaction();
            database.execSQL(INSERT_INTO_LOG, new Object[] { logWrapper.getDate(), logWrapper.getNewNumber(),
                    logWrapper.getReviseNumber(), logWrapper.getSign() });
            close();
        }
    }

    @Override
    public void update(String date, int number, String field) {
        String sql = "";
        switch (field) {
        case FIELD_LOG_NEW_NUMBER:
            sql = UPDATE_LOG_NEW_NUMBER;
            break;
        case FIELD_LOG_REVISE_NUMBER:
            sql = UPDATE_LOG_REVISE_NUMBER;
            break;
        case FIELD_LOG_SIGN:
            sql = UPDATE_LOG_SIGN;
            break;
        default:
            break;
        }
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(sql, new Object[] { number });
        close();
    }

    @Override
    public void updateLogWrapper(LogWrapper wrapper) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(UPDATE_LOG, new Object[] { wrapper.getNewNumber(),wrapper.getReviseNumber(),wrapper.getSign(),wrapper.getDate() });
        close();
    }
}
