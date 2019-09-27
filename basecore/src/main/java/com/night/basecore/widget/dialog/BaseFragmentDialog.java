package com.night.basecore.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

/**
 * author:zhuwentao242278
 * date:  On 2019/9/27
 */
public abstract class BaseFragmentDialog extends DialogFragment {
    protected Context mContext;

    protected int     mWidth  = ViewGroup.LayoutParams.WRAP_CONTENT;

    protected int     mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

    protected boolean mIsAttach;

    protected String  mTag    = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCompatibilitySetting();
        mContext=getActivity();
        return init(inflater,container,savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window dialogWindow = getDialog().getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams params = dialogWindow.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            dialogWindow.setAttributes(params);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIsAttach = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIsAttach = false;
    }

    /**
     * View初始化方法 在onCreateView中被调用，返回值将作为Dialog的布局
     */
    protected abstract View init(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState);

    /**
     * 设置Dialog的尺寸大小，其数值会在onResume时应用于窗口，默认为两个WRAP_CONTENT
     * @param width 宽度
     * @param height 高度
     */
    protected void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public void show(Context context) {
        if (context instanceof FragmentActivity) {
            show(((FragmentActivity) context).getSupportFragmentManager(), mTag);
        }
    }

    protected void setCompatibilitySetting() {
        // 部分三星机型会在Dialog上方显示一条蓝色分隔线，这里若发现这条线，即将其设置为透明
        View dividerInSamsung = getDialog()
                .findViewById(getContext().getResources().getIdentifier("android:id/titleDivider", null, null));
        if (dividerInSamsung != null) {
            dividerInSamsung.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
