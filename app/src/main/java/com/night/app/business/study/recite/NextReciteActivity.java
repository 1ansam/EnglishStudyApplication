package com.night.app.business.study.recite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.api.consts.enums.WordEnums;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.common.adapter.WordInfoRecyclerAdapter;
import com.night.app.common.util.TitleInitUtil;
import com.night.app.common.util.WordDisplayUtil;
import com.night.basecore.utils.LogUtil;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.basecore.widget.recyclerview.DividerGridItemDecoration;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuwentao
 */
public class NextReciteActivity extends BaseActivity {
    private TextView                mTvCompletedNumber;

    private TextView                mTvSurplusNumber;

    private RecyclerView            mRecyclerViewWordInfo;

    private Button                  mBtnRandomTest;

    private Button                  mBtnNextRecite;

    private WordDisplayUtil         mWordDisplayUtil;

    private WordInfoRecyclerAdapter mWordInfoRecyclerAdapter;

    private List<ReciteWordWrapper> mReciteWordWrapperList;

    private List<ReciteWordWrapper> mSureWordWrapperList;

    private List<ReciteWordWrapper> mNotSureWrapperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_recite);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initData() {
        mReciteWordWrapperList = (List<ReciteWordWrapper>) getIntent().getSerializableExtra("reciteWordWrapperList");
        mSureWordWrapperList = new ArrayList<>();
        mNotSureWrapperList = new ArrayList<>();
        for (int i = 0; i < mReciteWordWrapperList.size(); i++) {
            ReciteWordWrapper wrapper = mReciteWordWrapperList.get(i);
            if (wrapper.getWordReciteState() == WordEnums.SURE) {
                mSureWordWrapperList.add(wrapper);
            } else {
                wrapper.setWordReciteState(WordEnums.NULL_SURE);
                wrapper.setTranslationVisibilityState(View.GONE);
                mNotSureWrapperList.add(wrapper);
            }
        }
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.next_recite);
        mTvCompletedNumber = findViewById(R.id.next_recite_tv_completed_number);
        mTvSurplusNumber = findViewById(R.id.next_recite_tv_surplus_number);
        mRecyclerViewWordInfo = findViewById(R.id.next_recite_recycler_word_list);
        mBtnRandomTest = findViewById(R.id.next_recite_btn_random_test);
        mBtnNextRecite = findViewById(R.id.next_recite_btn_next_recite);
        mWordDisplayUtil = new WordDisplayUtil(this, false, true, true);
        mWordInfoRecyclerAdapter = new WordInfoRecyclerAdapter(this, mSureWordWrapperList);

        mTvCompletedNumber.setText(String.valueOf(mSureWordWrapperList.size()));
        mTvSurplusNumber.setText(String.valueOf(mNotSureWrapperList.size()));
        mRecyclerViewWordInfo.setAdapter(mWordInfoRecyclerAdapter);
        mRecyclerViewWordInfo
                .setLayoutManager(new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewWordInfo.addItemDecoration(new DividerGridItemDecoration(this));
        if (mNotSureWrapperList.isEmpty()) {
            mBtnNextRecite.setText(R.string.sign_in);
        }
    }

    @Override
    public void initClick() {
        mBtnRandomTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtnNextRecite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mNotSureWrapperList.isEmpty()) {

                } else {
                    Intent intent = new Intent(NextReciteActivity.this, ReciteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("reciteWordWrapperList", (Serializable) mNotSureWrapperList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
        mWordInfoRecyclerAdapter
                .setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ReciteWordWrapper>() {
                    @Override
                    public void onItemClick(View view, ReciteWordWrapper reciteWordWrapper, int position) {
                        int id = view.getId();
                        switch (id) {
                        case R.id.word_list_info_iv_horn:
                            String url = (String) view.getTag();
                            LogUtil.d(url);
                            MediaPlayerUtil.playHornAnimation(NextReciteActivity.this, (ImageView) view, url);
                            break;
                        default:
                            break;
                        }
                    }
                });

        mWordDisplayUtil.getCheckBoxEye().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWordDisplayUtil.getCheckBoxEye().isChecked()) {
                    mWordInfoRecyclerAdapter.setWordTranslationVisibility(View.VISIBLE);
                    mWordInfoRecyclerAdapter.notifyDataSetChanged();
                } else {
                    mWordInfoRecyclerAdapter.setWordTranslationVisibility(View.GONE);
                    mWordInfoRecyclerAdapter.notifyDataSetChanged();
                }
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

}
