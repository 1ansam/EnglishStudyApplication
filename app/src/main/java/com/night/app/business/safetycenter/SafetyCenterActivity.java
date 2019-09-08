package com.night.app.business.safetycenter;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.night.app.R;
import com.night.app.business.safetycenter.adapter.SafetyCenterRecycleViewAdapter;
import com.night.app.business.title.TitleInitUtil;
import com.night.app.base.activity.BaseActivity;

public class SafetyCenterActivity extends BaseActivity {
    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_center);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        TitleInitUtil.initTitle(this, R.string.account_function_safety_center);
        mRecycleView = findViewById(R.id.safety_center_recycle_view_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setAdapter(new SafetyCenterRecycleViewAdapter(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.linear_divider_bg));
        mRecycleView.addItemDecoration(divider);
    }

    @Override
    public void initClick() {

    }
}
