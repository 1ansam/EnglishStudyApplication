package com.night.app.business.login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.night.app.R;
import com.night.app.business.home.HomeActivity;
import com.night.app.base.fragment.BaseFragment;

public class LoginByAccountPwdFragment extends BaseFragment {
    private Button mBtnLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_by_account_pwd, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mBtnLogin=view.findViewById(R.id.login_pwd_fragment_btn_login);
    }

    @Override
    public void initClick() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
