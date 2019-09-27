package com.night.basecore.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

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
        Object color = new ForegroundColorSpan(ContextCompat.getColor(context, colorId));
        return setSpan(color, needChangeText);
    }

    /**
     * 不需要再次写入needChangeText,延续前面第一个needChangeText
     * @param colorId
     * @return
     */
    public SpannableStringUtil setColor(int colorId) {
        initNeedChangeString();
        return setColor(needChangeString, colorId);
    }

    /**
     * 设置字体样式
     * @param needChangeString
     * @param type default,default-bold,monospace,serif,sans-serif
     * @return
     */
    public SpannableStringUtil setTypeface(String needChangeString, String type) {
        Object typeFace = new TypefaceSpan(type);
        return setSpan(typeFace, needChangeString);
    }

    /**
     * 设置字体，延续前一个needChangeString
     * @param type default,default-bold,monospace,serif,sans-serif
     * @return
     */
    public SpannableStringUtil setTypeFace(String type) {
        initNeedChangeString();
        return setTypeface(needChangeString, type);
    }

    /**
     * 设置字体绝对大小
     * @param needChangeString
     * @param size 单位dip
     * @return
     */
    public SpannableStringUtil setAbsoluteSize(String needChangeString, int size) {
        Object sizeSpan = new AbsoluteSizeSpan(size, true);
        return setSpan(sizeSpan, needChangeString);
    }

    /**
     * 设置字体绝对大小
     * @param size
     * @return
     */
    public SpannableStringUtil setAbsoluteSize(int size) {
        initNeedChangeString();
        return setAbsoluteSize(needChangeString, size);
    }

    /**
     * 设置字体相对大小
     * @param needChangeString
     * @param size 参数表示为默认字体大小的多少倍
     * @return
     */
    public SpannableStringUtil setRelativeSize(String needChangeString, float size) {
        Object sizeSpan = new RelativeSizeSpan(size);
        return setSpan(sizeSpan, needChangeString);
    }

    /**
     * 设置字体相对大小
     * @param size
     * @return
     */
    public SpannableStringUtil setRelativeSize(float size) {
        initNeedChangeString();
        return setRelativeSize(needChangeString, size);
    }

    /**
     * 设置字体背景颜色
     * @param needChangeString
     * @param colorId
     * @return
     */
    public SpannableStringUtil setBackgroundColor(String needChangeString, int colorId) {
        Object color = new BackgroundColorSpan(ContextCompat.getColor(context, colorId));
        return setSpan(color, needChangeString);
    }

    /**
     * 设置字体背景颜色
     * @param colorId
     * @return
     */
    public SpannableStringUtil setBackgroundColor(int colorId) {
        initNeedChangeString();
        return setBackgroundColor(needChangeString, colorId);
    }

    /**
     * 设置字体样式
     * @param needChangeString
     * @param textStyle Typeface. NORMAL正常，BOLD粗体，ITALIC斜体，BOLD_ITALIC粗斜体
     * @return
     */
    public SpannableStringUtil setStyle(String needChangeString, int textStyle) {
        Object style = new StyleSpan(textStyle);
        return setSpan(style, needChangeString);
    }

    /**
     * 设置字体样式
     * @param textStyle Typeface. NORMAL正常，BOLD粗体，ITALIC斜体，BOLD_ITALIC粗斜体
     * @return
     */
    public SpannableStringUtil setStyle(int textStyle) {
        initNeedChangeString();
        return setStyle(needChangeString, textStyle);
    }

    /**
     * 设置下划线
     * @param needChangeString
     * @return
     */
    public SpannableStringUtil setUnderline(String needChangeString) {
        Object underline = new UnderlineSpan();
        return setSpan(underline, needChangeString);
    }

    /**
     * 设置删除线
     * @param needChangeString
     * @return
     */
    public SpannableStringUtil setStrikethrough(String needChangeString) {
        Object strikethrough = new StrikethroughSpan();
        return setSpan(strikethrough, needChangeString);
    }

    /**
     * 设置下标
     * @param needChangeString
     * @return
     */
    public SpannableStringUtil setSubscript(String needChangeString) {
        Object subscript = new SubscriptSpan();
        return setSpan(subscript, needChangeString);
    }

    /**
     * 设置上标
     * @param needChangeString
     * @return
     */
    public SpannableStringUtil setSuperscript(String needChangeString) {
        Object superscript = new SuperscriptSpan();
        return setSpan(superscript, needChangeString);
    }

    /**
     * 设置字体相对宽度,高度不变
     * @param needChangeString
     * @param width
     * @return
     */
    public SpannableStringUtil setScaleXSpan(String needChangeString, float width) {
        Object widthObject = new ScaleXSpan(width);
        return setSpan(widthObject, needChangeString);
    }

    /**
     * 设置超链接
     * @param needChangeString
     * @param url
     * @param textView  给textView开启点击
     * @return
     */
    public SpannableStringUtil setUrl(String needChangeString, String url, TextView textView) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Object urlObject = new URLSpan(url);
        return setSpan(urlObject, needChangeString);
    }

    /**
     * 设置超链接
     * @param url
     * @param textView  给textView开启点击
     * @return
     */
    public SpannableStringUtil setUrl(String url, TextView textView) {
        initNeedChangeString();
        return setUrl(needChangeString, url, textView);
    }

    /**
     * 设置点击事件
     * @param needChangeString
     * @param textView
     * @param clickListener
     * @return
     */
    public SpannableStringUtil setClickListener(String needChangeString, TextView textView,ClickableSpan clickListener){
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return setSpan(clickListener,needChangeString);
    }

    /**
     * 设置点击事件
     * @param textView
     * @param clickListener
     * @return
     */
    public SpannableStringUtil setClickListener(TextView textView,ClickableSpan clickListener){
        initNeedChangeString();
        return setClickListener(needChangeString,textView,clickListener);
    }

    public SpannableStringUtil setSpan(Object object, String needChangeString) {
        this.needChangeString = needChangeString;
        int startIndex = spannableString.toString().indexOf(needChangeString);
        int endIndex = startIndex + needChangeString.length();
        spannableString.setSpan(object, startIndex, endIndex, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return this;
    }

    public void initNeedChangeString() {
        if (needChangeString == null) {
            needChangeString = spannableString.toString();
        }
    }

    public SpannableString getSpannableString() {
        return spannableString;
    }

}
