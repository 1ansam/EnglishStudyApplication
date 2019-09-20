package com.night.basecore.widget.spannablestring;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

public class BaseClickableSpan extends ClickableSpan {
    private Context mContext;

    public BaseClickableSpan(Context context) {
        this.mContext = context;
    }


    @Override
    public void onClick(@NonNull View view) {

    }
}
