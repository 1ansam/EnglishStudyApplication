package com.night.basecore.widget.custombutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.night.basecore.R;


public class CustomButton extends AppCompatButton {
    //背景色
    private int mBackgroundColor = Color.TRANSPARENT;
    //按压背景色
    private int mPressedColor = 0;
    //圆角
    private float mCornerRadius = 0f;
    private float mCornerRadiiTopLeft = 0f;
    private float mCornerRadiiTopRight = 0f;
    private float mCornerRadiiBottomRight = 0f;
    private float mCornerRadiiBottomLeft = 0f;
    //描边
    private int mStrokeColor = 0;
    private int mStrokeWidth = 0;
    //是否使用按压波纹效果
    private boolean isPressEffectEnable = true;
    // 普通背景 shape 样式
    private GradientDrawable mNormalBackgroundDrawable = new GradientDrawable();
    // 按压shape 样式
    private GradientDrawable mPressedDrawable = new GradientDrawable();
    // shape 样式集合
    private StateListDrawable mStateListDrawable = new StateListDrawable();

    public CustomButton(Context context) {
        super(context);
        initialize(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs,0);//第3个参数传0，不使用系统的默认样式
        initialize(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    private void initialize(Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
            mBackgroundColor = typedArray.getColor(R.styleable.CustomButton_btn_backgroundColor, Color.TRANSPARENT);
            mCornerRadius = typedArray.getDimension(R.styleable.CustomButton_btn_cornerRadius, 0);
            mCornerRadiiTopLeft = typedArray.getDimension(R.styleable.CustomButton_btn_cornerRadii_topLeft, mCornerRadius);
            mCornerRadiiTopRight = typedArray.getDimension(R.styleable.CustomButton_btn_cornerRadii_topRight, mCornerRadius);
            mCornerRadiiBottomRight = typedArray.getDimension(R.styleable.CustomButton_btn_cornerRadii_bottomRight, mCornerRadius);
            mCornerRadiiBottomLeft = typedArray.getDimension(R.styleable.CustomButton_btn_cornerRadii_bottomLeft, mCornerRadius);

            mStrokeColor = typedArray.getColor(R.styleable.CustomButton_btn_strokeColor, 0);
            mStrokeWidth = (int) typedArray.getDimension(R.styleable.CustomButton_btn_strokeWidth, 0);

            mPressedColor = typedArray.getColor(R.styleable.CustomButton_btn_pressedColor, 0x33666666);
            isPressEffectEnable = typedArray.getBoolean(R.styleable.CustomButton_btn_isPressEffectEnable,true);
            setGravity(TEXT_ALIGNMENT_CENTER);
            typedArray.recycle();
        }

        //字母全大写
        setAllCaps(false);
        //文字留边距
        setIncludeFontPadding(false);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //默认设置矩形
        mNormalBackgroundDrawable.setShape(GradientDrawable.RECTANGLE);
        //设置背景色
        mNormalBackgroundDrawable.setColor(mBackgroundColor);
        //设置圆角
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        //设置描边
        setNormalDrawableStroke();
        //设置按压效果
        setBackgroundDrawableCompat();
    }

    /**设置描边*/
    private void setNormalDrawableStroke() {
        if (mStrokeColor != 0 && mStrokeWidth > 0) {
            mNormalBackgroundDrawable.setStroke(mStrokeWidth, mStrokeColor, 0,0);
        }
    }

    /**设置按压效果*/
    private void setBackgroundDrawableCompat() {
        // 是否开启点击动效
        if (isPressEffectEnable) {
            // 5.0以上水波纹效果
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setBackground(new RippleDrawable(ColorStateList.valueOf(mPressedColor), mNormalBackgroundDrawable, null));
            }
            // 5.0以下变色效果
            else {
                // 初始化pressed状态
                mPressedDrawable.setColor(mPressedColor);
                mPressedDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mCornerRadius, getResources().getDisplayMetrics()));
                mPressedDrawable.setStroke(mStrokeWidth, mStrokeColor);

                // 注意此处的add顺序，normal必须在最后一个，否则其他状态无效
                // 设置pressed状态
                mStateListDrawable.addState(new int[]{android.R.attr.state_pressed}, mPressedDrawable);
                // 设置normal状态
                mStateListDrawable.addState(new int[]{}, mNormalBackgroundDrawable);
                setBackgroundDrawable(mStateListDrawable);
            }
        } else {
            setBackgroundDrawable(mNormalBackgroundDrawable);
        }
    }

    /**
     * 设置背景色
     * @param backgroundColor 颜色
     */
    @Override
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        mNormalBackgroundDrawable.setColor(mBackgroundColor);
        setBackgroundDrawableCompat();
    }

    /**
     * 设置圆角
     * @param cornerRadius 圆角半径
     */
    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
        mCornerRadiiTopLeft = mCornerRadiiTopRight = mCornerRadiiBottomRight = mCornerRadiiBottomLeft = cornerRadius;
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        setBackgroundDrawableCompat();
    }

    /**
     * 设置左上圆角
     * @param cornerRadiiTopLeft 圆角半径
     */
    public void setCornerRadiiTopLeft(float cornerRadiiTopLeft) {
        mCornerRadiiTopLeft = cornerRadiiTopLeft;
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        setBackgroundDrawableCompat();
    }

    /**
     * 设置右上圆角
     * @param cornerRadiiTopRight 圆角半径
     */
    public void setCornerRadiiTopRight(float cornerRadiiTopRight) {
        mCornerRadiiTopRight = cornerRadiiTopRight;
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        setBackgroundDrawableCompat();
    }

    /**
     * 设置右下圆角
     * @param cornerRadiiBottomRight 圆角半径
     */
    public void setCornerRadiiBottomRight(float cornerRadiiBottomRight) {
        mCornerRadiiBottomRight = cornerRadiiBottomRight;
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        setBackgroundDrawableCompat();
    }

    /**
     * 设置左下圆角
     * @param cornerRadiiBottomLeft 圆角半径
     */
    public void setCornerRadiiBottomLeft(float cornerRadiiBottomLeft) {
        mCornerRadiiBottomLeft = cornerRadiiBottomLeft;
        mNormalBackgroundDrawable.setCornerRadii(getCornerRadii());
        setBackgroundDrawableCompat();
    }

    /**
     * 设置描边颜色
     * @param strokeColor 颜色
     */
    public void setStrokeColor(int strokeColor) {
        mStrokeColor = strokeColor;
        setNormalDrawableStroke();
        setBackgroundDrawableCompat();
    }

    /**
     * 设置描边宽
     * @param strokeWidth 宽度
     */
    public void setStrokeWidth(int strokeWidth) {
        mStrokeWidth = strokeWidth;
        setNormalDrawableStroke();
        setBackgroundDrawableCompat();
    }

    /**
     * 设置按压颜色
     * @param pressedColor 颜色
     */
    public void setPressedColor(int pressedColor) {
        mPressedColor = pressedColor;
        setBackgroundDrawableCompat();
    }

    /**
     * 设置按压效果是否启用
     * @param pressEffectEnable 是否启用
     */
    public void setPressEffectEnable(boolean pressEffectEnable) {
        isPressEffectEnable = pressEffectEnable;
        setBackgroundDrawableCompat();
    }

    /**
     * 获取圆角float数组
     * @return float数组
     */
    private float[] getCornerRadii() {
        return new float[]{mCornerRadiiTopLeft, mCornerRadiiTopLeft,
                mCornerRadiiTopRight, mCornerRadiiTopRight,
                mCornerRadiiBottomRight, mCornerRadiiBottomRight,
                mCornerRadiiBottomLeft, mCornerRadiiBottomLeft};
    }
}

