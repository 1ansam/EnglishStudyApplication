package com.night.app.business.study.recite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.business.study.recite.adapter.NextReciteItemRecyclerViewAdapter;
import com.night.app.common.title.TitleInitUtil;
import com.night.app.consts.enums.WordEnums;
import com.night.basecore.utils.LogUtil;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.basecore.widget.recyclerview.DividerGridItemDecoration;
import com.night.model.wrapper.recite.ReciteWordWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuwentao
 */
public class NextReciteActivity extends BaseActivity {
    private TextView                          mTvCompletedNumber;

    private TextView                          mTvSurplusNumber;

    private RecyclerView                      mRecyclerViewWordInfo;

    private Button                            mBtnRandomTest;

    private Button                            mBtnNextRecite;

    private CheckBox                          mCheckBoxEye;

    private NextReciteItemRecyclerViewAdapter mItemRecyclerViewAdapter;

    private List<ReciteWordWrapper>           mReciteWordWrapperList;

    private List<ReciteWordWrapper>           mSureWordWrapperList;

    private List<ReciteWordWrapper>           mNotSureWrapperList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_recite);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.next_recite);
        mTvCompletedNumber = findViewById(R.id.next_recite_tv_completed_number);
        mTvSurplusNumber = findViewById(R.id.next_recite_tv_surplus_number);
        mRecyclerViewWordInfo = findViewById(R.id.next_recite_recycler_word_list);
        mBtnRandomTest = findViewById(R.id.next_recite_btn_random_test);
        mBtnNextRecite = findViewById(R.id.next_recite_btn_next_recite);
        mCheckBoxEye = findViewById(R.id.next_recite_check_box_eye);
        mItemRecyclerViewAdapter = new NextReciteItemRecyclerViewAdapter(this, mReciteWordWrapperList);

        mTvCompletedNumber.setText(String.valueOf(mSureWordWrapperList.size()));
        mTvSurplusNumber.setText(String.valueOf(mNotSureWrapperList.size()));
        mRecyclerViewWordInfo.setAdapter(mItemRecyclerViewAdapter);
        mRecyclerViewWordInfo
                .setLayoutManager(new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewWordInfo.addItemDecoration(new DividerGridItemDecoration(this));

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

            }
        });
        mItemRecyclerViewAdapter
                .setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ReciteWordWrapper>() {
                    @Override
                    public void onItemClick(View view, ReciteWordWrapper reciteWordWrapper, int position) {
                        int id = view.getId();
                        switch (id) {
                        case R.id.next_recite_iv_horn:
                            String url = (String) view.getTag();
                            LogUtil.d(url);
                            MediaPlayerUtil.playHornAnimation(NextReciteActivity.this, (ImageView) view, url);
                            break;
                        default:
                            break;
                        }
                    }
                });

        mCheckBoxEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCheckBoxEye.isChecked()){
                    mItemRecyclerViewAdapter.setWordTranslationVisibility(View.VISIBLE);
                    mItemRecyclerViewAdapter.notifyDataSetChanged();
                }else{
                    mItemRecyclerViewAdapter.setWordTranslationVisibility(View.GONE);
                    mItemRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initData() {
        mReciteWordWrapperList = (List<ReciteWordWrapper>) getIntent().getSerializableExtra("reciteWordWrapperList");
        mSureWordWrapperList = new ArrayList<>();
        mNotSureWrapperList = new ArrayList<>();
        for (int i = 0; i < mReciteWordWrapperList.size(); i++) {
            if (mReciteWordWrapperList.get(i).getWordReciteState() == WordEnums.SURE) {
                mSureWordWrapperList.add(mReciteWordWrapperList.get(i));
            } else {
                mNotSureWrapperList.add(mReciteWordWrapperList.get(i));
            }
        }
    }

}
