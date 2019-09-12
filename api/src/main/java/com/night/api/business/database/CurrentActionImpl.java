package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.basecore.utils.DateUtil;

import java.util.List;

public class CurrentActionImpl extends BaseSQLiteActionImpl implements CurrentAction {
    private static final String TABLE_NAME          = "current";

    private static final String FIELD_WORD_NAME     = "word_name";

    private static final String FIELD_FIRST_DATE    = "first_date";

    private static final String FIELD_NEXT_DATE     = "next_date";

    private static final String FIELD_END_DATE      = "end_date";

    private static final String FIELD_STATE         = "state";

    private static final String INSERT_INTO_CURRENT = "insert into " + TABLE_NAME + "(" + FIELD_WORD_NAME + ","
            + FIELD_FIRST_DATE + ") values(?,?)";

    private static final String QUERY_CURRENT_RECITE = "select * from " + TABLE_NAME+" where "+FIELD_STATE+"=? and "+FIELD_NEXT_DATE+"<=?";

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
    public List<String> getCurrentRecite() {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor=database.rawQuery(QUERY_CURRENT_RECITE,null);
        while(cursor.moveToNext()){
//            String wordName=
        }
        return null;
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
