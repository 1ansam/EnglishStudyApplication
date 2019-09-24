package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.api.consts.enums.WordEnums;
import com.night.basecore.utils.DateUtil;
import com.night.model.wrapper.database.CurrentWrapper;

import java.util.ArrayList;
import java.util.List;

public class CurrentActionImpl extends BaseSQLiteActionImpl implements CurrentAction {
    private static final String TABLE_NAME               = "current";

    private static final String FIELD_WORD_NAME          = "word_name";

    private static final String FIELD_FIRST_DATE         = "first_date";

    private static final String FIELD_NEXT_DATE          = "next_date";

    private static final String FIELD_END_DATE           = "end_date";

    private static final String FIELD_STATE              = "state";

    private static final String INSERT_INTO_CURRENT      = "insert into " + TABLE_NAME + "(" + FIELD_WORD_NAME + ","
            + FIELD_FIRST_DATE + ") values(?,?)";

    private static final String QUERY_CURRENT_BY_STATE = "select * from " + TABLE_NAME + " where " + FIELD_STATE
            + "!=?";

    private static final String UPDATE_CURRENT_NEXT_DATE = "update " + TABLE_NAME + " set " + FIELD_NEXT_DATE + "=?,"
            + FIELD_STATE + "=? where " + FIELD_WORD_NAME + "=?";

    private static final String UPDATE_CURRENT_END_DATE  = "update " + TABLE_NAME + " set " + FIELD_NEXT_DATE + "=?,"
            + FIELD_END_DATE + "=?," + FIELD_STATE + "=? where " + FIELD_WORD_NAME + "=?";

    private static final String UPDATE_CURRENT_STATE     = "update " + TABLE_NAME + " set " + FIELD_STATE + "=? where "
            + FIELD_WORD_NAME + "=?";

    private static final String QUERY_CURRENT_BY_NAME="select * from "+TABLE_NAME+" where "+FIELD_WORD_NAME+"=?";

    public CurrentActionImpl(Context context) {
        super(context);
    }

    @Override
    public void insertIntoCurrent(List<String> wordNameList) {
        List<Boolean> booleanList =isContainedInTable(wordNameList);

        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String currentDate = DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number);
        for (int i = 0; i < wordNameList.size(); i++) {
            if(booleanList.get(i)){
                continue;
            }
            database.execSQL(INSERT_INTO_CURRENT, new Object[] { wordNameList.get(i), Integer.valueOf(currentDate) });
        }
        close();
    }

    @Override
    public List<CurrentWrapper> getCurrentRecite(int targetNumber) {
        String currentDate = DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number);
        List<CurrentWrapper> list = new ArrayList<>();

        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_CURRENT_BY_STATE, new String[] { String.valueOf(WordEnums.STATE_END) });
        while (cursor.moveToNext()) {
            Integer nextDate = cursor.getInt(getFieldIndex(FIELD_NEXT_DATE));
            if (nextDate > Integer.valueOf(currentDate)) {
                continue;
            }
            String wordName = cursor.getString(getFieldIndex(FIELD_WORD_NAME));
            Integer firstDate = cursor.getInt(getFieldIndex(FIELD_FIRST_DATE));
            Integer state = cursor.getInt(getFieldIndex(FIELD_STATE));
            CurrentWrapper currentWrapper = new CurrentWrapper(wordName, firstDate, nextDate, state);
            list.add(currentWrapper);
            if (list.size() == targetNumber) {
                break;
            }
        }
        close();
        return list;
    }

    @Override
    public void updateCurrentNextDate(String wordName, int expectedNextDate, int state) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(UPDATE_CURRENT_NEXT_DATE, new Object[] { expectedNextDate, state, wordName });
        close();
    }

    @Override
    public void updateCurrentEnd(String wordName) {
        int currentDate = Integer.valueOf(DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number));
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(UPDATE_CURRENT_END_DATE,
                new Object[] { null, currentDate, WordEnums.STATE_END,wordName });
        close();
    }

    @Override
    public void updateCurrentState(String wordName, int state) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(UPDATE_CURRENT_STATE, new Object[] { state, wordName });
        close();
    }

    @Override
    public List<Boolean> isContainedInTable(List<String> wordNameList) {
        List<Boolean> booleanList = new ArrayList<>();
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        for (int i = 0; i < wordNameList.size(); i++) {
            cursor = database.rawQuery(QUERY_CURRENT_BY_NAME, new String[] { wordNameList.get(i) });
            booleanList.add(false);
        }
        while (cursor.moveToNext()) {
            String wordName = cursor.getString(getFieldIndex(FIELD_WORD_NAME));
            if (wordNameList.contains(wordName)) {
                booleanList.set(wordNameList.indexOf(wordName), true);
            }
        }
        close();
        return booleanList;
    }

    @Override
    public boolean isContainedInTable(String wordName) {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_CURRENT_BY_NAME, new String[]{wordName});
        if (cursor.moveToNext()) {
            close();
            return true;
        }
        close();
        return false;
    }

}
