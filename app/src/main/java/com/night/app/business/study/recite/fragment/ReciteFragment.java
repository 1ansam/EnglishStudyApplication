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

import com.night.api.business.database.CurrentAction;
import com.night.api.business.database.CurrentActionImpl;
import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;
import com.night.app.common.adapter.WordTranslationRecyclerAdapter;
import com.night.api.consts.enums.WordEnums;
import com.night.api.business.util.WordUtil;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.utils.StyleUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.model.wrapper.database.CurrentWrapper;
import com.night.model.wrapper.recite.ReciteWordWrapper;

/**
 * @author zhuwentao
 */
public class ReciteFragment extends BaseFragment {
    private ImageView                                mIvCollect;

    private ImageView                                mIvSureState;

    private TextView mTvProgress;

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

    private WordTranslationRecyclerAdapter mRecyclerViewTranslationAdapter;

    private ReciteWordWrapper                        mReciteWordWrapper;

    private WordAction                               mWordAction;

    private CurrentAction                            mCurrentAction;

    private FragmentNextFragmentItemListener         mNextFragmentItem;

    /**
     * 当前fragment所在位置
     */
    private int position;

    /**
     * 当前数据长度
     */
    private int size;

    public ReciteFragment(ReciteWordWrapper reciteWordWrapper,int position,int size) {
        this.mReciteWordWrapper=reciteWordWrapper;
        this.position=position;
        this.size=size;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recite_fragment, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mIvCollect = view.findViewById(R.id.recite_iv_collect);
        mIvSureState = view.findViewById(R.id.recite_iv_sure_state);
        mTvProgress=view.findViewById(R.id.recite_tv_progress);
        mTvWordName = view.findViewById(R.id.word_tv_name);
        mTvWordPhEn = view.findViewById(R.id.word_tv_ph_en);
        mIvWordPhEnMp3 = view.findViewById(R.id.word_iv_ph_en_mp3);
        mTvWordPhAm = view.findViewById(R.id.word_tv_ph_am);
        mIvWordPhAmMp3 = view.findViewById(R.id.word_iv_ph_am_mp3);
        mRecyclerViewWordTranslation = view.findViewById(R.id.word_recycler_view_translation);
        mRecyclerViewTranslationAdapter = new WordTranslationRecyclerAdapter(getContext(),
                mReciteWordWrapper.getWordWrapper().getWordTranslationWrapperList());
        mRecyclerViewWordTranslation.setAdapter(mRecyclerViewTranslationAdapter);
        mRecyclerViewWordTranslation
                .setLayoutManager(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mLayoutWordTranslationView = view.findViewById(R.id.recite_layout_main_word_view);
        mIvEye = view.findViewById(R.id.recite_iv_eye);
        mBtnNotSure = view.findViewById(R.id.recite_btn_not_sure);
        mBtnSure = view.findViewById(R.id.recite_btn_sure);
        mWordAction = new WordActionImpl(getContext());
        mCurrentAction = new CurrentActionImpl(getContext());

        mTvProgress.setText(String.valueOf(position+1)+"/"+String.valueOf(size));
        mTvWordName.setText(mReciteWordWrapper.getWordWrapper().getWordName());
        mTvWordPhEn.setText(StyleUtil.getWordPh(mReciteWordWrapper.getWordWrapper().getWordPhEn(), "en"));
        mTvWordPhAm.setText(StyleUtil.getWordPh(mReciteWordWrapper.getWordWrapper().getWordPhAm(), "am"));
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
                    mIvCollect.setImageResource(R.mipmap.icon_star_light_gray);
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
                MediaPlayerUtil.playHornAnimation(getContext(), mIvWordPhEnMp3, mp3Url);
            }
        });

        mIvWordPhAmMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhAmMp3.getTag();
                MediaPlayerUtil.playHornAnimation(getContext(), mIvWordPhAmMp3, mp3Url);
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
                mCurrentAction.updateCurrentState(mReciteWordWrapper.getCurrentWrapper().getWordName(),
                        WordEnums.STATE_ING);
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
                CurrentWrapper currentWrapper = mReciteWordWrapper.getCurrentWrapper();
                int expectedNextDate = WordUtil.getEexpectedNextDate(currentWrapper);
                if (expectedNextDate == 0) {
                    mCurrentAction.updateCurrentEnd(currentWrapper.getWordName());
                } else {
                    mCurrentAction.updateCurrentNextDate(currentWrapper.getWordName(), expectedNextDate,
                            WordEnums.STATE_ING);
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
            mIvCollect.setImageResource(R.mipmap.icon_star_light_gray);
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

}
