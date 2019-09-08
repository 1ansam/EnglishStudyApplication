/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.night.basecore.widget.verticalviewpager;

import android.content.Context;

import android.os.Handler;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * 垂直滑动的ViewPager
 */
public class VerticalViewPager extends ViewPager {

    private static final int MOVE_LIMITATION = 200;  // 触发移动的像素距离

    private float            lastMotionY;            // 手指触碰屏幕的最后一次y坐标

    private int              scrollSpeed     = 400;  // 页面之间切换时间（速度）

    /** 计时器线程 */
    private Handler          handler;

    private timeRun          run;

    /** 是否定时播放 */
    private boolean          isTime          = false;

    /** 轮播时间 */
    private int              cirlceTime      = 5000;

    public VerticalViewPager(Context context) {
        this(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置viewpage的切换动画,这里设置才能真正实现垂直滑动的viewpager
        setPageTransformer(true, new VerticalTransformer());
        // 设置viewpager滑动切换速度
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(), new AccelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(scrollSpeed);
        } catch (Exception e) {

        }
    }

    /**
     * 拦截touch事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = super.onInterceptTouchEvent(swapEvent(ev));
        swapEvent(ev);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        final float y = ev.getY();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            lastMotionY = y;
            break;
        case MotionEvent.ACTION_MOVE:
            break;
        case MotionEvent.ACTION_UP:
            if (lastMotionY - y > MOVE_LIMITATION) {
                setCurrentItem(getCurrentItem() + 1, true);
                return true;
            } else if (y - lastMotionY > MOVE_LIMITATION) {
                setCurrentItem(getCurrentItem() - 1, true);
                return true;
            }
            break;
        default:
            break;
        }
        return super.onTouchEvent(swapEvent(ev));
    }

    /**
     * 转换x,y轴坐标
     * @param event
     * @return
     */
    private MotionEvent swapEvent(MotionEvent event) {
        // 获取宽高
        float width = getWidth();
        float height = getHeight();
        // 将Y轴的移动距离转变成X轴的移动距离
        float swappedX = (event.getY() / height) * width;
        // 将X轴的移动距离转变成Y轴的移动距离
        float swappedY = (event.getX() / width) * height;
        // 重设event的位置
        event.setLocation(swappedX, swappedY);
        return event;
    }

    private void setHandler() {
        handler = new Handler();
        run = new timeRun();
        handler.postDelayed(run, cirlceTime);
    }

    public void stopAutoCircle() {
        handler.removeCallbacks(run);
    }

    public void resumenAutoCircle(int cirlceTime) {
        this.cirlceTime = cirlceTime;
        setHandler();
    }

    /**
     * 设置定时切换到下一个
     */
    class timeRun implements Runnable {
        @Override
        public void run() {
            setCurrentItem(getCurrentItem() + 1, true);
            handler.postDelayed(run, cirlceTime);
        }
    }

    public void setScrollSpeed(int time) {
        scrollSpeed = time;
    }

}
