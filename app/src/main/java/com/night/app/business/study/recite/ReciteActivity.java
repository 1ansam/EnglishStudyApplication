package com.night.app.business.study.recite;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.night.api.business.database.CurrentAction;
import com.night.api.business.database.CurrentActionImpl;
import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.study.recite.adapter.ReciteViewPagerAdapter;
import com.night.app.common.title.TitleInitUtil;
import com.night.app.consts.ShareConsts;
import com.night.app.consts.enums.WordEnums;
import com.night.basecore.utils.SharedPrefsUtil;
import com.night.basecore.widget.viewpager.ZoomOutPagerTransformer;
import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.database.WordWrapper;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.util.ArrayList;
import java.util.List;

public class ReciteActivity extends BaseActivity {
    private ViewPager               mViewPager;

    private ReciteViewPagerAdapter  mViewPagerAdapter;

    private CurrentAction           mCurrentAction;

    private WordAction              mWordAction;

    private List<CurrentWrapper>    mCurrentWrapperList;

    private List<WordWrapper>       mWordWrapperList;

    private List<ReciteWordWrapper> mReciteWordWrapperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.study_function_recite);
        mViewPager = findViewById(R.id.recite_view_pager);

        mCurrentAction = new CurrentActionImpl(this);
        mWordAction = new WordActionImpl(this);
        mCurrentWrapperList = mCurrentAction
                .getCurrentRecite(SharedPrefsUtil.getInt(this, ShareConsts.DAY_TARGET_NUMBER, 50));
        mWordWrapperList = mWordAction.getWordByCurrent(mCurrentWrapperList);
        setReciteWordWrapperListData();

        mViewPagerAdapter = new ReciteViewPagerAdapter(getSupportFragmentManager(), mReciteWordWrapperList);
        mViewPagerAdapter.setNextFragmentItem(new ReciteViewPagerAdapter.ViewPagerNextFragmentItem() {
            @Override
            public void nextItem(int position) {
                if(position!=mReciteWordWrapperList.size()){
                    mViewPager.setCurrentItem(position+1,true);
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

    private void setReciteWordWrapperListData() {
        mReciteWordWrapperList = new ArrayList<>();
        for (int i = 0; i < mWordWrapperList.size(); i++) {
            ReciteWordWrapper wrapper = new ReciteWordWrapper(mWordWrapperList.get(i), View.GONE, WordEnums.NULL_SURE);
            mReciteWordWrapperList.add(wrapper);
        }
    }
}
