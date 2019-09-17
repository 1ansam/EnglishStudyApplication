package com.night.app.business.study.recite.adapter;

import android.content.Context;
import android.widget.TextView;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.model.wrapper.database.WordTranslationWrapper;

import java.util.List;

public class ReciteWordTranslationRecyclerViewAdapter extends BaseRecyclerAdapter<WordTranslationWrapper> {

    public ReciteWordTranslationRecyclerViewAdapter(Context context, List<WordTranslationWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, WordTranslationWrapper wordTranslationWrapper) {
        TextView tvWordPart=(TextView)holder.getView(R.id.recite_tv_word_part);
        TextView tvWordMean=(TextView)holder.getView(R.id.recite_tv_word_mean);

        tvWordPart.setText(wordTranslationWrapper.getWordPart());
        tvWordMean.setText(wordTranslationWrapper.getWordMean());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_recite_word_translation;
    }
}
