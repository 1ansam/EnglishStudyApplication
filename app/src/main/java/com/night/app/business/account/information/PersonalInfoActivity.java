package com.night.app.business.account.information;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.account.information.adapter.PersonalInfoRecycleViewAdapter;
import com.night.app.common.title.TitleInitUtil;

public class PersonalInfoActivity extends BaseActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.account_function_personal_info);
        mRecycleView = findViewById(R.id.personal_info_recycle_view_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(new PersonalInfoRecycleViewAdapter(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.linear_divider_bg));
        mRecycleView.addItemDecoration(divider);
    }

    @Override
    public void initClick() {

    }
}
