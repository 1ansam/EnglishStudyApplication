package com.night.app.business.startup.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

import com.night.app.R;
import com.night.app.business.startup.login.LoginActivity;
import com.night.basecore.widget.circleslidingindicator.CircleSlidingIndicator;
import com.night.app.base.activity.BaseActivity;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {
    private CircleSlidingIndicator mCircleSlidingIndicator;

    private Button mBtnExperienceImmediately;

    private ArrayList<Integer> mCircleSlidingIndicatorImageResourceIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        mCircleSlidingIndicator = findViewById(R.id.guide_indicator);
        mBtnExperienceImmediately = findViewById(R.id.guide_activity_btn_experience_immediately);

        mCircleSlidingIndicatorImageResourceIdList.add(R.mipmap.guide_activity_circle_sliding_indicator_one);
        mCircleSlidingIndicatorImageResourceIdList.add(R.mipmap.guide_activity_circle_sliding_indicator_two);
        mCircleSlidingIndicatorImageResourceIdList.add(R.mipmap.guide_activity_circle_sliding_indicator_three);
        mCircleSlidingIndicatorImageResourceIdList.add(R.mipmap.guide_activity_circle_sliding_indicator_four);
        mCircleSlidingIndicator.showCircleSlidingIndicatorByImgId(mCircleSlidingIndicatorImageResourceIdList, false);
    }

    @Override
    public void initClick() {
        mCircleSlidingIndicator.getCircleSlidingIndicatorViewPager()
                .addOnPageChangeListener((new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        if (position == mCircleSlidingIndicatorImageResourceIdList.size() - 1) {
                            mBtnExperienceImmediately.setVisibility(View.VISIBLE);
                        } else {
                            mBtnExperienceImmediately.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                }));

        mBtnExperienceImmediately.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
