package com.night.app.common.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.night.app.common.fragment.WordInfoFragment;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.util.List;

public class WordInfoViewPagerAdapter extends FragmentStatePagerAdapter {
    private WordInfoFragment          mFragment;

    private List<ReciteWordWrapper>   mWrapperList;

    private ViewPagerNextFragmentItem mNextFragmentItem;

    /**
     * 用来设置在不同情况下是否显示 收藏图标、确定不确定按钮
     */
    private boolean                   isSpecialViewVisible = true;

    public WordInfoViewPagerAdapter(FragmentManager fm, List<ReciteWordWrapper> list) {
        super(fm);
        mWrapperList = list;
    }

    @Override
    public Fragment getItem(final int position) {
        mFragment = new WordInfoFragment(mWrapperList.get(position), position, mWrapperList.size());
        mFragment.setSpecialViewVisible(isSpecialViewVisible);
        if (isSpecialViewVisible) {
            mFragment.setNextFragmentItem(new WordInfoFragment.FragmentNextFragmentItemListener() {
                @Override
                public void nextItem() {
                    if (mNextFragmentItem != null) {
                        mNextFragmentItem.nextItem(position);
                    }
                }
            });
        }
        return mFragment;
    }

    @Override
    public int getCount() {
        if (mWrapperList != null) {
            return mWrapperList.size();
        }
        return 0;
    }

    public interface ViewPagerNextFragmentItem {
        void nextItem(int position);
    }

    public void setNextFragmentItem(ViewPagerNextFragmentItem mNextFragmentItem) {
        this.mNextFragmentItem = mNextFragmentItem;
    }

    public void refresh(List<ReciteWordWrapper> list) {
        mWrapperList = list;
        notifyDataSetChanged();
    }

    public void setSpecialViewVisible(boolean specialViewVisible) {
        isSpecialViewVisible = specialViewVisible;
    }





}
