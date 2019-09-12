package com.night.app.business.account.personalization.adapter;

import com.night.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalPlanRecycleViewAdapter extends RecyclerView.Adapter<PersonalPlanRecycleViewAdapter.Holder> {
    private Context   mContext;

    private int[]     titleTextIdArr   = { R.string.personal_plan_day_target_number,
            R.string.personal_plan_remind_time };

    private String[]  bodyTextArr      = { "50","20:00" };

    public PersonalPlanRecycleViewAdapter(Context context) {
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
        holder.bodyTextView.setText(bodyTextArr[position]);
    }

    @Override
    public int getItemCount() {
        return titleTextIdArr.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private TextView  titileTextView;

        private TextView  bodyTextView;


        public Holder(@NonNull View itemView) {
            super(itemView);
            titileTextView = itemView.findViewById(R.id.common_item_tv_title);
            bodyTextView = itemView.findViewById(R.id.common_item_tv_body);
        }
    }
}
