package com.night.basecore.utils;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListUtil {
    public static ArrayList<ImageView> resourceArr2List(Context context, int[] imgRes){
        ArrayList<ImageView> list = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(imgRes[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(iv);
        }
        return list;
    }
}
