package com.night.api.business.okhttp;

import android.content.Context;

import com.night.api.base.BaseApiActionImpl;
import com.night.api.business.database.WordActionImpl;
import com.night.api.business.util.WordUtil;
import com.night.api.consts.UrlConsts;
import com.night.api.okhttp.GsonObjectCallback;
import com.night.api.okhttp.OkHttp3Utils;
import com.night.basecore.utils.StringUtil;
import com.night.model.entity.WordEntity;
import com.night.model.wrapper.database.WordDataBaseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class WordApiActionImpl extends BaseApiActionImpl implements WordApiAction {
    public WordApiListener wordApiListener;

    public WordApiActionImpl(Context context) {
        super(context);
    }

    @Override
    public void getWordEntityList(final List<String> wordNameList) {
        final List<WordEntity> wordEntityList = new ArrayList<>();

        for (int i = 0; i < wordNameList.size(); i++) {
            OkHttp3Utils.doGet(UrlConsts.getQueryWordUrl(wordNameList.get(i)), new GsonObjectCallback<WordEntity>() {

                @Override
                public void onSuccess(WordEntity wordEntity) {
                    wordEntityList.add(wordEntity);
                    if (wordEntityList.size() == wordNameList.size()) {
                        List<WordDataBaseWrapper> dataBaseWrapperList = new ArrayList<>();
                        for (int i = 0; i < wordEntityList.size(); i++) {
                            if (StringUtil.isEmpty(wordEntityList.get(i).getWord_name())) {
                                continue;
                            }
                            dataBaseWrapperList.add(WordUtil.changeEntityToDataBaseWrapper(wordEntityList.get(i)));
                        }
                        new WordActionImpl(context).insertIntoWord(dataBaseWrapperList);
                        if (wordApiListener != null) {
                            wordApiListener.doAfterSuccess("success");
                        }
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {
                    if (wordApiListener != null) {
                        wordApiListener.doAfterFailed("failed");
                    }
                }
            });
        }
    }

    @Override
    public void setWordApiListener(WordApiListener wordApiListener) {
        this.wordApiListener = wordApiListener;
    }

    @Override
    public void getWordEntity(String wordName) {
        OkHttp3Utils.doGet(UrlConsts.getQueryWordUrl(wordName), new GsonObjectCallback<WordEntity>() {
            @Override
            public void onSuccess(WordEntity wordEntity) {
                if (wordApiListener != null) {
                    wordApiListener.doAfterSuccess(wordEntity);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {
                if (wordApiListener != null) {
                    wordApiListener.doAfterFailed(null);
                }
            }
        });
    }
}
