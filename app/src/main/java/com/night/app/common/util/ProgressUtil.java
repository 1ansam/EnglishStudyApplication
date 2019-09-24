package com.night.app.common.util;


import android.app.Activity;
import android.widget.RelativeLayout;

import com.night.app.R;

public class ProgressUtil {
    public static void showProgress(Activity activity, int visibility){
        RelativeLayout progressLayout =activity.findViewById(R.id.common_progress_layout);
        progressLayout.setVisibility(visibility);
    }
}
