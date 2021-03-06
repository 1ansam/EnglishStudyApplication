package com.night.app.business.study.select.word;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.night.api.business.database.BookAction;
import com.night.api.business.database.BookActionImpl;
import com.night.api.business.database.BookWordAction;
import com.night.api.business.database.BookWordActionImpl;
import com.night.api.business.database.CurrentAction;
import com.night.api.business.database.CurrentActionImpl;
import com.night.api.business.database.LogAction;
import com.night.api.business.database.LogActionImpl;
import com.night.api.business.okhttp.WordApiAction;
import com.night.api.business.okhttp.WordApiActionImpl;
import com.night.api.consts.MessageConsts;
import com.night.api.consts.SharePreferenceConsts;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.study.select.word.adapter.SelectWordFragmentAdapter;
import com.night.app.business.study.select.word.fragment.SelectedWordFragment;
import com.night.app.business.study.select.word.fragment.UnSelectedWordFragment;
import com.night.app.common.util.ProgressUtil;
import com.night.app.common.util.TitleInitUtil;
import com.night.app.consts.BusinessConsts;
import com.night.basecore.utils.DateUtil;
import com.night.basecore.utils.NumberUtil;
import com.night.basecore.utils.SharedPrefsUtil;
import com.night.basecore.utils.ToastUtil;
import com.night.basecore.widget.viewpager.tabSelectListener;
import com.night.model.wrapper.database.BookWordWrapper;
import com.night.model.wrapper.database.LogWrapper;
import com.night.model.wrapper.database.WordStateWrapper;

import java.util.ArrayList;
import java.util.List;

public class SelectWordActivity extends BaseActivity {
    private TabLayout                 mTabLayout;

    private ViewPager                 mViewPager;

    private LinearLayout              mLayoutListSelect;

    private LinearLayout              mLayoutRandomSelect;

    private LinearLayout              mLayoutCommit;

    public TextView                   mTvSelectedNumber;

    private TextView                  mTvRecommendNumber;

    private UnSelectedWordFragment    mUnSelectedWordFragment;

    private SelectedWordFragment      mSelectedWordFragment;

    private String                    mBookLibraryName;

    private String                    mBookChineseName;

    private SelectWordFragmentAdapter mFragmentAdapter;

    private BookWordAction            mBookWordAction;

    private BookAction                mBookAction;

    private CurrentAction             mCurrentAction;

    private WordApiAction             mWordApiAction;

    private LogAction                 mLogAction;

    private List<WordStateWrapper>    mUnSelectedWordStateList;

    private List<WordStateWrapper>    mSelectedWordStateList;

    private int                       mRecommendNumber;

    public static final int           FINISH_RESULE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_word);
        Intent intent = getIntent();
        mBookLibraryName = intent.getStringExtra(BusinessConsts.BOOK_LIBRARY_NAME);
        mBookChineseName = intent.getStringExtra(BusinessConsts.BOOK_CHINESE_NAME);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initData() {
        mBookWordAction = new BookWordActionImpl(this);
        mBookAction = new BookActionImpl(this);
        mCurrentAction = new CurrentActionImpl(this);
        mWordApiAction = new WordApiActionImpl(this);
        mLogAction = new LogActionImpl(this);

        BookWordWrapper bookWordWrapper = mBookWordAction.getBookWordWrapper(mBookLibraryName);
        mUnSelectedWordStateList = bookWordWrapper.getUnSelectedWordList();
        mSelectedWordStateList = bookWordWrapper.getSelectedWordList();
        mUnSelectedWordFragment = new UnSelectedWordFragment(mUnSelectedWordStateList);
        mSelectedWordFragment = new SelectedWordFragment(mSelectedWordStateList);

        LogWrapper logWrapper = mLogAction.getLogWrapper(DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number));
        int logNumber = logWrapper.getNewNumber() + logWrapper.getReviseNumber();
        int reciteNumber = mCurrentAction.getCurrentRecite(Integer.MAX_VALUE).size();
        mRecommendNumber = SharedPrefsUtil.getInt(this, SharePreferenceConsts.DAY_TARGET_NUMBER, 50) - logNumber
                - reciteNumber;
//        LogUtil.d("logNumber "+logNumber+"   reciteNumber "+reciteNumber);
        if (mRecommendNumber < 0) {
            mRecommendNumber = 0;
        }
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, mBookChineseName);
        mTabLayout = findViewById(R.id.select_word_tablayout);
        mViewPager = findViewById(R.id.select_word_view_pager);
        mLayoutListSelect = findViewById(R.id.select_word_layout_list_select);
        mLayoutRandomSelect = findViewById(R.id.select_word_layout_random_select);
        mLayoutCommit = findViewById(R.id.select_word_layout_commit);
        mTvSelectedNumber = findViewById(R.id.select_word_tv_selected_number);
        mTvRecommendNumber = findViewById(R.id.select_word_tv_recommend_number);
        initTab();
        initPager();
        mTvRecommendNumber.setText(String.valueOf("建议选" + mRecommendNumber));
    }

    @Override
    public void initClick() {
        mLayoutListSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearWordState();
                for (int i = 0; i < mRecommendNumber; i++) {
                    mUnSelectedWordStateList.get(i).setSelected(true);
                }
                mUnSelectedWordFragment.getRecyclerAdapter().refresh(mUnSelectedWordStateList);
                mTvSelectedNumber.setText("已选" + mRecommendNumber);
                mUnSelectedWordFragment.setSelectedNumber(mRecommendNumber);
            }
        });

        mLayoutRandomSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearWordState();
                List<Integer> list = NumberUtil.getRandomList(0, mUnSelectedWordStateList.size(), mRecommendNumber);
                for (int i = 0; i < list.size(); i++) {
                    mUnSelectedWordStateList.get(list.get(i)).setSelected(true);
                }
                mUnSelectedWordFragment.getRecyclerAdapter().refresh(mUnSelectedWordStateList);
                mTvSelectedNumber.setText("已选" + mRecommendNumber);
                mUnSelectedWordFragment.setSelectedNumber(mRecommendNumber);
            }
        });
        mLayoutCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtil.showProgress(SelectWordActivity.this, View.VISIBLE);
                final List<String> commitWordNameList = new ArrayList<>();
                for (int i = 0; i < mUnSelectedWordStateList.size(); i++) {
                    if (mUnSelectedWordStateList.get(i).isSelected()) {
                        commitWordNameList.add(mUnSelectedWordStateList.get(i).getWordName());
                    }
                }

                mWordApiAction.setWordApiListener(new WordApiAction.WordApiListener<String>() {
                    @Override
                    public void doAfterSuccess(String result) {
                        // 更新bookWord
                        mBookWordAction.updateBookWordState(commitWordNameList, BookWordActionImpl.WORD_STATE_SELECYED);
                        // 更新book
                        mBookAction
                                .updateWordSelectedNumber(mBookWordAction.getWordExistInBookNumber(commitWordNameList));
                        // 更新current
                        mCurrentAction.insertIntoCurrent(commitWordNameList);
                        setResult(FINISH_RESULE_CODE);
                        finish();
                    }

                    @Override
                    public void doAfterFailed(String result) {
                        ProgressUtil.showProgress(SelectWordActivity.this, View.GONE);
                        ToastUtil.showLongToast(getBaseContext(), MessageConsts.InternetConsts.INTERNET_NOT_AVAILABLE);
                    }
                });

                // 网络请求单词数据并写入word表
                mWordApiAction.getWordEntityList(commitWordNameList);

            }
        });
    }

    private void initTab() {
        String unSelectedNumber = mUnSelectedWordStateList.size() + "\n未完成";
        String selectedNumber = mSelectedWordStateList.size() + "\n已完成";
        String[] tabTextArr = { unSelectedNumber, selectedNumber };
        for (int i = 0; i < 2; i++) {
            View tabItem = getLayoutInflater().inflate(R.layout.item_select_word_tab_layout, null);
            TextView text = tabItem.findViewById(R.id.select_word_tv_tab_layout_item);
            text.setText(tabTextArr[i]);
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabItem));
        }
    }

    private void initPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(mUnSelectedWordFragment);
        fragmentList.add(mSelectedWordFragment);

        mFragmentAdapter = new SelectWordFragmentAdapter(this, fragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(1);

        mTabLayout.addOnTabSelectedListener(new tabSelectListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void clearWordState() {
        for (int i = 0; i < mUnSelectedWordStateList.size(); i++) {
            if (mUnSelectedWordStateList.get(i).isSelected()) {
                mUnSelectedWordStateList.get(i).setSelected(false);
            }
        }
    }
}
