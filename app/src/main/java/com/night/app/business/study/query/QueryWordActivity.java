package com.night.app.business.study.query;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.api.business.database.BookAction;
import com.night.api.business.database.BookActionImpl;
import com.night.api.business.database.BookWordAction;
import com.night.api.business.database.BookWordActionImpl;
import com.night.api.business.database.CurrentAction;
import com.night.api.business.database.CurrentActionImpl;
import com.night.api.business.database.WordAction;
import com.night.api.business.database.WordActionImpl;
import com.night.api.business.okhttp.WordApiAction;
import com.night.api.business.okhttp.WordApiActionImpl;
import com.night.api.business.util.WordUtil;
import com.night.api.consts.MessageConsts;
import com.night.api.consts.enums.WordEnums;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.common.adapter.WordTranslationRecyclerAdapter;
import com.night.app.common.util.ProgressUtil;
import com.night.app.common.util.TitleInitUtil;
import com.night.basecore.utils.ListUtil;
import com.night.basecore.utils.MediaPlayerUtil;
import com.night.basecore.utils.StringUtil;
import com.night.basecore.utils.StyleUtil;
import com.night.basecore.utils.ToastUtil;
import com.night.basecore.widget.recyclerview.CustomLinearLayoutManager;
import com.night.model.entity.WordEntity;
import com.night.model.wrapper.common.WordWrapper;
import com.night.model.wrapper.database.WordDataBaseWrapper;

public class QueryWordActivity extends BaseActivity {
    private EditText                       mEtQuery;

    private ImageView                      mIvClearEditText;

    private Button                         mBtnQuery;

    private View                           mViewWordInfo;

    private TextView                       mTvWordName;

    private TextView                       mTvWordPhEn;

    private TextView                       mTvWordPhAm;

    private ImageView                      mIvWordPhEnMp3;

    private ImageView                      mIvWordPhAmMp3;

    private RecyclerView                   mRecyclerWordTranslation;

    private Button                         mBtnAddToCollect;

    private Button                         mBtnAddToRecite;

    private WordTranslationRecyclerAdapter mTranslationAdapter;

    private WordWrapper                    mWordWrapper;

    private WordAction                     mWordAction;

    private WordApiAction                  mWordApiAction;

    private CurrentAction                  mCurrentAction;

    private BookWordAction                 mBookWordAction;

    private BookAction                     mBookAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_word);
        initData();
        initView();
        initClick();
    }

    @Override
    public void initData() {
        mWordAction = new WordActionImpl(this);
        mWordApiAction = new WordApiActionImpl(this);
        mCurrentAction = new CurrentActionImpl(this);
        mBookWordAction = new BookWordActionImpl(this);
        mBookAction = new BookActionImpl(this);
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.study_function_query_word);
        mEtQuery = findViewById(R.id.query_word_et_query);
        mIvClearEditText = findViewById(R.id.query_word_iv_clear_edit_text);
        mBtnQuery = findViewById(R.id.query_word_btn_query);
        mViewWordInfo = findViewById(R.id.query_word_layout_word_info);
        mTvWordName = findViewById(R.id.word_pager_info_tv_name);
        mTvWordPhEn = findViewById(R.id.word_pager_info_tv_ph_en);
        mTvWordPhAm = findViewById(R.id.word_pager_info_tv_ph_am);
        mIvWordPhEnMp3 = findViewById(R.id.word_pager_info_ph_en_mp3);
        mIvWordPhAmMp3 = findViewById(R.id.word_pager_info_iv_ph_am_mp3);
        mRecyclerWordTranslation = findViewById(R.id.word_pager_info_recycler_view_translation);
        mRecyclerWordTranslation.setVisibility(View.VISIBLE);
        mRecyclerWordTranslation
                .setLayoutManager(new CustomLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBtnAddToCollect = findViewById(R.id.query_word_btn_add_to_collect);
        mBtnAddToRecite = findViewById(R.id.query_word_btn_add_to_recite);

    }

    @Override
    public void initClick() {
        topBarClickListener();
        wordPhListener();

        mBtnAddToCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWordAction.updateWordCollectState(mWordWrapper.getWordName(), WordEnums.COLLECTED);
                mBtnAddToCollect.setVisibility(View.GONE);
            }
        });

        mBtnAddToRecite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 更新bookWord
                mBookWordAction.updateBookWordState(ListUtil.getStringList(mWordWrapper.getWordName()),
                        BookWordActionImpl.WORD_STATE_SELECYED);
                // 更新book
                mBookAction.updateWordSelectedNumber(
                        mBookWordAction.getWordExistInBookNumber(ListUtil.getStringList(mWordWrapper.getWordName())));
                // 更新current
                mCurrentAction.insertIntoCurrent(ListUtil.getStringList(mWordWrapper.getWordName()));
                mBtnAddToRecite.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 包括输入文本框、查询按钮、清空按钮监听
     */
    private void topBarClickListener() {
        mEtQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (StringUtil.isEmpty(mEtQuery.getText().toString())) {
                    mIvClearEditText.setVisibility(View.GONE);
                } else {
                    mIvClearEditText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mIvClearEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtQuery.setText("");
                mIvClearEditText.setVisibility(View.GONE);
            }
        });

        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordName = StyleUtil.getFormatQueryText(mEtQuery.getText().toString());
                if (StringUtil.isEmpty(wordName)) {
                    ToastUtil.showLongToast(getBaseContext(), MessageConsts.StyleCheckConsts.NULL_WORD);
                } else {
                    ProgressUtil.showProgress(QueryWordActivity.this, View.VISIBLE);
                    mWordWrapper = mWordAction.getWordByName(wordName);
                    if (mWordWrapper == null) {
                        mWordApiAction.setWordApiListener(new WordApiAction.WordApiListener<WordEntity>() {
                            @Override
                            public void doAfterSuccess(WordEntity data) {
                                ProgressUtil.showProgress(QueryWordActivity.this, View.GONE);
                                if (data == null || StringUtil.isEmpty(data.getWord_name())) {
                                    showWordInfo(View.GONE);
                                    ToastUtil.showLongToast(getBaseContext(),
                                            MessageConsts.TipConsts.NOT_FIND_THIS_WORD);
                                } else {
                                    WordDataBaseWrapper wordDataBaseWrapper = WordUtil
                                            .changeEntityToDataBaseWrapper(data);
                                    mWordAction.insertIntoWord(wordDataBaseWrapper);
                                    mWordWrapper = WordUtil.changeEntityToWrapper(data);
                                    showWordInfo(View.VISIBLE);
                                }
                            }

                            @Override
                            public void doAfterFailed(WordEntity data) {
                                ProgressUtil.showProgress(QueryWordActivity.this, View.GONE);
                                ToastUtil.showLongToast(getBaseContext(),
                                        MessageConsts.InternetConsts.INTERNET_NOT_AVAILABLE);
                            }
                        });
                        mWordApiAction.getWordEntity(wordName);
                    } else {
                        ProgressUtil.showProgress(QueryWordActivity.this, View.GONE);
                        showWordInfo(View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 关于单词发音按钮的监听
     */
    private void wordPhListener() {
        mIvWordPhEnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhEnMp3.getTag();
                MediaPlayerUtil.playHornAnimation(getBaseContext(), mIvWordPhEnMp3, mp3Url);
            }
        });

        mIvWordPhAmMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mp3Url = (String) mIvWordPhAmMp3.getTag();
                MediaPlayerUtil.playHornAnimation(getBaseContext(), mIvWordPhAmMp3, mp3Url);
            }
        });
    }

    /**
     * 设置单词信息是否显示
     * @param visibility
     */
    private void showWordInfo(int visibility) {
        if (View.VISIBLE == visibility) {
            boolean isRecited = mCurrentAction.isContainedInTable(mWordWrapper.getWordName());
            mTvWordName.setText(mWordWrapper.getWordName());
            mTvWordPhEn.setText(StyleUtil.getWordPh(mWordWrapper.getWordPhEn(), "en"));
            mTvWordPhAm.setText(StyleUtil.getWordPh(mWordWrapper.getWordPhAm(), "am"));
            mIvWordPhEnMp3.setTag(mWordWrapper.getWordPhEnMp3());
            mIvWordPhAmMp3.setTag(mWordWrapper.getWordPhAmMp3());
            mTranslationAdapter = new WordTranslationRecyclerAdapter(this,
                    mWordWrapper.getWordTranslationWrapperList());
            mRecyclerWordTranslation.setAdapter(mTranslationAdapter);
            mViewWordInfo.setVisibility(View.VISIBLE);
            if (mWordWrapper.getWordCollectState() == WordEnums.NOT_COLLECTED) {
                mBtnAddToCollect.setVisibility(View.VISIBLE);
            } else {
                mBtnAddToCollect.setVisibility(View.GONE);
            }
            if (isRecited) {
                mBtnAddToRecite.setVisibility(View.GONE);
            } else {
                mBtnAddToRecite.setVisibility(View.VISIBLE);
            }

        } else {
            mViewWordInfo.setVisibility(View.GONE);
            mBtnAddToCollect.setVisibility(View.GONE);
            mBtnAddToRecite.setVisibility(View.GONE);
        }
    }
}
