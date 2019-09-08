package com.night.app.business.selectword.fragment;

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
import com.night.app.business.selectword.SelectWordActivity;
import com.night.app.business.selectword.adapter.SelectWordRecyclerViewAdapter;
import com.night.basecore.utils.LogUtil;
import com.night.basecore.widget.RecycleView.CustomLinearLayoutManager;
import com.night.business.wrapper.database.WordStateWrapper;

import java.util.ArrayList;
import java.util.List;

public class UnSelectedWordFragment extends BaseFragment {
    private List<WordStateWrapper>           mSelectedWordList = new ArrayList<>();

    private RecyclerView                     mRecyclerView;

    private SelectWordRecyclerViewAdapter    mRecyclerAdapter;

    private SelectWordActivity               mSelectWordActivity;

    private int                              mSelectedNumber;

    private CustomLinearLayoutManager mLayoutManager;

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
        mLayoutManager = new CustomLinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new SelectWordRecyclerViewAdapter(getContext(), mSelectedWordList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mSelectWordActivity = (SelectWordActivity) getActivity();
        mSelectedNumber = 0;
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

                    if (isSelected) {
                        mSelectedNumber++;
                    } else {
                        mSelectedNumber--;
                    }
                    mSelectWordActivity.mTvSelectedNumber.setText("已选 " + mSelectedNumber);
                    break;
                }
            }
        });

        mRecyclerAdapter.setOnItemTouchListener(new BaseRecyclerAdapter.OnItemTouchListener<WordStateWrapper>() {
            @Override
            public void onItemTouch(View view, WordStateWrapper wordStateWrapper, int position,
                    MotionEvent motionEvent) {
                // recyclerView 的item行高
                float recyclerViewItemHeight = mRecyclerView.getChildAt(0).getHeight();
               // LogUtil.d("item height  "+recyclerViewItemHeight);

                //recyclerView的高度
                float recyclerViewHeight=mRecyclerView.getHeight();
               // LogUtil.d("recycler View  height  "+recyclerViewHeight);

                // 当前屏幕能够显示的item条数
                int itemNumber = (int)(recyclerViewHeight/recyclerViewItemHeight);
               // LogUtil.d("itemNumber   "+itemNumber);
                int id = view.getId();
                if (R.id.select_word_item_line_checkbox == id) {
                    float endY = 0;
                    // 滑动距离跨越的行数
                    int spaceNumber = 0;

                    // 滑动距离跨越的行数的备份，用以判断spaceNumber是否变化
                    int tempSpaceNumber = -1;

                    // 当前滑动到的位置
                    int currentPosition = position;

                    // 对touch动作类型进行判断
                    switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mLayoutManager.setCanVerticalScroll(false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        endY = motionEvent.getY();
                        //LogUtil.d("endY   "+endY);

                        spaceNumber = (int) ((Math.abs(endY)) / recyclerViewItemHeight);
                        //LogUtil.d("spaceNumber  " + spaceNumber);

                        // 仅在spaceNumber变化时对数据进行更新
                        if (tempSpaceNumber != spaceNumber) {
                            if (endY < 0) {
                                // 上滑
                                currentPosition = position - spaceNumber;
                                LogUtil.d("currentPosition" + currentPosition);
                            } else {
                                //下滑
                                currentPosition=position+spaceNumber;
                            }
                            LogUtil.d("index" + currentPosition%itemNumber);
                            if (mRecyclerView.getChildAt(currentPosition % itemNumber) != null) {
                                CheckBox checkBox = (CheckBox) mRecyclerView
                                        .getChildAt((currentPosition % itemNumber))
                                        .findViewById(R.id.select_word_item_line_checkbox);
                                checkBox.setChecked(!mSelectedWordList.get(currentPosition).isSelected());
                                tempSpaceNumber = spaceNumber;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mLayoutManager.setCanVerticalScroll(true);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        endY = 0;
                        break;
                    }

                }
            }
        });

    }

    public SelectWordRecyclerViewAdapter getmRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    public void setmSelectedNumber(int mSelectedNumber) {
        this.mSelectedNumber = mSelectedNumber;
    }

}
