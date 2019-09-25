package com.night.app.common.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.api.consts.SharePreferenceConsts;
import com.night.api.consts.enums.WordEnums;
import com.night.basecore.utils.SharedPrefsUtil;
import com.night.basecore.utils.StyleUtil;
import com.night.model.wrapper.common.WordWrapper;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.util.List;

public class WordInfoRecyclerAdapter extends BaseRecyclerAdapter<ReciteWordWrapper> {
    /**
     * 单词发音类型 WordEnums.WORD_PH_EN 英式发音 WordEnums.WORD_PH_AM 美式发音
     */
    private int wordPhType;

    /**
     * 单词翻译可见性
     */
    private int wordTranslationVisibility = View.VISIBLE;

    public WordInfoRecyclerAdapter(Context context, List<ReciteWordWrapper> data) {
        super(context, data);
        wordPhType = SharedPrefsUtil.getInt(context, SharePreferenceConsts.WORD_PH_TYPE, WordEnums.WORD_PH_EN);
    }

    @Override
    public void bindData(BaseViewHolder holder, ReciteWordWrapper reciteWordWrapper) {
        TextView tvWordName = (TextView) holder.getView(R.id.word_list_info_tv_word_name);
        TextView tvWordPh = (TextView) holder.getView(R.id.word_list_info_tv_word_ph);
        ImageView ivHorn = (ImageView) holder.getView(R.id.word_list_info_iv_horn);
        TextView tvWordTranslation = (TextView) holder.getView(R.id.word_list_info_tv_word_translation);

        WordWrapper wordWrapper = reciteWordWrapper.getWordWrapper();
        tvWordName.setText(wordWrapper.getWordName());
        if (wordPhType == WordEnums.WORD_PH_EN) {
            tvWordPh.setText(StyleUtil.getWordPh(wordWrapper.getWordPhEn(), "en"));
            ivHorn.setTag(wordWrapper.getWordPhEnMp3());
        }
        if (wordPhType == WordEnums.WORD_PH_AM) {
            tvWordPh.setText(StyleUtil.getWordPh(wordWrapper.getWordPhAm(), "am"));
            ivHorn.setTag(wordWrapper.getWordPhAmMp3());
        }
        String wordTranslation = "";
        for (int i = 0; i < wordWrapper.getWordTranslationWrapperList().size(); i++) {
            wordTranslation += (wordWrapper.getWordTranslationWrapperList().get(i).getWordPart()
                    + wordWrapper.getWordTranslationWrapperList().get(i).getWordMean());
            if (i != wordWrapper.getWordTranslationWrapperList().size() - 1) {
                wordTranslation += "\n";
            }
        }
        tvWordTranslation.setVisibility(wordTranslationVisibility);
        tvWordTranslation.setText(wordTranslation);

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_word_list_info;
    }

    public void setWordPhType(int wordPhType) {
        this.wordPhType = wordPhType;
    }

    public int getWordPhType() {
        return wordPhType;
    }

    public void setWordTranslationVisibility(int wordTranslationVisibility) {
        this.wordTranslationVisibility = wordTranslationVisibility;
    }
}
