package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.basecore.utils.DateUtil;
import com.night.model.wrapper.database.CurrentWrapper;

import java.util.ArrayList;
import java.util.List;

public class CurrentActionImpl extends BaseSQLiteActionImpl implements CurrentAction {
    private static final String TABLE_NAME           = "current";

    private static final String FIELD_WORD_NAME      = "word_name";

    private static final String FIELD_FIRST_DATE     = "first_date";

    private static final String FIELD_NEXT_DATE      = "next_date";

    private static final String FIELD_END_DATE       = "end_date";

    private static final String FIELD_STATE          = "state";

    private static final String INSERT_INTO_CURRENT  = "insert into " + TABLE_NAME + "(" + FIELD_WORD_NAME + ","
            + FIELD_FIRST_DATE + ") values(?,?)";

    private static final String QUERY_CURRENT_RECITE = "select * from " + TABLE_NAME + " where " + FIELD_STATE + "=?";

    public static final int    STATE_ING            = 0;

    public static final int    STATE_END            = 1;

    public CurrentActionImpl(Context context) {
        super(context);
    }

    @Override
    public boolean insertIntoCurrent(List<String> wordNameList) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        String currentDate = DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number);
        for (int i = 0; i < wordNameList.size(); i++) {
            database.execSQL(INSERT_INTO_CURRENT, new Object[] { wordNameList.get(i), Integer.valueOf(currentDate) });
        }
        close();
        return true;
    }

    @Override
    public List<CurrentWrapper> getCurrentRecite(int targetNumber) {
        String currentDate = DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number);
        List<CurrentWrapper> list = new ArrayList<>();

        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_CURRENT_RECITE, new String[] { String.valueOf(STATE_ING) });
        while (cursor.moveToNext()) {
            Integer nextDate = cursor.getInt(getFieldIndex(FIELD_NEXT_DATE));
            if(nextDate>Integer.valueOf(currentDate)){
                continue;
            }
            String wordName = cursor.getString(getFieldIndex(FIELD_WORD_NAME));
            Integer firstDate = cursor.getInt(getFieldIndex(FIELD_FIRST_DATE));

            CurrentWrapper currentWrapper = new CurrentWrapper(wordName, firstDate, nextDate);
            list.add(currentWrapper);
            if (list.size() == targetNumber) {
                break;
            }
        }
        close();
        return list;
    }

    @Override
    public int getReviseNumber() {
        String currentDate = DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number);
        int number = 0;
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_CURRENT_RECITE, new String[] { String.valueOf(0), currentDate });
        while (cursor.moveToNext()) {
            number++;
        }
        close();
        return number;
    }

    @Override
    public boolean updateNextDate(String wordName) {
        return true;
    }

    @Override
    public boolean updateNextDate(List<String> wordNameList) {
        return false;
    }

    @Override
    public boolean updateEndDate(String wordName) {
        return false;
    }

}
