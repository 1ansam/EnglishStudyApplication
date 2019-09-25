package com.night.app.business.study.study;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.business.study.mybook.MyBookActivity;
import com.night.app.business.study.query.QueryWordActivity;
import com.night.app.business.study.recite.ReciteActivity;
import com.night.app.business.study.select.book.SelectBookActivity;
import com.night.app.business.study.study.adapter.StudyFunctionRecycleViewAdapter;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.basecore.widget.recyclerview.DividerGridItemDecoration;
import com.night.app.consts.BusinessConsts;
import com.night.app.base.fragment.BaseFragment;
import com.night.model.wrapper.baseui.CommonFunctionWrapper;

public class HomeStudyFragment extends BaseFragment {
    private RecyclerView                    mRecycleViewFunctionGrid;

    private StudyFunctionRecycleViewAdapter mStudyFunctionRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_study, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mRecycleViewFunctionGrid = view.findViewById(R.id.home_study_recycle_view_function_grid);
        mStudyFunctionRecycleViewAdapter = new StudyFunctionRecycleViewAdapter(getContext(),
                BusinessConsts.getStudyFunctionWrapperList());

        mRecycleViewFunctionGrid.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecycleViewFunctionGrid.setAdapter(mStudyFunctionRecycleViewAdapter);
        mRecycleViewFunctionGrid.addItemDecoration(new DividerGridItemDecoration(getContext()));
    }

    @Override
    public void initClick() {
        mStudyFunctionRecycleViewAdapter
                .setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CommonFunctionWrapper>() {
                    @Override
                    public void onItemClick(View view, CommonFunctionWrapper commonFunctionWrapper, int position) {
                        Intent intent = null;
                        switch (commonFunctionWrapper.getTitleNameId()) {
                        case R.string.study_function_recite:
                            intent = new Intent(getActivity(), ReciteActivity.class);
                            break;
                        case R.string.study_function_select_word:
                            intent = new Intent(getActivity(), SelectBookActivity.class);
                            break;
                        case R.string.study_function_query_word:
                            intent = new Intent(getActivity(), QueryWordActivity.class);
                            break;
                        case R.string.study_function_my_word_book:
                            intent = new Intent(getActivity(), MyBookActivity.class);
                            break;
                        default:
                            break;
                        }
                        if (intent != null) {
                            startActivity(intent);
                        }
                    }
                });
    }
}
