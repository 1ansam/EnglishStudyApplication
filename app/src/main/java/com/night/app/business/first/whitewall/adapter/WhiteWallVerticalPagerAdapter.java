package com.night.app.business.first.whitewall.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.night.app.business.first.whitewall.fragments.WhiteWallFragment;
import com.night.model.wrapper.first.whitewall.WhiteWallWrapper;

import java.util.List;

public class WhiteWallVerticalPagerAdapter extends FragmentStatePagerAdapter {
    private WhiteWallFragment mFragment = new WhiteWallFragment();

    private List<WhiteWallWrapper> mWrapperList;

    public WhiteWallVerticalPagerAdapter(FragmentManager fm, List<WhiteWallWrapper> list) {
        super(fm);
        mWrapperList = list;
    }

    @Override
    public Fragment getItem(int position) {
        mFragment = new WhiteWallFragment();
        mFragment.setWrapper(mWrapperList.get(position % mWrapperList.size()));
        return mFragment;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

}
