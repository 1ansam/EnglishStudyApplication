package com.night.app.base.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class BaseFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    public BaseFragmentAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity.getSupportFragmentManager());
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments!=null){
            return mFragments.size();
        }
        return 0;
    }
}
