package com.night.basecore.utils;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static ArrayList<ImageView> resourceArr2List(Context context, int[] imgRes) {
        ArrayList<ImageView> list = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(imgRes[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(iv);
        }
        return list;
    }

    public static List<String> getStringList(String str) {
        List<String> list = new ArrayList<>();
        list.add(str);
        return list;
    }

    public static List<Integer> getIntegerList(Integer integer) {
        List<Integer> list = new ArrayList<>();
        list.add(integer);
        return list;
    }

}
