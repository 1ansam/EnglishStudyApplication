package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.model.wrapper.database.TestWrapper;

import java.util.ArrayList;
import java.util.List;

public class TestActionImpl extends BaseSQLiteActionImpl implements TestAction {
    private static final String TABLE_NAME                 = "test";

    private static final String FIELD_WORD_NAME            = "word_name";

    private static final String FIELD_A_CHOICE_TRANSLATION = "a_choice_translation";

    private static final String FIELD_B_CHOICE_TRANSLATION = "b_choice_translation";

    private static final String FIELD_C_CHOICE_TRANSLATION = "c_choice_translation";

    private static final String FIELD_D_CHOICE_TRANSLATION = "d_choice_translation";

    private static final String FIELD_SCORE                = "score";

    private static final String FIELD_ANSWER               = "answer";

    private static final String QUERY_TEST                 = "select * from " + TABLE_NAME;

    private static final String INSERT_INTO_TEST           = "insert into " + TABLE_NAME + " values(?,?,?,?,?,?,?)";

    public TestActionImpl(Context context) {
        super(context);
    }

    @Override
    public List<TestWrapper> getTestWrapperList() {
        List<TestWrapper> wrapperList = new ArrayList<>();
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_TEST, null);
        while (cursor.moveToNext()) {
            wrapperList.add(new TestWrapper(cursor.getString(getFieldIndex(FIELD_WORD_NAME)),
                    cursor.getString(getFieldIndex(FIELD_A_CHOICE_TRANSLATION)),
                    cursor.getString(getFieldIndex(FIELD_B_CHOICE_TRANSLATION)),
                    cursor.getString(getFieldIndex(FIELD_C_CHOICE_TRANSLATION)),
                    cursor.getString(getFieldIndex(FIELD_D_CHOICE_TRANSLATION)),
                    cursor.getInt(getFieldIndex(FIELD_SCORE)), cursor.getString(getFieldIndex(FIELD_ANSWER))));
        }
        close();
        return wrapperList;
    }

    @Override
    public void insertIntoTest(List<TestWrapper> wrapperList) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        for (int i = 0; i < wrapperList.size(); i++) {
            TestWrapper wrapper = wrapperList.get(i);
            database.execSQL(INSERT_INTO_TEST,
                    new Object[] { wrapper.getWordName(), wrapper.getaTranslation(), wrapper.getbTranslation(),
                            wrapper.getcTranslation(), wrapper.getdTranslation(), wrapper.getScore(),
                            wrapper.getAnswer() });
        }
        close();
    }
}
