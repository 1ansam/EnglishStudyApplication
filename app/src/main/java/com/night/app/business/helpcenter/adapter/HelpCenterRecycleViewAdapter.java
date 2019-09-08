package com.night.app.business.helpcenter.adapter;

import com.night.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HelpCenterRecycleViewAdapter extends RecyclerView.Adapter<HelpCenterRecycleViewAdapter.Holder> {
    private Context   mContext;

    private int[]     titleTextIdArr    = { R.string.help_center_about_use, R.string.help_center_about_us,
            R.string.help_center_about_forgetting_curve, R.string.help_center_submit_comment };

    private boolean[] displayFrowardBtn = { false, false, false, true };

    public HelpCenterRecycleViewAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_item_list_linear_child, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.titileTextView.setText(titleTextIdArr[position]);
        if(displayFrowardBtn[position]){
            holder.forwardImageView.setVisibility(View.VISIBLE);
        }else{
            holder.forwardImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return titleTextIdArr.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView titileTextView;
        private ImageView forwardImageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            titileTextView = itemView.findViewById(R.id.common_item_tv_title);
            forwardImageView=itemView.findViewById(R.id.common_item_iv_forward);
        }
    }
}
