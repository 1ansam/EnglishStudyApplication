package com.night.app.base.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        // LogUtil.d("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // LogUtil.d("onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        // LogUtil.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // LogUtil.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // LogUtil.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // LogUtil.d("onDestroy");
    }

    public void initView() {

    }

    public void initClick() {

    }

}
