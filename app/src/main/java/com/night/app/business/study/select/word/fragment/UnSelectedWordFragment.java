package com.night.app.business.study.select.word.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.business.study.select.word.SelectWordActivity;
import com.night.app.business.study.select.word.adapter.SelectWordRecyclerViewAdapter;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.model.wrapper.database.WordStateWrapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UnSelectedWordFragment extends BaseFragment {
    private List<WordStateWrapper>        mSelectedWordList;

    private RecyclerView                  mRecyclerView;

    private SelectWordRecyclerViewAdapter mRecyclerAdapter;

    private SelectWordActivity            mSelectWordActivity;

    private int                           mSelectedNumber;

    private CustomLinearLayoutManager     mLayoutManager;

    public UnSelectedWordFragment(List<WordStateWrapper> list) {
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
        mLayoutManager = new CustomLinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new SelectWordRecyclerViewAdapter(getContext(), mSelectedWordList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mSelectWordActivity = (SelectWordActivity) getActivity();
        mSelectedNumber = 0;
    }

    @Override
    public void initClick() {
        mRecyclerAdapter.setOnItemTouchListener(new BaseRecyclerAdapter.OnItemTouchListener<WordStateWrapper>() {
            // 滑动距离跨越的行数 负数为向上滑的行数 正数为向下滑的行数
            int          spaceNumber         = 0;

            // checkBox被选择的位置集合，用来设置已选数量
            Set<Integer> selectedPositionSet = new HashSet<>();

            @Override
            public void onItemTouch(View view, WordStateWrapper wordStateWrapper, int position,
                    MotionEvent motionEvent) {
                int id = view.getId();

                if (R.id.select_word_item_line_checkbox == id) {
                    float endY = 0;
                    // 行高
                    float recyclerViewItemHeight = mRecyclerView.getChildAt(0).getHeight();
                    // 第一个可见位置
                    int firstVisiblePosition = 0;
                    // 最后一个可见位置
                    int lastVisiblePosition = 0;
                    // 当前滑动所在的位置
                    int currentPosition = 0;
                    // for循环变量
                    int startIndex, endIndex;
                    switch (motionEvent.getAction()) {
                    // 仅在按下时禁止滑动
                    case MotionEvent.ACTION_DOWN:
                        mLayoutManager.setCanVerticalScroll(false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        break;
                    // 仅在移动时对checkBox状态进行设置
                    case MotionEvent.ACTION_MOVE:
                        endY = motionEvent.getY();
                        spaceNumber = (int) ((endY) / recyclerViewItemHeight);
                        firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                        lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                        currentPosition = position - firstVisiblePosition + spaceNumber;
                        if (currentPosition < 0) {
                            currentPosition = 0;
                        }
                        // if(currentPosition>lastVisiblePosition-firstVisiblePosition){
                        // currentPosition=lastVisiblePosition-firstVisiblePosition;
                        // }

                        if (endY < 0) {
                            // 上滑
                            startIndex = currentPosition;
                            endIndex = position - firstVisiblePosition;
                        } else {
                            // 下滑
                            startIndex = position - firstVisiblePosition;
                            endIndex = currentPosition;
                        }
                        for (int i = 0; i <= lastVisiblePosition - firstVisiblePosition; i++) {
                            boolean checkBoxState = mSelectedWordList.get(i + firstVisiblePosition).isSelected();
                            CheckBox checkBox = (CheckBox) (mRecyclerView.getChildAt(i)
                                    .findViewById(R.id.select_word_item_line_checkbox));
                            if (i >= startIndex && i <= endIndex) {
                                checkBox.setChecked(!checkBoxState);
                            } else {
                                checkBox.setChecked(checkBoxState);
                            }
                            if (checkBox.isChecked()) {
                                selectedPositionSet.add(i + firstVisiblePosition);
                            } else {
                                selectedPositionSet.remove(i + firstVisiblePosition);
                            }
                            mSelectWordActivity.mTvSelectedNumber.setText("已选 " + selectedPositionSet.size());
                        }
                        break;
                    // 仅在抬起时更新数据
                    case MotionEvent.ACTION_UP:
                        mLayoutManager.setCanVerticalScroll(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);

                        if (spaceNumber > 0) {
                            startIndex = position;
                            endIndex = position + spaceNumber;
                        } else {
                            startIndex = position + spaceNumber;
                            endIndex = position;
                        }
                        for (int i = startIndex; i <= endIndex; i++) {
                            if (i < 0) {
                                continue;
                            }
                            mSelectedWordList.get(i).reverseSelectedState();
                        }
                        mRecyclerAdapter.refresh(mSelectedWordList);
                        break;
                    default:
                        break;
                    }
                }
            }

        });

    }

    public SelectWordRecyclerViewAdapter getRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.mSelectedNumber = selectedNumber;
    }

}
