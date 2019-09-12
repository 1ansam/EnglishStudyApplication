package com.night.app.common.title;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.night.app.R;

public class TitleInitUtil {
    public static void initTitle(final Activity activity, int titleNameId){
        TextView textView=activity.findViewById(R.id.layout_title_tv_title_name);
        ImageView imageView=activity.findViewById(R.id.layout_title_iv_back);

        textView.setText(titleNameId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
               activity.finish();
            }
        });
    }

    public static void initTitle(final Activity activity, String titleName){
        TextView textView=activity.findViewById(R.id.layout_title_tv_title_name);
        ImageView imageView=activity.findViewById(R.id.layout_title_iv_back);

        textView.setText(titleName);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                activity.finish();
            }
        });
    }
}
