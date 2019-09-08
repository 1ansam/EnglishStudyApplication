package com.night.app.base.fragment;

import android.view.View;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    public View view;

    public abstract void initView();

    public abstract void initClick();

}
