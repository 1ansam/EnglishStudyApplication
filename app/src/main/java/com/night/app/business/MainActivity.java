package com.night.app.business;

import android.content.Intent;
import android.os.Bundle;

import com.night.api.business.database.LogAction;
import com.night.api.business.database.LogActionImpl;
import com.night.api.database.DatabaseUtil;
import com.night.app.R;
import com.night.app.base.activity.BaseActivity;
import com.night.app.business.home.HomeActivity;
import com.night.basecore.utils.DateUtil;
import com.night.model.wrapper.database.LogWrapper;

public class MainActivity extends BaseActivity {
    private LogAction mLogAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initData() {
        DatabaseUtil.packDataBase(this);
        mLogAction=new LogActionImpl(this);
        mLogAction.insertIntoLog(new LogWrapper(DateUtil.getCurrentDate2Str(DateUtil.yyyy_MM_dd_number),0,0,0));
    }
}
