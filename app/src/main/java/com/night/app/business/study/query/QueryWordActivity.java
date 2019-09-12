package com.night.app.business.study.query;


import android.os.Bundle;

import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.common.title.TitleInitUtil;

public class QueryWordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_word);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this,R.string.study_function_query_word);
    }

    @Override
    public void initClick() {

    }
}
