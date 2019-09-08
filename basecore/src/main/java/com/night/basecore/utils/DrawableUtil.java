package com.night.basecore.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.RadioButton;

public class DrawableUtil {

    public static Drawable setDrawableSize(EditText editText, int width, int height,int direction){
        Drawable[] drawable=new Drawable[2];
        drawable=editText.getCompoundDrawables();
        Rect r = new Rect(0,0,width,height);
        drawable[direction].setBounds(r);
        return drawable[direction];
    }

    public static Drawable setDrawableSize(RadioButton radioButton, int width, int height,int direction){
        Drawable[] drawable=new Drawable[2];
        drawable=radioButton.getCompoundDrawables();
        Rect r = new Rect(0,0,width,height);
        drawable[direction].setBounds(r);
        return drawable[direction];
    }
}
