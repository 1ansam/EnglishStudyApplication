package com.night.app.business.study.recite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.business.study.recite.adapter.ReciteWordTranslationRecyclerViewAdapter;
import com.night.app.consts.enums.WordEnums;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.utils.StringUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.model.wrapper.recite.ReciteWordWrapper;

public class ReciteFragment extends BaseFragment {
    private ImageView                                mIvCollect;

    private ImageView                                mIvSureState;

    private TextView                                 mTvWordName;

    private TextView                                 mTvWordPhEn;

    private TextView                                 mTvWordPhAm;

    private ImageView                                mIvWordPhEnMp3;

    private ImageView                                mIvWordPhAmMp3;

    private RecyclerView                             mRecyclerViewWordTranslation;

    private LinearLayout mLayoutWordTranslationView;

    private ImageView                                mIvEye;

    private Button                                   mBtnNotSure;

    private Button                                   mBtnSure;

    private ReciteWordTranslationRecyclerViewAdapter mRecyclerViewTranslationAdapter;

    private ReciteWordWrapper                        mReciteWordWrapper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_recite_fragment, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mIvCollect = view.findViewById(R.id.recite_iv_collect);
        mIvSureState = view.findViewById(R.id.recite_iv_sure_state);
        mTvWordName = view.findViewById(R.id.recite_tv_word_name);
        mTvWordPhEn = view.findViewById(R.id.recite_tv_word_ph_en);
        mIvWordPhEnMp3 = view.findViewById(R.id.recite_iv_word_ph_en_mp3);
        mTvWordPhAm = view.findViewById(R.id.recite_tv_word_ph_am);
        mIvWordPhAmMp3 = view.findViewById(R.id.recite_iv_word_ph_am_mp3);
        mRecyclerViewWordTranslation = view.findViewById(R.id.recite_recycler_view_word_translation);
        mRecyclerViewTranslationAdapter = new ReciteWordTranslationRecyclerViewAdapter(getContext(),
                mReciteWordWrapper.getWordWrapper().getWordTranslationWrapperList());
        mRecyclerViewWordTranslation.setAdapter(mRecyclerViewTranslationAdapter);
        mRecyclerViewWordTranslation
                .setLayoutManager(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mLayoutWordTranslationView = view.findViewById(R.id.recite_layout_main_word_view);
        mIvEye = view.findViewById(R.id.recite_iv_eye);
        mBtnNotSure = view.findViewById(R.id.recite_btn_not_sure);
        mBtnSure = view.findViewById(R.id.recite_btn_sure);

        mTvWordName.setText(mReciteWordWrapper.getWordWrapper().getWordName());
        mTvWordPhEn.setText("英/" + mReciteWordWrapper.getWordWrapper().getWordPhEn() + "/");
        mTvWordPhAm.setText("美/" + mReciteWordWrapper.getWordWrapper().getWordPhAm() + "/");
        mIvWordPhEnMp3.setTag(mReciteWordWrapper.getWordWrapper().getWordPhEnMp3());
        mIvWordPhAmMp3.setTag(mReciteWordWrapper.getWordWrapper().getWordPhAmMp3());

        setWordCollectState();
        setWordStateView();
        setTranslationView();
    }



    @Override
    public void initClick() {
        mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mReciteWordWrapper.getWordCollectState()==WordEnums.COLLECTED_STATE){
                    mIvCollect.setImageResource(R.mipmap.icon_star);
                    mReciteWordWrapper.setWordCollectState(WordEnums.COLLECTED_STATE);
                }else{
                    mIvCollect.setImageResource(R.mipmap.icon_star_fill);
                    mReciteWordWrapper.setWordCollectState(WordEnums.NOT_COLLECTED_STATE);
                }
            }
        });
        mIvWordPhEnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhEnMp3.getTag();
                if (!StringUtil.isEmpty(mp3Url)) {
                    MediaPlayerUtil.play(getContext(), mp3Url);
                }
            }
        });

        mIvWordPhAmMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhAmMp3.getTag();
                if (!StringUtil.isEmpty(mp3Url)) {
                    MediaPlayerUtil.play(getContext(), mp3Url);
                }
            }
        });

        mLayoutWordTranslationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTranslationState();
            }
        });

        mBtnNotSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvSureState.setVisibility(View.VISIBLE);
                mIvSureState.setBackgroundResource(R.mipmap.icon_flower_bad);
                mReciteWordWrapper.setWordReciteState(WordEnums.NOT_SURE_STATE);
            }
        });

        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvSureState.setVisibility(View.VISIBLE);
                mIvSureState.setBackgroundResource(R.mipmap.icon_flower_red);
                mReciteWordWrapper.setWordReciteState(WordEnums.SURE_STATE);
            }
        });
    }

    public void setReciteWordWrapper(ReciteWordWrapper mReciteWordWrapper) {
        this.mReciteWordWrapper = mReciteWordWrapper;
    }

    /**
     * s设置单词是否被收藏标记
     */
    private void setWordCollectState() {
        if(mReciteWordWrapper.getWordCollectState()==WordEnums.COLLECTED_STATE){
            mIvCollect.setImageResource(R.mipmap.icon_star_fill);
        }else{
            mIvCollect.setImageResource(R.mipmap.icon_star);
        }
    }

    /**
     * 设置单词是否确定或者不确定标记
     */
    private void setWordStateView() {
        switch (mReciteWordWrapper.getWordReciteState()) {
            case WordEnums.NULL_STATE:
                mIvSureState.setVisibility(View.GONE);
                break;
            case WordEnums.NOT_SURE_STATE:
                mIvSureState.setVisibility(View.VISIBLE);
                mIvSureState.setImageResource(R.mipmap.icon_flower_bad);
                break;
            case WordEnums.SURE_STATE:
                mIvSureState.setVisibility(View.VISIBLE);
                mIvSureState.setImageResource(R.mipmap.icon_flower_red);
                break;
        }
    }

    /**
     * 设置单词翻译可见性
     */
    private void setTranslationView() {
        if (mReciteWordWrapper.getTranslationVisibilityState() == View.GONE) {
            setTranslationGone();
        } else {
            setTranslationVisible();
        }
    }

    /**
     * 点击事件下单词翻译可见性设置
     */
    private void changeTranslationState() {
        if (mReciteWordWrapper.getTranslationVisibilityState() == View.GONE) {
            setTranslationVisible();
            mReciteWordWrapper.setTranslationVisibilityState(View.VISIBLE);
        } else {
            setTranslationGone();
            mReciteWordWrapper.setTranslationVisibilityState(View.GONE);
        }
    }

    private void setTranslationVisible(){
        mIvEye.setImageResource(R.mipmap.icon_eye_open);
        mRecyclerViewWordTranslation.setVisibility(View.VISIBLE);
        mBtnNotSure.setVisibility(View.VISIBLE);
        mBtnSure.setVisibility(View.VISIBLE);
    }

    private void setTranslationGone(){
        mIvEye.setImageResource(R.mipmap.icon_eye_close);
        mRecyclerViewWordTranslation.setVisibility(View.GONE);
        mBtnNotSure.setVisibility(View.GONE);
        mBtnSure.setVisibility(View.GONE);
    }
}
