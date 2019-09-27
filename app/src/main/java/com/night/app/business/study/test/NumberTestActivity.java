package com.night.app.business.study.test;

import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.night.api.business.database.TestAction;
import com.night.api.business.database.TestActionImpl;
import com.night.api.consts.SharePreferenceConsts;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.study.test.adapter.NumberTestViewPager;
import com.night.app.business.study.test.adapter.TestViewPagerAdapter;
import com.night.app.business.study.test.fragment.TestSelectWordFragment;
import com.night.app.common.util.TitleInitUtil;
import com.night.basecore.utils.SharedPrefsUtil;
import com.night.basecore.utils.SpannableStringUtil;
import com.night.basecore.utils.TranslateAnimationUtil;
import com.night.model.wrapper.database.TestWrapper;

import java.util.List;

public class NumberTestActivity extends BaseActivity {
    private RelativeLayout       mLayoutTip;

    private LinearLayout         mLayoutSelectWord;

    private RelativeLayout       mLayoutComeOn;

    private TextView             mTvLastTimeTestNumber;

    private TextView             mTvTip;

    private Button               mBtnStartTest;

    private NumberTestViewPager  mViewPagerWord;

    private TextView             mTvScore;

    private TextView             mTvComeOn;

    private Button               mBtnRecordScore;

    private TestViewPagerAdapter mTestWordViewPagerAdapter;

    private List<TestWrapper>    mTestWrapperList;

    private TestAction           mTestAction;

    private int                  mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_test);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initData() {
        mTestAction = new TestActionImpl(this);
        mTestWrapperList = mTestAction.getTestWrapperList();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.study_function_test);
        mLayoutTip = findViewById(R.id.number_test_layout_tip);
        mLayoutSelectWord = findViewById(R.id.number_test_layout_select_word);
        mLayoutComeOn = findViewById(R.id.number_test_layout_come_on);
        mTvLastTimeTestNumber = findViewById(R.id.number_test_tv_last_time_test_number);
        mTvTip = findViewById(R.id.number_test_tv_tip);
        mBtnStartTest = findViewById(R.id.number_test_btn_start_test);
        mViewPagerWord = findViewById(R.id.number_test_view_pager_word);
        mTvScore = findViewById(R.id.number_test_tv_score);
        mTvComeOn = findViewById(R.id.number_test_tv_come_on);
        mBtnRecordScore = findViewById(R.id.number_test_btn_record_this_score);
        mTestWordViewPagerAdapter = new TestViewPagerAdapter(getSupportFragmentManager(), mTestWrapperList);
        mTestWordViewPagerAdapter.setScoreListener(new TestSelectWordFragment.ScoreListener() {
            @Override
            public void doAfterSelect(int score) {
                if (score != 0) {
                    mScore += score;
                    setScore(mScore);
                }
                if (mViewPagerWord.getCurrentItem() != mTestWrapperList.size() - 1) {
                    mViewPagerWord.setCurrentItem(mViewPagerWord.getCurrentItem() + 1);
                } else {
                    int lastTimeNumber = SharedPrefsUtil.getInt(getBaseContext(),
                            SharePreferenceConsts.LAST_TIME_TEST_NUMBER, 0);
                    String comeOnText = String.format(mTvComeOn.getText().toString(),
                            new String[] { String.valueOf(lastTimeNumber), String.valueOf(mScore) });
                    SpannableString spannableString = new SpannableStringUtil(getBaseContext(), comeOnText)
                            .setColor(String.valueOf(lastTimeNumber), R.color.text_red)
                            .setColor(String.valueOf(mScore), R.color.text_red).getSpannableString();
                    mTvComeOn.setText(spannableString);
                    mLayoutSelectWord.setVisibility(View.GONE);
                    mLayoutComeOn.setVisibility(View.VISIBLE);
                }
            }
        });
        mViewPagerWord.setAdapter(mTestWordViewPagerAdapter);

        setLastTimeTestNumberTextView();
        setTipTextView();
        setScore(0);
    }

    @Override
    public void initClick() {
        mBtnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutTip.startAnimation(TranslateAnimationUtil.getHideAnim(500,TranslateAnimationUtil.DIRECTION_RIGHT));
                mLayoutTip.setVisibility(View.GONE);
                mLayoutSelectWord.startAnimation(TranslateAnimationUtil.getShowAnim(500,TranslateAnimationUtil.DIRECTION_RIGHT));
                mLayoutSelectWord.setVisibility(View.VISIBLE);
            }
        });

        mBtnRecordScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsUtil.writeInt(getBaseContext(), SharePreferenceConsts.LAST_TIME_TEST_NUMBER, mScore);
                finish();
            }
        });
    }

    private void setLastTimeTestNumberTextView() {
        int lastTimeNumber = SharedPrefsUtil.getInt(this, SharePreferenceConsts.LAST_TIME_TEST_NUMBER, 0);
        String lastTimeTvText = String.format(mTvLastTimeTestNumber.getText().toString(),
                String.valueOf(lastTimeNumber));
        SpannableString spannableString = new SpannableStringUtil(this, lastTimeTvText)
                .setColor(String.valueOf(lastTimeNumber), R.color.text_red).getSpannableString();
        mTvLastTimeTestNumber.setText(spannableString);
    }

    private void setTipTextView() {
        String tipText = mTvTip.getText().toString();
        SpannableString spannableString = new SpannableStringUtil(this, tipText).setColor("不确定", R.color.text_red)
                .setColor("不认识", R.color.text_red).setColor("要一次性测试完毕才可以记录分数哦！", R.color.text_red).getSpannableString();
        mTvTip.setText(spannableString);
    }

    private void setScore(int score) {
        String scoreText = String.format(getString(R.string.number_test_score), String.valueOf(score));
        SpannableString spannableString = new SpannableStringUtil(this, scoreText)
                .setColor(String.valueOf(score), R.color.text_red).getSpannableString();
        mTvScore.setText(spannableString);
    }
}
