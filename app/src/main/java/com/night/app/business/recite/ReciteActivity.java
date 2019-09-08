package com.night.app.business.recite;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.night.app.R;
import com.night.app.business.recite.adapter.ReciteViewPagerAdapter;
import com.night.basecore.widget.viewpager.ZoomOutPagerTransformer;
import com.night.app.base.activity.BaseActivity;
import com.night.business.wrapper.database.ReciteWordWrapper;

import java.util.ArrayList;
import java.util.List;

public class ReciteActivity extends BaseActivity {
    private ViewPager              mViewPager;

    private ReciteViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        mViewPager=findViewById(R.id.recite_view_pager);
        List<ReciteWordWrapper> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(new ReciteWordWrapper());
        }
        mViewPagerAdapter=new ReciteViewPagerAdapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setPageMargin(10);
        mViewPager.setPageTransformer(true,new ZoomOutPagerTransformer());
    }

    @Override
    public void initClick() {

    }
}
