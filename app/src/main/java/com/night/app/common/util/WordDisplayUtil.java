package com.night.app.common.util;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;

import com.night.app.R;

public class WordDisplayUtil {
    private CheckBox checkBoxDisplayStyle;

    private CheckBox checkBoxEye;

    private CheckBox checkBoxWordPh;

    public WordDisplayUtil(Activity activity, boolean style, boolean eye, boolean wordPh) {
        initView(activity, style, eye, wordPh);
    }

    /**
     * 对于item_word_display_setting中的控件初始化
     * @param activity
     * @param style
     * @param eye
     * @param wordPh
     */
    public void initView(Activity activity, boolean style, boolean eye, boolean wordPh) {
        if (style) {
            checkBoxDisplayStyle = activity.findViewById(R.id.word_display_setting_check_box_display_style);
            activity.findViewById(R.id.word_display_setting_layout_display_style).setVisibility(View.VISIBLE);
        }
        if (eye) {
            checkBoxEye = activity.findViewById(R.id.word_display_setting_check_box_eye);
            activity.findViewById(R.id.word_display_setting_layout_eye).setVisibility(View.VISIBLE);
        }
        if (wordPh) {
            checkBoxWordPh = activity.findViewById(R.id.word_display_setting_check_box_word_ph);
            activity.findViewById(R.id.word_display_setting_layout_word_ph).setVisibility(View.VISIBLE);
        }
        if (style && eye && wordPh) {
            activity.findViewById(R.id.word_display_setting_first_line).setVisibility(View.VISIBLE);
            activity.findViewById(R.id.word_display_setting_second_line).setVisibility(View.VISIBLE);
        } else if (!style & eye && wordPh) {
            activity.findViewById(R.id.word_display_setting_second_line).setVisibility(View.VISIBLE);
        } else if (style && !eye && wordPh) {
            activity.findViewById(R.id.word_display_setting_second_line).setVisibility(View.VISIBLE);
        } else if (style && !eye && wordPh) {
            activity.findViewById(R.id.word_display_setting_first_line).setVisibility(View.VISIBLE);
        }
    }

    public CheckBox getCheckBoxDisplayStyle() {
        return checkBoxDisplayStyle;
    }

    public CheckBox getCheckBoxEye() {
        return checkBoxEye;
    }

    public CheckBox getCheckBoxWordPh() {
        return checkBoxWordPh;
    }
}
