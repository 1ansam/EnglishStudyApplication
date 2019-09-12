package com.night.app.business.study.recite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.business.study.recite.adapter.ReciteWordTranslationRecyclerViewAdapter;

public class ReciteFragment extends BaseFragment {
    private TextView     mTvWordName;

    private TextView     mTvWordPhEn;

    private ImageView    mIvWordPhEnMp3;

    private TextView     mTvWordPhAm;

    private ImageView    mIvWordPhAmMp3;

    private RecyclerView mRecyclerViewWordTranslation;

    private ReciteWordTranslationRecyclerViewAdapter mRecyclerViewTranslationAdapter;

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
        mTvWordName=view.findViewById(R.id.recite_tv_word_name);
        mTvWordPhEn=view.findViewById(R.id.recite_tv_word_ph_en);
        mIvWordPhEnMp3=view.findViewById(R.id.recite_iv_word_ph_en_mp3);
        mTvWordPhAm=view.findViewById(R.id.recite_tv_word_ph_am);
        mIvWordPhAmMp3=view.findViewById(R.id.recite_iv_word_ph_am_mp3);
        mRecyclerViewWordTranslation=view.findViewById(R.id.recite_recycler_view_word_translation);

    }

    @Override
    public void initClick() {

    }
}
