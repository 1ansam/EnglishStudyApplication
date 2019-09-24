package com.night.app.business.study.recite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.night.api.business.database.CurrentAction;
import com.night.api.business.database.CurrentActionImpl;
import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.api.business.util.WordUtil;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.study.recite.adapter.ReciteViewPagerAdapter;
import com.night.app.common.util.TitleInitUtil;
import com.night.api.consts.SharePreferenceConsts;
import com.night.api.consts.enums.WordEnums;
import com.night.basecore.utils.SharedPrefsUtil;
import com.night.basecore.widget.viewpager.ZoomOutPagerTransformer;
import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.Common.WordWrapper;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReciteActivity extends BaseActivity {
    private ViewPager               mViewPager;

    private ReciteViewPagerAdapter  mViewPagerAdapter;

    private CurrentAction           mCurrentAction;

    private WordAction              mWordAction;

    private List<CurrentWrapper>    mCurrentWrapperList;

    private List<ReciteWordWrapper> mReciteWordWrapperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        initData(savedInstanceState);
        initView();
        initClick();
    }

    private void initData(Bundle savedInstanceState) {
        mCurrentAction = new CurrentActionImpl(this);
        mWordAction = new WordActionImpl(this);
        mReciteWordWrapperList = (List<ReciteWordWrapper>) getIntent().getSerializableExtra("reciteWordWrapperList");
        if (mReciteWordWrapperList == null) {
            mCurrentWrapperList = mCurrentAction
                    .getCurrentRecite(SharedPrefsUtil.getInt(this, SharePreferenceConsts.DAY_TARGET_NUMBER, 50));
            List<WordWrapper> wordWrapperList = mWordAction.getWordByName(WordUtil.getWordNameListFromCurrentWrapper(mCurrentWrapperList));
            setReciteWordWrapperListData(wordWrapperList);
        }
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.study_function_recite);
        mViewPager = findViewById(R.id.recite_view_pager);
        mViewPagerAdapter = new ReciteViewPagerAdapter(getSupportFragmentManager(), mReciteWordWrapperList);
        mViewPagerAdapter.setNextFragmentItem(new ReciteViewPagerAdapter.ViewPagerNextFragmentItem() {
            @Override
            public void nextItem(int position) {
                if (position != mReciteWordWrapperList.size() - 1) {
                    mViewPager.setCurrentItem(position + 1, true);
                } else {
                    Intent intent = new Intent(ReciteActivity.this, NextReciteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("reciteWordWrapperList", (Serializable) mReciteWordWrapperList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageMargin(10);
        mViewPager.setPageTransformer(true, new ZoomOutPagerTransformer());
    }

    @Override
    public void initClick() {

    }

    private void setReciteWordWrapperListData(List<WordWrapper> wordWrapperList) {
        mReciteWordWrapperList = new ArrayList<>();
        for (int i = 0; i < wordWrapperList.size(); i++) {
            ReciteWordWrapper wrapper = new ReciteWordWrapper(wordWrapperList.get(i),mCurrentWrapperList.get(i), View.GONE, WordEnums.NULL_SURE);
            mReciteWordWrapperList.add(wrapper);
        }
    }

}
