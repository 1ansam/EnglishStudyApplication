package com.night.basecore.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by jiangry on 2016/4/8.
 */
public class DisplayUtil {
    public static final int RESOLUTION_LOW  = 0;

    public static final int RESOLUTION_MID  = 1;

    public static final int RESOLUTION_HIGH = 2;
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param context   （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param context    （DisplayMetrics类中属性density）
     * @return
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取当前分辨率
     *
     * @param context
     * @return
     */
    public static int getSolution(Context context) {
        int solution = RESOLUTION_HIGH;
        DisplayMetrics dm = new DisplayMetrics();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            int screenW = dm.widthPixels;
            if (screenW <= 480) {
                solution = RESOLUTION_LOW;
            } else if (480 < screenW && screenW <= 720) {
                solution = RESOLUTION_MID;
            } else {
                solution = RESOLUTION_HIGH;
            }
        }
        return solution;
    }
}
