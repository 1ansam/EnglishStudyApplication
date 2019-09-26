package com.night.app.business.study.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;
import com.night.basecore.utils.LogUtil;
import com.night.model.wrapper.database.TestWrapper;

public class TestSelectWordFragment extends BaseFragment {
    private TestWrapper   mTestWrapper;

    private TextView      mTvWordName;

    private RadioButton   mBtnA;

    private RadioButton   mBtnB;

    private RadioButton   mBtnC;

    private RadioButton   mBtnD;

    private RadioButton   mBtnE;

    private Button mBtnNextWord;

    private ScoreListener mScoreListener;

    public TestSelectWordFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_number_test_word, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mTvWordName = view.findViewById(R.id.number_test_tv_word_name);
        mBtnA = view.findViewById(R.id.number_test_btn_a);
        mBtnB = view.findViewById(R.id.number_test_btn_b);
        mBtnC = view.findViewById(R.id.number_test_btn_c);
        mBtnD = view.findViewById(R.id.number_test_btn_d);
        mBtnE = view.findViewById(R.id.number_test_btn_e);
        mBtnNextWord=view.findViewById(R.id.number_test_btn_next_word);

        mTvWordName.setText(mTestWrapper.getWordName());
        mBtnA.setText(mTestWrapper.getaTranslation());
        mBtnB.setText(mTestWrapper.getbTranslation());
        mBtnC.setText(mTestWrapper.getcTranslation());
        mBtnD.setText(mTestWrapper.getdTranslation());

        switch (mTestWrapper.getAnswer()) {
        case "A":
            mBtnA.setTag(true);
            break;
        case "B":
            mBtnB.setTag(true);
            break;
        case "C":
            mBtnC.setTag(true);
            break;
        case "D":
            mBtnD.setTag(true);
            break;
        default:
            break;
        }
    }

    @Override
    public void initClick() {
        mBtnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d(getSelectScore()+"");
                mScoreListener.doAfterSelect(getSelectScore());
            }
        });
    }

    private int getSelectScore(){
        if((Boolean)mBtnA.getTag()!=null&&"A".equals(mTestWrapper.getAnswer())&&mBtnA.isChecked()){
            return mTestWrapper.getScore();
        }
        if((Boolean)mBtnB.getTag()!=null&&"B".equals(mTestWrapper.getAnswer())&&mBtnB.isChecked()){
            return mTestWrapper.getScore();
        }
        if((Boolean)mBtnC.getTag()!=null&&"C".equals(mTestWrapper.getAnswer())&&mBtnC.isChecked()){
            return mTestWrapper.getScore();
        }
        if((Boolean)mBtnD.getTag()!=null&&"D".equals(mTestWrapper.getAnswer())&&mBtnD.isChecked()){
            return mTestWrapper.getScore();
        }
        return 0;
    }

    public void setTestWrapper(TestWrapper mTestWrapper) {
        this.mTestWrapper = mTestWrapper;
    }

    public void setScoreListener(ScoreListener mScoreListener) {
        this.mScoreListener = mScoreListener;
    }

    public interface ScoreListener {
        void doAfterSelect(int score);
    }

}
