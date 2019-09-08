package com.night.app.business.recite.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.night.app.R;
import com.night.app.base.fragment.BaseFragment;

public class ReciteFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.item_recite_fragment,container,false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initClick() {

    }
}
