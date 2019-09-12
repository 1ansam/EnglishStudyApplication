package com.night.app.business.first;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;
import com.night.app.business.first.adapter.FunctionRecycleViewAdapter;
import com.night.app.business.first.whitewall.adapter.WhiteWallVerticalPagerAdapter;
import com.night.app.business.first.whitewall.wrapper.WhiteWallWrapper;
import com.night.basecore.widget.circleslidingindicator.CircleSlidingIndicator;
import com.night.basecore.widget.customimageview.widget.CustomShapeImageView;
import com.night.basecore.widget.verticalviewpager.VerticalViewPager;
import com.night.app.base.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFirstFragment extends BaseFragment {
    private TextView               mTvTitleSayHello;

    private ImageView              mIvAddFriend;

    private ImageView              mIvScan;

    private ImageView              mIvQrCode;

    private CircleSlidingIndicator mCircleSlidingIndicatorBanner;

    private CustomShapeImageView   mCustomIvMyself;

    private TextView               mTvMyselfTargetStudyNumber;

    private TextView               mTvMySelfAlreadyCompletedStudyNumber;

    private TextView               mTvMyselfExecution;

    private CustomShapeImageView   mCustomIvPartner;

    private TextView               mTvPartnerTargetStudyNumber;

    private TextView               mTvPartnerAlreadyCompletedStudyNumber;

    private TextView               mTvPartnerExecution;

    private VerticalViewPager      mVerticalViewPagerWhiteWall;

    private RecyclerView           mLayoutFunctionContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_first, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        // title
        mTvTitleSayHello = view.findViewById(R.id.home_first_title_tv_say_hello);
        mIvAddFriend = view.findViewById(R.id.home_first_iv_add_friend);
        mIvScan = view.findViewById(R.id.home_first_iv_scan);
        mIvQrCode = view.findViewById(R.id.home_first_iv_qr_code);

        // banner
        mCircleSlidingIndicatorBanner = view.findViewById(R.id.home_first_circle_sliding_indicator_banner);

        // myself
        mCustomIvMyself = view.findViewById(R.id.home_first_custom_iv_myself);
        mTvMyselfTargetStudyNumber = view.findViewById(R.id.first_myself_tv_target_study_number);
        mTvMySelfAlreadyCompletedStudyNumber = view.findViewById(R.id.first_myself_tv_already_completed_study_number);
        mTvMyselfExecution = view.findViewById(R.id.first_myself_tv_execution);

        // partner
        mCustomIvPartner = view.findViewById(R.id.home_first_custom_iv_partner);
        mTvPartnerTargetStudyNumber = view.findViewById(R.id.first_partner_tv_target_study_number);
        mTvPartnerAlreadyCompletedStudyNumber = view.findViewById(R.id.first_partner_tv_already_completed_study_number);
        mTvPartnerExecution = view.findViewById(R.id.first_partner_tv_execution);

        // white wall
        mVerticalViewPagerWhiteWall = view.findViewById(R.id.home_first_circle_sliding_indicator_white_wall);

        // function container
        mLayoutFunctionContainer = view.findViewById(R.id.home_first_layout_function_container);

        initBanner();
        initWhiteWall();
        initFunctionContainer();

    }

    @Override
    public void initClick() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mCircleSlidingIndicatorBanner.resumenAutoCircle(5000);
        mVerticalViewPagerWhiteWall.resumenAutoCircle(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mCircleSlidingIndicatorBanner.stopAutoCircle();
        mVerticalViewPagerWhiteWall.stopAutoCircle();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initBanner() {
        ArrayList<Integer> bannerImageResourceIdList = new ArrayList<>();
        bannerImageResourceIdList.add(R.mipmap.first_banner_one);
        bannerImageResourceIdList.add(R.mipmap.first_banner_two);
        bannerImageResourceIdList.add(R.mipmap.first_banner_three);
        mCircleSlidingIndicatorBanner.setFloatPointHeight(20);
        mCircleSlidingIndicatorBanner.setFloatPointWidth(20);
        mCircleSlidingIndicatorBanner.setCircleSlidingIndicatorMarginBottomSpace(15);
        mCircleSlidingIndicatorBanner
                .setViewPagerImageViewBackground(R.drawable.layout_background_white_circular_corner);
        mCircleSlidingIndicatorBanner.showCircleSlidingIndicatorByImgId(bannerImageResourceIdList, true, null);
    }

    private void initWhiteWall() {
        List<WhiteWallWrapper> wrapperList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            wrapperList.add(new WhiteWallWrapper("LV5", "超级无敌猪猪侠" + i, "两只小猪两只小猪跑的快,跑的快!一只没有耳朵,一只没有尾巴,真奇怪,真奇怪!"));
        }
        mVerticalViewPagerWhiteWall.setAdapter(new WhiteWallVerticalPagerAdapter(getFragmentManager(), wrapperList));
        mVerticalViewPagerWhiteWall.setCurrentItem(0);
    }

    private void initFunctionContainer() {
        mLayoutFunctionContainer.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mLayoutFunctionContainer.setAdapter(new FunctionRecycleViewAdapter(getContext()));
    }
}
