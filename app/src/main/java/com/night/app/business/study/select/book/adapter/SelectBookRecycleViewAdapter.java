package com.night.app.business.study.select.book.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.model.wrapper.database.BookWrapper;

import java.util.List;

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
