package com.night.app.business.safetycenter.adapter;

import com.night.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SafetyCenterRecycleViewAdapter extends RecyclerView.Adapter<SafetyCenterRecycleViewAdapter.Holder> {
    private Context   mContext;

    private int[]     titleTextIdArr   = { R.string.safety_center_edit_pwd,R.string.safety_center_login_out};


    public SafetyCenterRecycleViewAdapter(Context context) {
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
    }

    @Override
    public int getItemCount() {
        return titleTextIdArr.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView  titileTextView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            titileTextView = itemView.findViewById(R.id.common_item_tv_title);
        }
    }
}
