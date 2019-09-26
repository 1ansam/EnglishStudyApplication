package com.night.app.business.study.test.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class NumberTestViewPager extends ViewPager {
    private boolean isScroll = false;

    public NumberTestViewPager(@NonNull Context context) {
        super(context);
    }

    public NumberTestViewPager(@NonNull Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }
}
