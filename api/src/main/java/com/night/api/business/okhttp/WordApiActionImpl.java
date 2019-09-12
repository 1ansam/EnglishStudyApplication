package com.night.api.business.okhttp;

import android.content.Context;

import com.night.api.base.BaseApiActionImpl;
import com.night.api.business.database.WordActionImpl;
import com.night.api.consts.MessageConsts;
import com.night.api.consts.UrlConsts;
import com.night.api.okhttp.GsonObjectCallback;
import com.night.api.okhttp.OkHttp3Utils;
import com.night.basecore.utils.LogUtil;
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
            // LogUtil.d(UrlConsts.getQueryWordUrl(wordNameList.get(i)));
//            final Integer finalI = i;
            OkHttp3Utils.doGet(UrlConsts.getQueryWordUrl(wordNameList.get(i)), new GsonObjectCallback<WordEntity>() {

                @Override
                public void onSuccess(WordEntity wordEntity) {
                    // if(wordEntity==null){
                    // LogUtil.d("null ");
                    // }
                    // LogUtil.d("wordName " + wordEntity.getWord_name());
                    // LogUtil.d("word_ph_en " + wordEntity.getSymbols().get(0).getPh_en());
                    // LogUtil.d("word_ph_en_mp3 " + wordEntity.getSymbols().get(0).getPh_en_mp3());
                    // LogUtil.d("word_ph_am " + wordEntity.getSymbols().get(0).getPh_am());
                    // LogUtil.d("word_ph_am_mp3 " + wordEntity.getSymbols().get(0).getPh_am_mp3());
                    // for (int i = 0; i < wordEntity.getSymbols().get(0).getParts().size(); i++) {
                    // LogUtil.d("word_translation part " +
                    // wordEntity.getSymbols().get(0).getParts().get(i).getPart());
                    // LogUtil.d("word_translation means "
                    // + wordEntity.getSymbols().get(0).getParts().get(i).getMeans());
                    // }
                    // LogUtil.d(wordEntityList.size()+" ");
                    // LogUtil.d(wordEntityList.get(0).getWord_name()+" smty");
                    //当请求的单词不存在时，返回的wordName为null可做判断
//                    LogUtil.d(finalI+"  position"+wordNameList.get(finalI));
//                    if(wordEntity.getWord_name()!=null){
//                        LogUtil.d(wordEntity.getWord_name());
//                    }else{
//                        LogUtil.d("null");
//                    }
                    wordEntityList.add(wordEntity);
                    if(wordEntityList.size()==wordNameList.size()){
                        for(int i=0;i<wordEntityList.size();i++){
                            if(StringUtil.isEmpty(wordEntityList.get(i).getWord_name())){
                                wordEntityList.remove(i);
                                LogUtil.d("remove");
                                i--;
                            }
                        }
                        new WordActionImpl(context).insertIntoWord(wordEntityList);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {
                    ToastUtil.showShortToast(context, MessageConsts.INTERNET_NOT_AVAILABLE);
//                    LogUtil.i(Thread.currentThread().toString());
//                     LogUtil.d("failure");
                }
            });
        }
    }
}
