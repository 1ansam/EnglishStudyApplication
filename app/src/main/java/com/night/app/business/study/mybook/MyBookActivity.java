package com.night.app.business.study.mybook;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.api.consts.enums.WordEnums;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.common.adapter.WordInfoRecyclerAdapter;
import com.night.app.common.adapter.WordInfoViewPagerAdapter;
import com.night.app.common.util.TitleInitUtil;
import com.night.app.common.util.WordDisplayUtil;
import com.night.basecore.utils.LogUtil;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.basecore.widget.recyclerview.DividerGridItemDecoration;
import com.night.basecore.widget.viewpager.ZoomOutPagerTransformer;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.util.List;

public class MyBookActivity extends BaseActivity {
    private RecyclerView             mRecyclerWordListInfo;

    private WordDisplayUtil          mWordDisplayUtil;

    private ViewPager                mViewPagerWordPagerInfo;

    private WordInfoViewPagerAdapter mWordInfoViewPagerAdapter;

    private WordInfoRecyclerAdapter  mWordInfoRecyclerAdapter;

    private WordAction               mWordAvtion;

    private List<ReciteWordWrapper>  mReciteWordWrapperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initData() {
        mWordAvtion = new WordActionImpl(this);
        mReciteWordWrapperList = mWordAvtion.getWordByState(WordEnums.COLLECTED);
        setReciteWrapperListTranslationState(View.VISIBLE);
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.study_function_my_word_book);
        mRecyclerWordListInfo = findViewById(R.id.my_book_recycler_word_list);
        mWordInfoRecyclerAdapter = new WordInfoRecyclerAdapter(this, mReciteWordWrapperList);
        mRecyclerWordListInfo.setAdapter(mWordInfoRecyclerAdapter);
        mRecyclerWordListInfo
                .setLayoutManager(new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerWordListInfo.addItemDecoration(new DividerGridItemDecoration(this));

        mWordDisplayUtil = new WordDisplayUtil(this, true, true, true);
        mViewPagerWordPagerInfo = findViewById(R.id.my_book_view_pager_word_info);
        mWordInfoViewPagerAdapter = new WordInfoViewPagerAdapter(getSupportFragmentManager(), mReciteWordWrapperList);
        mWordInfoViewPagerAdapter.setSpecialViewVisible(false);
        mViewPagerWordPagerInfo.setAdapter(mWordInfoViewPagerAdapter);
        mViewPagerWordPagerInfo.setOffscreenPageLimit(2);
        mViewPagerWordPagerInfo.setPageMargin(10);
        mViewPagerWordPagerInfo.setPageTransformer(true, new ZoomOutPagerTransformer());
    }

    @Override
    public void initClick() {
        mWordInfoRecyclerAdapter
                .setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ReciteWordWrapper>() {
                    @Override
                    public void onItemClick(View view, ReciteWordWrapper reciteWordWrapper, int position) {
                        int id = view.getId();
                        switch (id) {
                        case R.id.word_list_info_iv_horn:
                            String url = (String) view.getTag();
                            LogUtil.d(url);
                            MediaPlayerUtil.playHornAnimation(MyBookActivity.this, (ImageView) view, url);
                            break;
                        default:
                            break;
                        }
                    }
                });

        mWordDisplayUtil.getCheckBoxDisplayStyle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWordDisplayUtil.getCheckBoxDisplayStyle().isChecked()) {
                    mRecyclerWordListInfo.setVisibility(View.VISIBLE);
                    mViewPagerWordPagerInfo.setVisibility(View.GONE);
                } else {
                    mRecyclerWordListInfo.setVisibility(View.GONE);
                    mViewPagerWordPagerInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        mWordDisplayUtil.getCheckBoxEye().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWordDisplayUtil.getCheckBoxEye().isChecked()) {
                    mWordInfoRecyclerAdapter.setWordTranslationVisibility(View.VISIBLE);
                    setReciteWrapperListTranslationState(View.VISIBLE);
                } else {
                    mWordInfoRecyclerAdapter.setWordTranslationVisibility(View.GONE);
                    setReciteWrapperListTranslationState(View.GONE);
                }
                mWordInfoRecyclerAdapter.notifyDataSetChanged();

                int position = mViewPagerWordPagerInfo.getCurrentItem();
                mWordInfoViewPagerAdapter = new WordInfoViewPagerAdapter(getSupportFragmentManager(),
                        mReciteWordWrapperList);
                mWordInfoViewPagerAdapter.setSpecialViewVisible(false);
                mViewPagerWordPagerInfo.setAdapter(mWordInfoViewPagerAdapter);
                mViewPagerWordPagerInfo.setCurrentItem(position);
            }
        });

        mWordDisplayUtil.getCheckBoxWordPh().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWordInfoRecyclerAdapter.getWordPhType() == WordEnums.WORD_PH_EN) {
                    mWordInfoRecyclerAdapter.setWordPhType(WordEnums.WORD_PH_AM);
                    mWordInfoRecyclerAdapter.notifyDataSetChanged();
                    mWordDisplayUtil.getCheckBoxWordPh().setText(R.string.am);
                } else {
                    mWordInfoRecyclerAdapter.setWordPhType(WordEnums.WORD_PH_EN);
                    mWordInfoRecyclerAdapter.notifyDataSetChanged();
                    mWordDisplayUtil.getCheckBoxWordPh().setText(R.string.en);
                }
            }
        });
    }

    private void setReciteWrapperListTranslationState(int state) {
        for (int i = 0; i < mReciteWordWrapperList.size(); i++) {
            mReciteWordWrapperList.get(i).setTranslationVisibilityState(state);
        }
    }
}
