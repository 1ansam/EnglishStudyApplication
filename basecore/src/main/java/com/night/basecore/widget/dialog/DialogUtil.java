package com.night.basecore.widget.dialog;

import android.content.Context;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.View;

/**
 * author:zhuwentao242278 date: On 2019/9/27
 */
public class DialogUtil {
    private Context        context;

    private DialogBuilder  mDialogBuilder;

    private DialogCallback mDialogCallback;

    /**
     * 默认物理返回键返回，屏幕空白处不返回
     * @param context
     */
    public DialogUtil(Context context) {
        this(context, null);
    }

    /**
     * 默认物理返回键返回，屏幕空白处不返回
     * @param context
     * @param mDialogCallback
     */
    public DialogUtil(Context context, DialogCallback mDialogCallback) {
        this(context, mDialogCallback, false);
    }

    /**
     * 默认物理返回键返回，屏幕空白处不反悔
     * @param context
     * @param mDialogCallback
     * @param isShowCloseIv 是否显示关闭叉号
     */
    public DialogUtil(Context context, DialogCallback mDialogCallback, boolean isShowCloseIv) {
        this.context = context;
        this.mDialogCallback = mDialogCallback;
        this.mDialogBuilder = DialogBuilder.getInstance(context).withCloseIv(isShowCloseIv);
    }

    /**
     * 无标题显示一个按钮
     * @param message
     * @param buttonText
     */
    public void showOneButton(String message, String buttonText) {
        mDialogBuilder.getTvMessage().setGravity(Gravity.CENTER);
        showOneButton(null, message, buttonText);
    }

    /**
     * 显示一个按钮
     * @param title
     * @param message
     * @param buttonText
     */
    public void showOneButton(String title, String message, String buttonText) {
        showOneButton(title, new SpannableString(message), buttonText);
    }

    /**
     * 显示一个按钮
     * @param title
     * @param message
     * @param buttonText
     */
    public void showOneButton(String title, SpannableString message, String buttonText) {
        mDialogBuilder.withTitle(title).withMessage(message).withNegativeText(buttonText)
                .withNegativeClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mDialogCallback != null) {
                            mDialogCallback.confirm();
                        }
                        mDialogBuilder.dismiss();
                    }
                }).show();
    }

    /**
     * 无标题显示两个按钮
     * @param message
     * @param yesText
     * @param noText
     */
    public void showTwoButton(String message, String yesText, String noText) {
        mDialogBuilder.getTvMessage().setGravity(Gravity.CENTER);
        showTwoButton(null, message, yesText, noText);
    }

    /**
     * 显示两个按钮
     * @param title
     * @param message
     * @param yesText
     * @param noText
     */
    public void showTwoButton(String title, String message, String yesText, String noText) {
        showTwoButton(title, new SpannableString(message), yesText, noText);
    }

    /**
     * 显示两个按钮
     * @param title
     * @param message
     * @param yesText
     * @param noText
     */
    public void showTwoButton(String title, SpannableString message, String yesText, String noText) {
        mDialogBuilder.withTitle(title).withMessage(message).withPositiveText(yesText).withPositiveClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDialogCallback!=null){
                    mDialogCallback.confirm();
                }
                mDialogBuilder.dismiss();
            }
        }).withNegativeText(noText).withNegativeClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDialogCallback!=null){
                    mDialogCallback.cancel();
                }
                mDialogBuilder.dismiss();
            }
        }).show();
    }

    /**
     * 设置物理返回键是否可以返回
     * @param isCanaelable
     * @return
     */
    public DialogUtil withCancelable(boolean isCanaelable){
        mDialogBuilder.setCancelable(isCanaelable);
        return this;
    }

    /**
     * 设置屏幕空白处点击是否返回
     * @param isCancelable
     * @return
     */
    public DialogUtil withCancelOutside(boolean isCancelable){
        mDialogBuilder.setCanceledOnTouchOutside(isCancelable);
        return this;
    }
    public interface DialogCallback {
        /**
         * 确定按钮点击事件
         */
        void confirm();

        /**
         * 否定按钮点击事件
         */
        void cancel();
    }
}
