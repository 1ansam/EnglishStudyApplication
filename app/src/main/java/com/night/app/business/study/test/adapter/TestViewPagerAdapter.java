package com.night.app.business.study.test.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.night.app.business.study.test.fragment.TestSelectWordFragment;
import com.night.model.wrapper.database.TestWrapper;

import java.util.List;

public class TestViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<TestWrapper>                    mWrapperList;

    private TestSelectWordFragment.ScoreListener mScoreListener;

    public TestViewPagerAdapter(FragmentManager fm, List<TestWrapper> wrapperList) {
        super(fm);
        this.mWrapperList = wrapperList;
    }

    @Override
    public Fragment getItem(int position) {
        TestSelectWordFragment fragment = new TestSelectWordFragment();
        fragment.setTestWrapper(mWrapperList.get(position));
        fragment.setScoreListener(mScoreListener);
        return fragment;
    }

    @Override
    public int getCount() {
        if (mWrapperList != null) {
            return mWrapperList.size();
        }
        return 0;
    }


    public void setScoreListener(TestSelectWordFragment.ScoreListener mScoreListener) {
        this.mScoreListener = mScoreListener;
    }
}
