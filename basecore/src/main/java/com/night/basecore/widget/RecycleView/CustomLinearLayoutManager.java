package com.night.basecore.widget.RecycleView;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean canVerticalScroll = true;

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }


    @Override
    public boolean canScrollVertically() {
        if(canVerticalScroll){
            return super.canScrollVertically();
        }else{
            return false;
        }
    }

    public void setCanVerticalScroll(boolean canVerticalScroll) {
        this.canVerticalScroll = canVerticalScroll;
    }
}
