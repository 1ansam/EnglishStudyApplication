package com.night.app.business.study.select.word.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.model.wrapper.WordStateWrapper;

import java.util.List;

public class SelectWordRecyclerViewAdapter extends BaseRecyclerAdapter<WordStateWrapper> {
    public SelectWordRecyclerViewAdapter(Context context, List<WordStateWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, WordStateWrapper wordStateWrapper) {
        CheckBox checkBox = (CheckBox) holder.getView(R.id.select_word_item_line_checkbox);
        TextView tvWordName = (TextView) holder.getView(R.id.select_word_item_line_tv_name);
        tvWordName.setText(holder.getDataPosition()+"   "+wordStateWrapper.getWordName());
        checkBox.setChecked(wordStateWrapper.isSelected());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_select_word_recycler_line;
    }

}
