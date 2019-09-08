package com.night.app.business.activity;

import com.night.app.R;
import com.night.app.TestStaticData;
import com.night.app.business.activity.adapter.ActivityRecyclerViewAdapter;
import com.night.app.base.fragment.BaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HomeActivityFragment extends BaseFragment {
    private RecyclerView mRecycleView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home_activity,container,false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mRecycleView=view.findViewById(R.id.home_activity_recycle_view);
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
        mRecycleView.setAdapter(new ActivityRecyclerViewAdapter(getContext(), TestStaticData.getActivityWrapper()));
    }

    @Override
    public void initClick() {

    }
}
