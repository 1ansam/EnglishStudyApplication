package com.night.app.business.study.recite.fragment;

import android.graphics.drawable.AnimationDrawable;
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

import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.business.study.recite.adapter.ReciteWordTranslationRecyclerViewAdapter;
import com.night.app.consts.enums.WordEnums;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.utils.StringUtil;
import com.night.basecore.utils.StyleUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.model.wrapper.recite.ReciteWordWrapper;

/**
 * @author zhuwentao
 */
public class ReciteFragment extends BaseFragment {
    private ImageView                                mIvCollect;

    private ImageView                                mIvSureState;

    private TextView                                 mTvWordName;

    private TextView                                 mTvWordPhEn;

    private TextView                                 mTvWordPhAm;

    private ImageView                                mIvWordPhEnMp3;

    private ImageView                                mIvWordPhAmMp3;

    private RecyclerView                             mRecyclerViewWordTranslation;

    private LinearLayout                             mLayoutWordTranslationView;

    private ImageView                                mIvEye;

    private Button                                   mBtnNotSure;

    private Button                                   mBtnSure;

    private ReciteWordTranslationRecyclerViewAdapter mRecyclerViewTranslationAdapter;

    private ReciteWordWrapper                        mReciteWordWrapper;

    private WordAction                               mWordAction;

    private FragmentNextFragmentItemListener         mNextFragmentItem;

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
        mWordAction = new WordActionImpl(getContext());

        mTvWordName.setText(mReciteWordWrapper.getWordWrapper().getWordName());
        mTvWordPhEn.setText(StyleUtil.getWordPh(mReciteWordWrapper.getWordWrapper().getWordPhEn(),1));
        mTvWordPhAm.setText(StyleUtil.getWordPh(mReciteWordWrapper.getWordWrapper().getWordPhAm(),2));
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
                if (mReciteWordWrapper.getWordWrapper().getWordCollectState() == WordEnums.COLLECTED) {
                    mIvCollect.setImageResource(R.mipmap.icon_star_gray);
                    mReciteWordWrapper.getWordWrapper().setWordCollectState(WordEnums.NOT_COLLECTED);
                    mWordAction.updateWordCollectState(mReciteWordWrapper.getWordWrapper().getWordName(),
                            WordEnums.NOT_COLLECTED);
                } else {
                    mIvCollect.setImageResource(R.mipmap.icon_star_cyan);
                    mReciteWordWrapper.getWordWrapper().setWordCollectState(WordEnums.COLLECTED);
                    mWordAction.updateWordCollectState(mReciteWordWrapper.getWordWrapper().getWordName(),
                            WordEnums.COLLECTED);
                }
            }
        });
        mIvWordPhEnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhEnMp3.getTag();
                playHornAnimation(mIvWordPhEnMp3, mp3Url);
            }
        });

        mIvWordPhAmMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhAmMp3.getTag();
                playHornAnimation(mIvWordPhAmMp3, mp3Url);
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
                mIvSureState.setImageResource(R.mipmap.icon_flower_bad);
                mReciteWordWrapper.setWordReciteState(WordEnums.NOT_SURE);
                if (mNextFragmentItem != null) {
                    mNextFragmentItem.nextItem();
                }
            }
        });

        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvSureState.setVisibility(View.VISIBLE);
                mIvSureState.setImageResource(R.mipmap.icon_flower_good);
                mReciteWordWrapper.setWordReciteState(WordEnums.SURE);
                if (mNextFragmentItem != null) {
                    mNextFragmentItem.nextItem();
                }
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
        if (mReciteWordWrapper.getWordWrapper().getWordCollectState() == WordEnums.COLLECTED) {
            mIvCollect.setImageResource(R.mipmap.icon_star_cyan);
        } else {
            mIvCollect.setImageResource(R.mipmap.icon_star_gray);
        }
    }

    /**
     * 设置单词是否确定或者不确定标记
     */
    private void setWordStateView() {
        switch (mReciteWordWrapper.getWordReciteState()) {
        case WordEnums.NULL_SURE:
            mIvSureState.setVisibility(View.GONE);
            break;
        case WordEnums.NOT_SURE:
            mIvSureState.setVisibility(View.VISIBLE);
            mIvSureState.setImageResource(R.mipmap.icon_flower_bad);
            break;
        case WordEnums.SURE:
            mIvSureState.setVisibility(View.VISIBLE);
            mIvSureState.setImageResource(R.mipmap.icon_flower_good);
            break;
        default:
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
     * 点击事件下设置单词翻译可见性设置
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

    private void setTranslationVisible() {
        mIvEye.setImageResource(R.mipmap.icon_eye_open);
        mRecyclerViewWordTranslation.setVisibility(View.VISIBLE);
        mBtnNotSure.setVisibility(View.VISIBLE);
        mBtnSure.setVisibility(View.VISIBLE);
    }

    private void setTranslationGone() {
        mIvEye.setImageResource(R.mipmap.icon_eye_close_light_gray);
        mRecyclerViewWordTranslation.setVisibility(View.GONE);
        mBtnNotSure.setVisibility(View.GONE);
        mBtnSure.setVisibility(View.GONE);
    }

    public void setNextFragmentItem(FragmentNextFragmentItemListener nextFragmentItem) {
        this.mNextFragmentItem = nextFragmentItem;
    }

    public interface FragmentNextFragmentItemListener {
        /**
         * 展示下一个Fragment
         */
        void nextItem();
    }

    private void playHornAnimation(ImageView imageView, String mp3Url) {
        imageView.setImageResource(R.drawable.horn_play_animation);
        AnimationDrawable animation = (AnimationDrawable) imageView.getDrawable();
        animation.setOneShot(true);
        animation.start();
        if (!StringUtil.isEmpty(mp3Url)) {
            MediaPlayerUtil.play(getContext(), mp3Url);
        }
    }

}
