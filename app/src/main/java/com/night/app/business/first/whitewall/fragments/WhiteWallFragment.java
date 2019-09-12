package com.night.app.business.first.whitewall.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.night.app.R;
import com.night.app.business.first.whitewall.wrapper.WhiteWallWrapper;
import com.night.app.consts.enums.UserLevelEnums;
import com.night.app.base.fragment.BaseFragment;

public class WhiteWallFragment extends BaseFragment {
    private ImageView        mIvLevel;

    private TextView         mTvUerPetName;

    private TextView         mTvUserComment;

    private WhiteWallWrapper mWrapper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_first_white_wall, container, false);
        initView();
        initClick();
        return view;
    }

    @Override
    public void initView() {
        mIvLevel = view.findViewById(R.id.white_wall_iv_user_level);
        mTvUerPetName = view.findViewById(R.id.white_wall_user_tv_pet_name);
        mTvUserComment = view.findViewById(R.id.white_wall_tv_comment);
        initViewData();
    }

    @Override
    public void initClick() {

    }

    public void initViewData() {
        switch (mWrapper.getUserLevel()) {
        case UserLevelEnums.USER_LEVEL_ONE:
            mIvLevel.setImageResource(R.mipmap.icon_level_one);
            break;
        case UserLevelEnums.USER_LEVEL_TWO:
            mIvLevel.setImageResource(R.mipmap.icon_level_two);
            break;
        case UserLevelEnums.USER_LEVEL_THREE:
            mIvLevel.setImageResource(R.mipmap.icon_level_three);
            break;
        case UserLevelEnums.USER_LEVEL_FOUR:
            mIvLevel.setImageResource(R.mipmap.icon_level_four);
            break;
        case UserLevelEnums.USER_LEVEL_FIVE:
            mIvLevel.setImageResource(R.mipmap.icon_level_five);
            break;
        case UserLevelEnums.USER_LEVEL_SIX:
            mIvLevel.setImageResource(R.mipmap.icon_level_six);
            break;
        }
        mTvUerPetName.setText(mWrapper.getUserPetName());
        mTvUserComment.setText(mWrapper.getUserComment());
    }

    public void setWrapper(WhiteWallWrapper mWrapper) {
        this.mWrapper = mWrapper;
    }
}
