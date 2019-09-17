package com.night.app.business.study.select.word.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.business.study.select.word.adapter.SelectWordRecyclerViewAdapter;
import com.night.model.wrapper.database.WordStateWrapper;

import java.util.List;

public class SelectedWordFragment extends BaseFragment {

    private List<WordStateWrapper>        mSelectedWordList;

    private RecyclerView                  mRecyclerView;

    private SelectWordRecyclerViewAdapter mRecyclerAdapter;

    public SelectedWordFragment(List<WordStateWrapper> list) {
        mSelectedWordList = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_select_word_view_pager_fragment, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mRecyclerView = view.findViewById(R.id.select_word_item_recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        mRecyclerAdapter = new SelectWordRecyclerViewAdapter(getContext(), mSelectedWordList);
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }

    @Override
    public void initClick() {
        mRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<WordStateWrapper>() {
            @Override
            public void onItemClick(View view, WordStateWrapper wordStateWrapper, int position) {
                switch (view.getId()) {
                case R.id.select_word_item_line_checkbox:
                    boolean isSelected = !mSelectedWordList.get(position).isSelected();
                    mSelectedWordList.get(position).setSelected(isSelected);
                    mRecyclerAdapter.refresh(mSelectedWordList);
                }
            }
        });
    }
}
