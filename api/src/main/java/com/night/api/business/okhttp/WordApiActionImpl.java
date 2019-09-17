package com.night.api.business.okhttp;

import android.content.Context;

import com.night.api.base.BaseApiActionImpl;
import com.night.api.business.database.WordActionImpl;
import com.night.api.consts.MessageConsts;
import com.night.api.consts.UrlConsts;
import com.night.api.okhttp.GsonObjectCallback;
import com.night.api.okhttp.OkHttp3Utils;
import com.night.basecore.utils.StringUtil;
import com.night.basecore.utils.ToastUtil;
import com.night.model.entity.WordEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class WordApiActionImpl extends BaseApiActionImpl implements WordApiAction {

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
                        for (int i = 0; i < wordEntityList.size(); i++) {
                            if (StringUtil.isEmpty(wordEntityList.get(i).getWord_name())) {
                                wordEntityList.remove(i);
                                i--;
                            }
                            String wordPhEnMp3 = wordEntityList.get(i).getSymbols().get(0).getPh_en_mp3();
                            String wordPhAmMp3 = wordEntityList.get(i).getSymbols().get(0).getPh_am_mp3();
                            if (!StringUtil.isEmpty(wordPhEnMp3)) {
                                wordEntityList.get(i).getSymbols().get(0)
                                        .setPh_en_mp3(UrlConsts.HTTPS + wordPhEnMp3.substring(7));
                            }
                            if (!StringUtil.isEmpty(wordPhAmMp3)) {
                                wordEntityList.get(i).getSymbols().get(0)
                                        .setPh_am_mp3(UrlConsts.HTTPS + wordPhAmMp3.substring(7));
                            }
                        }
                        new WordActionImpl(context).insertIntoWord(wordEntityList);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {
                    ToastUtil.showShortToast(context, MessageConsts.INTERNET_NOT_AVAILABLE);
                    // LogUtil.i(Thread.currentThread().toString());
                    // LogUtil.d("failure");
                }
            });
        }
    }
}
