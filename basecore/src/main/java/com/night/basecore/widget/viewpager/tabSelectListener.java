package com.night.basecore.widget.viewpager;


import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class tabSelectListener implements TabLayout.OnTabSelectedListener {
    private final ViewPager mViewPager;

    public tabSelectListener(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        // No-op
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        // No-op
    }
}
