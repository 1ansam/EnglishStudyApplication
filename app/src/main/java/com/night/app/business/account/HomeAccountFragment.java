package com.night.app.business.account;

import com.night.app.R;
import com.night.app.business.account.adapter.AccountFunctionRecycleViewAdapter;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.business.consts.BusinessConsts;
import com.night.business.wrapper.common.CommonFunctionWrapper;
import com.night.app.business.helpcenter.HelpCenterActivity;
import com.night.app.business.personalinfo.PersonalInfoActivity;
import com.night.app.business.personalplan.PersonalPlanActivity;
import com.night.app.business.safetycenter.SafetyCenterActivity;
import com.night.basecore.widget.RecycleView.DividerGridItemDecoration;
import com.night.app.base.fragment.BaseFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeAccountFragment extends BaseFragment {
    private RecyclerView                      mRecycleViewFunctionGrid;

    private AccountFunctionRecycleViewAdapter mAccountFunctionRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_account, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mRecycleViewFunctionGrid = view.findViewById(R.id.home_account_recycle_view_function_grid);
        mAccountFunctionRecyclerAdapter = new AccountFunctionRecycleViewAdapter(getContext(), BusinessConsts.getAccountFunctionWrapperList());

        mRecycleViewFunctionGrid.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecycleViewFunctionGrid.setAdapter(mAccountFunctionRecyclerAdapter);
        mRecycleViewFunctionGrid.addItemDecoration(new DividerGridItemDecoration(getContext()));

    }

    @Override
    public void initClick() {
        mAccountFunctionRecyclerAdapter
                .setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CommonFunctionWrapper>() {
                    @Override
                    public void onItemClick(View view, CommonFunctionWrapper commonFunctionWrapper, int position) {
                        Intent intent = null;
                        switch (position) {
                        case 0:
                            intent = new Intent(getActivity(), PersonalInfoActivity.class);
                            break;
                        case 1:
                            intent = new Intent(getActivity(), PersonalPlanActivity.class);
                            break;
                        case 2:
                            intent = new Intent(getActivity(), SafetyCenterActivity.class);
                            break;
                        case 3:
                            intent = new Intent(getActivity(), HelpCenterActivity.class);
                            break;
                        }
                        startActivity(intent);
                    }
                });
    }
}
