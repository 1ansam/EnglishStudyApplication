package com.night.basecore.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class TranslateAnimationUtil {

    /**
     * 消失动画
     * @param number 动作持续时间
     * @param direction 动作消失方向
     * @return
     */
    public static TranslateAnimation getHideAnim(int number, String direction) {
        float[] directionFloatArr=getDirectionFloatArr(direction,View.GONE);
        return getTranslationAnimation(directionFloatArr,number);
    }

    /**
     * VISIBLE动画
     * @param number  动作持续时间
     * @param direction 动作出现方向
     * @return
     */
    public static TranslateAnimation getShowAnim(int number,String direction) {
        float[] directionFloatArr=getDirectionFloatArr(direction,View.VISIBLE);
        return getTranslationAnimation(directionFloatArr,number);
    }

    public static float[] getDirectionFloatArr(String direction,int viewAction){
        float fromXValue = 0.0f;
        float toXValue = 0.0f;
        float fromYValue = 0.0f;
        float toYValue = 0.0f;
        if(viewAction== View.GONE){
            if ("right".equals(direction)) {
                toXValue=1.0f;
            }else if("left".equals(direction)){
                toXValue=-1.0f;
            }else if("up".equals(direction)){
                toYValue=1.0f;
            }else if("down".equals(direction)){
                toYValue=-1.0f;
            }
        }
        if(viewAction==View.VISIBLE){
            if ("right".equals(direction)) {
                fromXValue=-1.0f;
            }else if("left".equals(direction)){
                fromXValue=1.0f;
            }else if("up".equals(direction)){
                fromYValue=-1.0f;
            }else if("down".equals(direction)){
                fromYValue=1.0f;
            }
        }
        return new float[]{fromXValue,toXValue,fromYValue,toYValue};
    }

    public static TranslateAnimation getTranslationAnimation(float[] directionFloatArr,int number){
        TranslateAnimation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, directionFloatArr[0],
                Animation.RELATIVE_TO_SELF, directionFloatArr[1], Animation.RELATIVE_TO_SELF,directionFloatArr[2], Animation.RELATIVE_TO_SELF,directionFloatArr[3]);
        anim.setDuration(number);
        return anim;
    }
}
