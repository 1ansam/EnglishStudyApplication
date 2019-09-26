package com.night.basecore.utils;

import android.content.Context;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class SpannableStringUtil {
    private Context         context;

    private SpannableString spannableString;

    private String          needChangeString;

    public SpannableStringUtil(Context context, String str) {
        this.context = context;
        spannableString = new SpannableString(str);
    }

    public SpannableStringUtil(Context context, SpannableString spannableString) {
        this.context = context;
        this.spannableString = spannableString;
    }

    /**
     * 改变指定字的颜色
     * @param needChangeText 需要改变的内容
     * @param colorId 颜色id
     * @return
     */
    public SpannableStringUtil setColor(String needChangeText, int colorId) {
        this.needChangeString = needChangeText;
        int startIndex = spannableString.toString().indexOf(needChangeString);
        int endIndex = startIndex + needChangeString.length();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorId, null)), startIndex,
                    endIndex, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return this;
    }

    /**
     * 不需要再次写入needChangeText,延续前面第一个needChangeText
     * @param colorId
     * @return
     */
    public SpannableStringUtil setColor(int colorId) {
        return setColor(needChangeString, colorId);
    }

    public SpannableString getSpannableString() {
        return spannableString;
    }

}
