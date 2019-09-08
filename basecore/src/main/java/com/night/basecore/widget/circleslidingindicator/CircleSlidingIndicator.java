package com.night.basecore.widget.circleslidingindicator;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.night.basecore.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author zwt create by 2019.8.16
 */
public class CircleSlidingIndicator extends FrameLayout {
    private Context                     context;

    /** 计时器线程 */
    private Handler                     handler;

    private timeRun                     run;

    /** 轮播时间 */
    private int                         time                                    = 5000;

    /** 设置页面切换的速度 */
    private Integer                     scrollSpeed;

    /** 背景圆点个数 */
    private int                         number                                  = 4;

    /** 背景圆点布局 */
    private LinearLayout                backgroundLayout;

    /** 背景圆点资源 */
    private int                         backgroundPointId                       = R.drawable.circular_indicator_background;

    /** 移动圆点布局 */
    private ImageView                   floatPointImageView;

    /** 移动圆点的宽度 */
    private int                         floatPointWidth                         = 30;

    /** 移动圆点的高度 */
    private int                         floatPointHeight                        = 30;

    /** 移动圆点左边距 */
    float                               floatPointMarginLeftSpace               = 0;

    /** 移动圆点资源 */
    private int                         floatPointId                            = R.drawable.circular_indicator_move;

    // * 点之间间隔*/
    private int                         backgroundPointSpace                    = 60;

    /** 指示器（背景圆点+移动圆点）总布局 */
    private FrameLayout                 circleSlidingIndicatorLayout;

    /** 指示器到bannar底端距离 */
    private int                         circleSlidingIndicatorMarginBottomSpace = 30;

    /** 图片viewpager布局 */
    private ViewPager                   circleSlidingIndicatorViewPager;

    /** 图片viewpager背景 */
    private Integer                     viewPagerImageViewBackground;

    /** 适配器 */
    private CircleImageViewPagerAdapter indicatorAdapter;

    /** 是否循环 */
    private boolean                     isCircle                                = false;

    public CircleSlidingIndicator(Context context) {
        this(context, null);
    }

    public CircleSlidingIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleSlidingIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * 将图片资源id转换为对应的ImageView的集合
     * @param context
     * @param isCircle
     * @param resList
     * @return
     */
    public ArrayList<ImageView> getImgViewById(Context context, boolean isCircle, ArrayList<Integer> resList) {
        ArrayList<Integer> list = new ArrayList<>();
        list = resList;
        if (isCircle) {
            list.add(0, resList.get(resList.size() - 1));
            list.add(resList.get(1));
        }

        ArrayList<ImageView> imgList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(list.get(i));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            if (viewPagerImageViewBackground != null) {
                iv.setBackgroundResource(viewPagerImageViewBackground);
            }
            imgList.add(iv);
        }
        setCircle(isCircle);
        return imgList;
    }

    /**
     * @param isCircle
     * @param list
     * @return
     */
    public ArrayList<ImageView> getImgViewByView(boolean isCircle, ArrayList<ImageView> list) {
        if (isCircle) {
            list.add(0, list.get(list.size() - 1));
            list.add(list.get(1));
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setScaleType(ImageView.ScaleType.FIT_XY);
            if (viewPagerImageViewBackground != null) {
                list.get(i).setBackgroundResource(viewPagerImageViewBackground);
            }
        }
        return list;
    }

    /**
     * 轮询展示
     */
    public void initSlidingIndicator() {
        init(context, floatPointWidth, floatPointHeight, circleSlidingIndicatorMarginBottomSpace);
        setOnClick();
    }

    /**
     * 初始化参数
     * @param context
     * @param width
     * @param height
     * @param bottomSpace
     */
    private void init(Context context, int width, int height, int bottomSpace) {
        // 创建指示器总布局 帧布局 宽度=（圆点间距+圆点宽度）*背景圆点个数
        circleSlidingIndicatorLayout = new FrameLayout(context);
        LayoutParams frameParams = new LayoutParams((backgroundPointSpace + width) * number,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        frameParams.bottomMargin = bottomSpace;
        frameParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        circleSlidingIndicatorLayout.setLayoutParams(frameParams);

        // 创建背景圆点布局
        backgroundLayout = new LinearLayout(context);
        backgroundLayout.setOrientation(LinearLayout.HORIZONTAL);
        backgroundLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        // 背景圆点设置 左右圆点两边为1/2间距 两点之间为一个间距
        for (int i = 0; i < number; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(backgroundPointId);
            MarginLayoutParams mlpBackground = new MarginLayoutParams(width, height);
            mlpBackground.setMargins(backgroundPointSpace / 2, 0, backgroundPointSpace / 2, 0);
            imageView.setLayoutParams(mlpBackground);
            backgroundLayout.addView(imageView);
        }

        // 移动圆点设置
        floatPointImageView = new ImageView(context);
        floatPointImageView.setBackgroundResource(floatPointId);
        MarginLayoutParams mlpSliding = new MarginLayoutParams(width, height);
        floatPointImageView.setLayoutParams(mlpSliding);

        // viewpager设置
        circleSlidingIndicatorViewPager = new ViewPager(context);
        circleSlidingIndicatorViewPager
                .setLayoutParams((new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)));
        circleSlidingIndicatorViewPager.setAdapter(indicatorAdapter);

        // 设置viewpager滑动切换速度
        if (scrollSpeed != null) {
            try {
                Field field = ViewPager.class.getDeclaredField("mScroller");
                field.setAccessible(true);
                FixedSpeedScroller scroller = new FixedSpeedScroller(circleSlidingIndicatorViewPager.getContext(),
                        new AccelerateInterpolator());
                field.set(circleSlidingIndicatorViewPager, scroller);
                scroller.setmDuration(scrollSpeed);
            } catch (Exception e) {

            }
        }

        // 按顺序添加背景圆点布局和移动圆点布局
        circleSlidingIndicatorLayout.addView(backgroundLayout);
        circleSlidingIndicatorLayout.addView(floatPointImageView);

        // 添加viewpager和圆点指示器
        addView(circleSlidingIndicatorViewPager);
        addView(circleSlidingIndicatorLayout);

        // 如果是循环轮播，则开始页面索引为1，否则为0
        if (isCircle) {
            circleSlidingIndicatorViewPager.setCurrentItem(1);
        } else {
            circleSlidingIndicatorViewPager.setCurrentItem(0);
        }
    }

    /**
     * 动态设置移动圆点左边距来作为位置
     *
     * @param position
     * @param positionOffset
     */
    public void setViewLayoutParams(int position, float positionOffset) {
        MarginLayoutParams params = (MarginLayoutParams) floatPointImageView.getLayoutParams();
        // 正确的移动距离=(点宽度+点间距)*（position+positionOffset)+0.5*点间距
        floatPointMarginLeftSpace = Math.round(backgroundPointSpace * position + backgroundPointSpace * positionOffset
                + 0.5 * backgroundPointSpace + floatPointWidth * positionOffset + floatPointWidth * position);
        params.leftMargin = Math.round(floatPointMarginLeftSpace);
        floatPointImageView.setLayoutParams(params);
    }

    /**
     * 通过监听当前ViewPager的当前页position，来改变首尾的切换,来达到循环展示的效果
     */
    public void setOnClick() {
        circleSlidingIndicatorViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (isCircle) {
                    position -= 1;
                }
                if ((position == number - 1 && positionOffset > 0.5)) {
                    position = -1;
                    setViewLayoutParams(position, positionOffset);
                } else if ((position == -1) && positionOffset < 0.5) {
                    position = number - 1;
                    setViewLayoutParams(position, positionOffset);
                } else if (position == number) {
                    position = 0;
                    setViewLayoutParams(position, positionOffset);
                } else if (true) {
                    setViewLayoutParams(position, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0 && isCircle) {
                    int currentItem = circleSlidingIndicatorViewPager.getCurrentItem();
                    if (currentItem == number + 1) {
                        circleSlidingIndicatorViewPager.setCurrentItem(1, false);
                    } else if (currentItem == 0) {
                        circleSlidingIndicatorViewPager.setCurrentItem(number, false);
                    }
                }
            }
        });
    }

    /**
     * 设置定时切换到下一个
     */
    class timeRun implements Runnable {
        @Override
        public void run() {
            circleSlidingIndicatorViewPager.setCurrentItem(circleSlidingIndicatorViewPager.getCurrentItem() + 1, true);
            handler.postDelayed(run, time);
        }
    }

    public void stopAutoCircle() {
        handler.removeCallbacks(run);
    }

    public void resumenAutoCircle(int time) {
        this.time = time;
        setHandler();
    }

    /**
     * 对外调用函数,外部调用该函数并设置参数来显示轮询 不循环轮询
     * @param imgResourceList
     * @param isCircle
     */
    public void showCircleSlidingIndicatorByImgId(ArrayList<Integer> imgResourceList, boolean isCircle) {
        showCircleSlidingIndicatorByImgId(imgResourceList, isCircle, null);
    }

    /**
     * 对外调用函数,外部调用该函数并设置参数来显示轮询
     * @param imgResourceList
     * @param isCircle
     * @param scrollSpeed
     */
    public void showCircleSlidingIndicatorByImgId(ArrayList<Integer> imgResourceList, boolean isCircle,
            Integer scrollSpeed) {
        ArrayList<ImageView> list = getImgViewById(context, isCircle, imgResourceList);
        setIndicatorAdapter(new CircleImageViewPagerAdapter(list));
        setNumber(list.size());

        if (scrollSpeed != null) {
            setScrollSpeed(scrollSpeed);
        }

        initSlidingIndicator();
    }

    public void showCircleSlidingIndicatorByImgView(ArrayList<ImageView> imgViewList, boolean isCircle) {
        showCircleSlidingIndicatorByImgView(imgViewList, isCircle, null);
    }

    public void showCircleSlidingIndicatorByImgView(ArrayList<ImageView> imgViewList, boolean isCircle,
            Integer scrollSpeed) {
        ArrayList<ImageView> list = getImgViewByView(isCircle, imgViewList);
        setIndicatorAdapter(new CircleImageViewPagerAdapter(list));
        setNumber(list.size());

        if (scrollSpeed == null) {
            setScrollSpeed(this.scrollSpeed);
        } else {
            setScrollSpeed(scrollSpeed);
        }
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setScrollSpeed(int time) {
        scrollSpeed = time;
    }

    public void setNumber(int number) {
        if (isCircle) {
            this.number = number - 2;
        } else {
            this.number = number;
        }
    }

    public void setBackgroundPointId(int backgroundPointId) {
        this.backgroundPointId = backgroundPointId;
    }

    public void setFloatPointWidth(int floatPointWidth) {
        this.floatPointWidth = floatPointWidth;
    }

    public void setFloatPointHeight(int floatPointHeight) {
        this.floatPointHeight = floatPointHeight;
    }

    public void setFloatPointId(int floatPointId) {
        this.floatPointId = floatPointId;
    }

    public void setBackgroundPointSpace(int backgroundPointSpace) {
        this.backgroundPointSpace = backgroundPointSpace;
    }

    public void setIndicatorAdapter(CircleImageViewPagerAdapter adapter) {
        indicatorAdapter = adapter;
    }

    public void setCircle(boolean circle) {
        this.isCircle = circle;
    }

    private void setHandler() {
        handler = new Handler();
        run = new timeRun();
        handler.postDelayed(run, time);
    }

    public ViewPager getCircleSlidingIndicatorViewPager() {
        return circleSlidingIndicatorViewPager;
    }

    public void setCircleSlidingIndicatorMarginBottomSpace(int circleSlidingIndicatorMarginBottomSpace) {
        this.circleSlidingIndicatorMarginBottomSpace = circleSlidingIndicatorMarginBottomSpace;
    }

    public void setViewPagerImageViewBackground(Integer viewPagerImageViewBackground) {
        this.viewPagerImageViewBackground = viewPagerImageViewBackground;
    }
}
