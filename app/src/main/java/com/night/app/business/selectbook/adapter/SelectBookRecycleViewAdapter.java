package com.night.app.business.selectbook.adapter;

import java.util.List;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.business.wrapper.database.BookWrapper;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SelectBookRecycleViewAdapter extends BaseRecyclerAdapter<BookWrapper> {

    public SelectBookRecycleViewAdapter(Context context, List<BookWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, BookWrapper bookWrapper) {
        TextView tvBookName = (TextView) holder.getView(R.id.select_book_tv_book_name);
        TextView tvWordNumber = (TextView) holder.getView(R.id.select_book_tv_book_word_number);
        LinearLayout layout = (LinearLayout) holder.getView(R.id.select_book_item_layout);
        tvBookName.setText(bookWrapper.getBookChineseName());
        String wordNumber = bookWrapper.getWordSelectedNumber() + "/" + bookWrapper.getWordTotalNumber();
        tvWordNumber.setText(wordNumber);
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_select_book_grid_child;
    }
}
