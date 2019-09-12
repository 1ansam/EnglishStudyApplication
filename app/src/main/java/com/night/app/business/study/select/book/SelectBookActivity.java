package com.night.app.business.study.select.book;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.api.business.database.BookAction;
import com.night.api.business.database.BookActionImpl;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.business.study.select.word.SelectWordActivity;
import com.night.app.business.study.select.book.adapter.SelectBookRecycleViewAdapter;
import com.night.app.common.title.TitleInitUtil;
import com.night.app.consts.BusinessConsts;
import com.night.basecore.widget.recyclerview.DividerGridItemDecoration;
import com.night.model.wrapper.BookWrapper;

import java.util.List;

public class SelectBookActivity extends BaseActivity {
    private BookAction mBookAction;
    private List<BookWrapper> mBookWrapperList;

    private RecyclerView mRecycleViewBookGrid;
    private SelectBookRecycleViewAdapter mBookRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_book);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this,R.string.study_function_select_word);

        mBookAction=new BookActionImpl(this);
        mBookWrapperList=mBookAction.getBookList();

        mRecycleViewBookGrid =findViewById(R.id.select_book_recycler_view);
        mBookRecyclerViewAdapter =new SelectBookRecycleViewAdapter(this,mBookWrapperList);

        mRecycleViewBookGrid.setLayoutManager(new GridLayoutManager(this, 2));
        mRecycleViewBookGrid.setAdapter(mBookRecyclerViewAdapter);
        mRecycleViewBookGrid.addItemDecoration(new DividerGridItemDecoration(this));
    }

    @Override
    public void initClick() {
        mBookRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<BookWrapper>() {
            @Override
            public void onItemClick(View view, BookWrapper bookWrapper, int position) {
                Intent intent = new Intent(getApplicationContext(), SelectWordActivity.class);
                intent.putExtra(BusinessConsts.BOOK_LIBRARY_NAME,bookWrapper.getBookLibraryName());
                intent.putExtra(BusinessConsts.BOOK_CHINESE_NAME,bookWrapper.getBookChineseName());
                startActivity(intent);
            }
        });
    }

}
