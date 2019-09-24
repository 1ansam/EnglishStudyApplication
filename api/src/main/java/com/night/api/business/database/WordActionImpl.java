package com.night.api.business.database;

import android.content.Context;

import com.night.api.base.BaseSQLiteActionImpl;
import com.night.api.business.util.WordUtil;
import com.night.api.consts.enums.WordEnums;
import com.night.basecore.utils.StringUtil;
import com.night.model.wrapper.Common.WordWrapper;
import com.night.model.wrapper.database.WordDataBaseWrapper;

import java.util.ArrayList;
import java.util.List;

public class WordActionImpl extends BaseSQLiteActionImpl implements WordAction {
    private static final String TABLE_NAME                   = "word";

    private static final String FIELD_WORD_NAME              = "word_name";

    private static final String FIELD_WORD_PH_EN             = "word_ph_en";

    private static final String FIELD_WORD_PH_EN_MP3         = "word_ph_en_mp3";

    private static final String FIELD_WORD_PH_AM             = "word_ph_am";

    private static final String FIELD_WORD_PH_AM_MP3         = "word_ph_am_mp3";

    private static final String FIELD_WORD_PARTS             = "word_parts";

    private static final String FIELD_WORD_MEANS             = "word_means";

    private static final String FIELD_WORD_COLLECT_STATE     = "word_collect_state";

    private static final String INSERT_INTO_WORD             = "insert into " + TABLE_NAME + " values(?,?,?,?,?,?,?,?)";

    private static final String QUERY_WORD_NAME_BY_WORD_NAME = "select " + FIELD_WORD_NAME + " from " + TABLE_NAME
            + " where " + FIELD_WORD_NAME + "=?";

    private static final String QUERY_WORD_BY_WORD_NAME      = "select * from " + TABLE_NAME + " where "
            + FIELD_WORD_NAME + "=?";

    private static final String UPDATE_WORD_COLLECT_STATE    = "update " + TABLE_NAME + " set "
            + FIELD_WORD_COLLECT_STATE + "=? where " + FIELD_WORD_NAME + " =?";

    public WordActionImpl(Context context) {
        super(context);
    }

    @Override
    public void insertIntoWord(List<WordDataBaseWrapper> wordList) {
        List<Boolean> isContainedList = isContainedInTable(WordUtil.getWordNameListFromDataBaseWrapper(wordList));

        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        for (int i = 0; i < wordList.size(); i++) {
            // 如果当前表中含有该单词则跳过
            if (isContainedList.get(i) == true) {
                continue;
            }
            WordDataBaseWrapper wrapper = wordList.get(i);
            // 如果该单词为空则跳过
            if (wrapper == null || StringUtil.isEmpty(wrapper.getWordName())) {
                continue;
            } else {
                database.execSQL(INSERT_INTO_WORD,
                        new Object[] { wrapper.getWordName(), wrapper.getWordPhEn(), wrapper.getWordPhEnMp3(),
                                wrapper.getWordPhAm(), wrapper.getWordPhAmMp3(), wrapper.getWordParts(),
                                wrapper.getWordMeans(), WordEnums.NOT_COLLECTED });
            }
        }
        close();
    }

    @Override
    public void insertIntoWord(WordDataBaseWrapper wrapper) {
        Boolean isContained = isContainedInTable(wrapper.getWordName());
        if (isContained) {
            return;
        } else {
            database = openHelper.getWritableDatabase();
            database.beginTransaction();
            database.execSQL(INSERT_INTO_WORD,
                    new Object[] { wrapper.getWordName(), wrapper.getWordPhEn(), wrapper.getWordPhEnMp3(),
                            wrapper.getWordPhAm(), wrapper.getWordPhAmMp3(), wrapper.getWordParts(),
                            wrapper.getWordMeans(),wrapper.getWordCollectState() });
            close();
        }
    }

    @Override
    public boolean isContainedInTable(String wordName) {
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        cursor = database.rawQuery(QUERY_WORD_NAME_BY_WORD_NAME, new String[]{wordName});
        if (cursor.moveToNext()) {
            close();
            return true;
        }
        close();
        return false;
    }

    @Override
    public List<Boolean> isContainedInTable(List<String> wordNameList) {
        List<Boolean> booleanList = new ArrayList<>();
        database = openHelper.getReadableDatabase();
        database.beginTransaction();
        for (int i = 0; i < wordNameList.size(); i++) {
            cursor = database.rawQuery(QUERY_WORD_NAME_BY_WORD_NAME, new String[] { wordNameList.get(i) });
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
    public List<WordWrapper> getWordByName(List<String> wordNameList) {
        List<WordWrapper> wordWrapperList = new ArrayList<>();
        database = openHelper.getReadableDatabase();
        for (int i = 0; i < wordNameList.size(); i++) {
            database.beginTransaction();
            cursor = database.rawQuery(QUERY_WORD_BY_WORD_NAME, new String[] { wordNameList.get(i) });
            if (cursor.moveToNext()) {
                String wordName = cursor.getString(getFieldIndex(FIELD_WORD_NAME));
                String wordPhEn = cursor.getString(getFieldIndex(FIELD_WORD_PH_EN));
                String wordPhEnMp3 = cursor.getString(getFieldIndex(FIELD_WORD_PH_EN_MP3));
                String wordPhAm = cursor.getString(getFieldIndex(FIELD_WORD_PH_AM));
                String wordPhAmMp3 = cursor.getString(getFieldIndex(FIELD_WORD_PH_AM_MP3));
                String wordPart = cursor.getString(getFieldIndex(FIELD_WORD_PARTS));
                String wordMean = cursor.getString(getFieldIndex(FIELD_WORD_MEANS));
                int wordCollectState = cursor.getInt(getFieldIndex(FIELD_WORD_COLLECT_STATE));
                wordWrapperList.add(WordUtil.changeDataBaseWrapperToWrapper(new WordDataBaseWrapper(wordName, wordPhEn,
                        wordPhEnMp3, wordPhAm, wordPhAmMp3, wordPart, wordMean, wordCollectState)));
            }
            database.setTransactionSuccessful();
            database.endTransaction();
        }
        database.close();
        if (cursor != null) {
            cursor.close();
        }
        openHelper.close();
        return wordWrapperList;
    }

    @Override
    public WordWrapper getWordByName(String wordName) {
        List<String> list = new ArrayList<>();
        list.add(wordName);
        List<WordWrapper> wrapperList = getWordByName(list);
        if (wrapperList.isEmpty()) {
            return null;
        } else {
            return wrapperList.get(0);
        }
    }

    @Override
    public void updateWordCollectState(String wordName, int wordCollectState) {
        database = openHelper.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(UPDATE_WORD_COLLECT_STATE, new Object[] { wordCollectState, wordName });
        close();
    }

}
