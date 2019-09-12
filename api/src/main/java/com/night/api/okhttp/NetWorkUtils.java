package com.night.api.okhttp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 1. 类的用途 联网判断
 */

public class NetWorkUtils {
    // 判断网络是否连接
    public static boolean isNetWorkAvailable(Context context) {
        // 网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        // 网络信息
        NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        if (info != null) {
            return true;
        }

        return false;
    }

}
