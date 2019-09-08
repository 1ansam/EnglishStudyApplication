package com.night.app.business.activity.adapter;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.app.business.activity.wrapper.ActivityWrapper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityRecyclerViewAdapter extends BaseRecyclerAdapter<ActivityWrapper> {

    public ActivityRecyclerViewAdapter(Context context, List<ActivityWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, ActivityWrapper activityWrapper) {
        TextView activityTvTitle = (TextView) holder.getView(R.id.activity_item_tv_title);
        ImageView activityIvImg = (ImageView) holder.getView(R.id.activity_item_iv_img);
        TextView activityTvTime = (TextView) holder.getView(R.id.activity_item_tv_time);
        activityTvTitle.setText(activityWrapper.getTitle());
        activityIvImg.setImageResource(activityWrapper.getImgId());
        activityTvTime.setText(activityWrapper.getTime());

        if (activityWrapper.isValidity()) {
            activityTvTime.setTextColor(context.getResources().getColor(R.color.text_dark_black));
            activityTvTime.setTextColor(context.getResources().getColor(R.color.text_dark_black));
        }
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_activity_child;
    }

}
