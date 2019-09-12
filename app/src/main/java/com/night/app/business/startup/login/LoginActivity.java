package com.night.app.business.startup.login;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.night.app.R;

import com.night.app.business.startup.login.adapter.LoginFragmentAdapter;
import com.night.app.business.startup.login.fragment.LoginByAccountPwdFragment;
import com.night.app.business.startup.login.fragment.LoginByVerificationCodeFragment;
import com.night.basecore.widget.viewpager.tabSelectListener;
import com.night.app.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity {
    private TextView mTvConstentAgreement;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private LoginFragmentAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initClick();
    }

    @Override
    public void initView() {
        mViewPager=findViewById(R.id.login_activity_view_pager_login_and_register);
        mTabLayout=findViewById(R.id.login_activity_tab_layout);
        mTvConstentAgreement=findViewById(R.id.login_activity_tv_agreement);

        initTab();
        initPager();
        initAgreement();

    }

    @Override
    public void initClick() {

    }

    private void initTab(){
        int[] strId=new int[]{R.string.login_by_account_pwd,R.string.login_by_verification_code};
        for(int i=0;i<strId.length;i++){
            View tabItem=getLayoutInflater().inflate(R.layout.item_login_tab_bar,null);
            TextView text=tabItem.findViewById(R.id.login_tv_tab_bar_text);
            text.setText(getResources().getText(strId[i]));
            text.setGravity(Gravity.CENTER_VERTICAL);
            if(i==0){
                text.setGravity((Gravity.START));
            }else{
                text.setGravity((Gravity.END));
            }
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabItem));
        }
    }

    private void initPager(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LoginByAccountPwdFragment());
        fragmentList.add(new LoginByVerificationCodeFragment());
        mFragmentAdapter=new LoginFragmentAdapter(this,fragmentList);
        mViewPager.setAdapter(mFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(fragmentList.size()-1);
        mTabLayout.addOnTabSelectedListener(new tabSelectListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    private void initAgreement(){
        //同意隐私政策及服务条款
        SpannableString spannableString = new SpannableString(getResources().getString(R.string.user_agreement_regulations));
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),2,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new UnderlineSpan(),2,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.cyan)),2,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD),7,11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new UnderlineSpan(),7,11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.cyan)),7,11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvConstentAgreement.setText(spannableString);
    }
}
