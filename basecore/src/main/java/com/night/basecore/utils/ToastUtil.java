package com.night.basecore.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showLongToast(Context c, String str) {
        if (str == null) {
            return;
        }
        Toast.makeText(c,str,Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context c, int strId) {
        if (strId <= 0) {
            return;
        }
        Toast.makeText(c,String.valueOf(strId),Toast.LENGTH_LONG);
    }

    public static void showShortToast(Context c, String str) {
        if (str == null) {
            return;
        }
        Toast.makeText(c,str,Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context c, int strId) {
        if (strId <= 0) {
            return;
        }
        Toast.makeText(c,String.valueOf(strId),Toast.LENGTH_SHORT);
    }
}

