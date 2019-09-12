package com.night.app.business.study.recite.adapter;

import android.content.Context;

import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.model.wrapper.WordTranslationWrapper;

import java.util.List;

public class ReciteWordTranslationRecyclerViewAdapter extends BaseRecyclerAdapter<WordTranslationWrapper> {
    public ReciteWordTranslationRecyclerViewAdapter(Context context, List<WordTranslationWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, WordTranslationWrapper wordTranslationWrapper) {

    }

    @Override
    public int getItemLayoutId() {
        return 0;
    }
}
