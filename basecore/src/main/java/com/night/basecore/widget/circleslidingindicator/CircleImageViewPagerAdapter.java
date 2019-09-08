package com.night.basecore.widget.circleslidingindicator;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class CircleImageViewPagerAdapter extends PagerAdapter{
    private ArrayList<ImageView> mList = new ArrayList<>();

    public CircleImageViewPagerAdapter(ArrayList<ImageView> list) {
         mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
