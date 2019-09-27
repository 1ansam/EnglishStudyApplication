package com.night.basecore.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.night.basecore.R;
import com.night.basecore.utils.TranslateAnimationUtil;

/**
 * author:zhuwentao242278 date: On 2019/9/27
 */
public class DialogBuilder extends Dialog implements DialogInterface {

    private Context              mContext;

    private View                 mDialogView;

    private RelativeLayout       mLayoutBackground;

    private TextView             mTvTittle;

    private TextView             mTvMessage;

    private Button               mBtnNegative;

    private Button               mBtnPositive;

    private View                 mLineVertical;

    private ImageView            mIvClose;

    private boolean              isCancelable    = true;

    private boolean              isCancelOutSide = false;

    private static DialogBuilder instance;

    public DialogBuilder(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
        initClick();
    }

    public DialogBuilder(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        initView();
        initClick();
    }

    public static DialogBuilder getInstance(Context context) {
        instance = new DialogBuilder(context, R.style.dialog_untran);
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes((WindowManager.LayoutParams) params);
    }

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {
        super.dismiss();
        instance = null;
    }

    private void initView() {
        mDialogView = View.inflate(mContext, R.layout.dialog_layout, null);
        mLayoutBackground = (RelativeLayout) mDialogView.findViewById(R.id.dialog_layout_background);
        mTvTittle = (TextView) mDialogView.findViewById(R.id.dialog_tv_title);
        mTvMessage = (TextView) mDialogView.findViewById(R.id.dialog_tv_message);
        mBtnNegative = (Button) mDialogView.findViewById(R.id.dialog_btn_negative);
        mBtnPositive = (Button) mDialogView.findViewById(R.id.dialog_btn_positive);
        mLineVertical = (View) mDialogView.findViewById(R.id.dialog_button_vertical_line);
        mIvClose = (ImageView) mDialogView.findViewById(R.id.dialog_iv_close);
        setContentView(mDialogView);
        // 默认点击物理返回键返回
        this.setCancelable(true);
    }

    private void initClick() {
        this.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                mLayoutBackground
                        .startAnimation(TranslateAnimationUtil.getShowAnim(500, TranslateAnimationUtil.DIRECTION_DOWN));
                mLayoutBackground.setVisibility(View.VISIBLE);
            }
        });

        mLayoutBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCancelOutSide) {
                    dismiss();
                }
            }
        });

        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 设置标题内容，为null则不显示标题
     * @param title
     * @return
     */
    public DialogBuilder withTitle(String title) {
        if (title == null) {
            mTvTittle.setVisibility(View.GONE);
        } else {
            mTvTittle.setText(title);
        }
        return this;
    }

    /**
     * 设置标题内容，为null则不显示标题
     * @param titleTextId
     * @return
     */
    public DialogBuilder withTitle(int titleTextId) {
        return withTitle(mContext.getString(titleTextId));
    }

    /**
     * 设置标题颜色
     * @param colorId
     * @return
     */
    public DialogBuilder withTitleColor(int colorId) {
        mTvTittle.setTextColor(colorId);
        return this;
    }

    /**
     * 设置弹窗主体内容
     * @param str
     * @return
     */
    public DialogBuilder withMessage(String str) {
        mTvMessage.setText(str);
        return this;
    }

    /**
     * 设置弹窗主体内容
     * @param spannableString
     * @return
     */
    public DialogBuilder withMessage(SpannableString spannableString) {
        mTvMessage.setText(spannableString);
        return this;
    }

    /**
     * 设置弹窗主体内容
     * @param strId
     * @return
     */
    public DialogBuilder withMessage(int strId) {
        mTvMessage.setText(mContext.getString(strId));
        return this;
    }

    /**
     * 设置否定按钮内容
     * @param str
     * @return
     */
    public DialogBuilder withNegativeText(String str) {
        mBtnNegative.setVisibility(View.VISIBLE);
        mBtnNegative.setText(str);
        return this;
    }

    /**
     * 设置否定按钮内容
     * @param strId
     * @return
     */
    public DialogBuilder withNegativeText(int strId) {
        mBtnNegative.setVisibility(View.VISIBLE);
        mBtnNegative.setText(mContext.getText(strId));
        return this;
    }

    /**
     * 设置确定按钮内容
     * @param str
     * @return
     */
    public DialogBuilder withPositiveText(String str) {
        mBtnPositive.setVisibility(View.VISIBLE);
        mLineVertical.setVisibility(View.VISIBLE);
        mBtnPositive.setText(str);
        return this;
    }

    /**
     * 设置确定按钮内容
     * @param strId
     * @return
     */
    public DialogBuilder withPositiveText(int strId) {
        mBtnPositive.setVisibility(View.VISIBLE);
        mLineVertical.setVisibility(View.VISIBLE);
        mBtnPositive.setText(mContext.getString(strId));
        return this;
    }

    /**
     * 设置否定按钮监听
     * @param click
     * @return
     */
    public DialogBuilder withNegativeClick(View.OnClickListener click) {
        mBtnNegative.setOnClickListener(click);
        return this;
    }

    /**
     * 设置确定按钮监听
     * @param click
     * @return
     */
    public DialogBuilder withPositiveClick(View.OnClickListener click) {
        mBtnPositive.setOnClickListener(click);
        return this;
    }

    /**
     * 点击弹窗外部是否会返回
     * @param cancelable
     * @return
     */
    public DialogBuilder isCancelableOnTouchOutSide(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    /**
     * 设置物理返回键是否返回
     * @param cancelable
     * @return
     */
    public DialogBuilder isCancelable(boolean cancelable) {
        this.isCancelable = cancelable;
        this.setCancelable(cancelable);
        return this;
    }

    /**
     * 设置点击屏幕空白处是否返回
     * @param cancelOutSide
     * @return
     */
    public DialogBuilder setCancelOutSide(boolean cancelOutSide) {
        isCancelOutSide = cancelOutSide;
        return this;
    }

    /**
     * 设置弹窗底部小叉号是否显示
     * @param showCloseIv
     * @return
     */
    public DialogBuilder withCloseIv(boolean showCloseIv) {
        mIvClose.setVisibility(showCloseIv ? View.VISIBLE : View.GONE);
        return this;
    }

    public TextView getTvMessage() {
        return mTvMessage;
    }
}
