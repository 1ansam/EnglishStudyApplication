package com.night.app.business.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.night.app.R;
import com.night.app.business.account.HomeAccountFragment;
import com.night.app.business.activity.HomeActivityFragment;
import com.night.app.business.first.HomeFirstFragment;
import com.night.app.business.home.adapter.HomeFragmentAdapter;
import com.night.app.business.study.HomeStudyFragment;
import com.night.basecore.widget.viewpager.tabSelectListener;
import com.night.app.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private ViewPager           mViewPager;

    private TabLayout           mTabLayout;

    private HomeFragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.home_page_container);
        mTabLayout = findViewById(R.id.home_tab_layout);
        initTab();
        initPager();
    }

    @Override
    public void initClick() {

    }

    private void initTab() {
        int[] iconsIdArr=new int[]{R.drawable.home_tabbar_first_btn,R.drawable.home_tabbar_study_btn,R.drawable.home_tabbar_activity_btn,R.drawable.home_tabbar_community_btn};
        int[] tabTextIdArr=new int[]{R.string.home_first,R.string.home_study,R.string.home_activity,R.string.home_account};
        for(int i=0;i<iconsIdArr.length;i++){
            View tabItem =getLayoutInflater().inflate(R.layout.item_home_tab_bar,null);
            ImageView icon=tabItem.findViewById(R.id.home_iv_tab_bar_icon);
            TextView  text=tabItem.findViewById(R.id.home_tv_tab_bar_text);
            icon.setImageResource(iconsIdArr[i]);
            text.setText(getText(tabTextIdArr[i]));
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabItem));
        }
    }

    private void initPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFirstFragment());
        fragmentList.add(new HomeStudyFragment());
        fragmentList.add(new HomeActivityFragment());
        fragmentList.add(new HomeAccountFragment());

        mFragmentAdapter = new HomeFragmentAdapter(this, fragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(3);

        mTabLayout.addOnTabSelectedListener(new tabSelectListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

}
