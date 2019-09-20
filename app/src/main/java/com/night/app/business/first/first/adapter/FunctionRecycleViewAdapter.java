package com.night.app.business.first.first.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.night.app.R;

public class FunctionRecycleViewAdapter extends RecyclerView.Adapter<FunctionRecycleViewAdapter.Holder> {
    private Context mContext;

    private int[]   textIdArr = new int[] { R.string.function_love, R.string.function_partner,
            R.string.function_friends, R.string.function_white_wall, R.string.function_message,
            R.string.function_study_statics, R.string.function_medal ,R.string.function_shop,R.string.function_regional_ranking};

    private int[]   imgIdArr  = new int[] { R.drawable.function_my_study_love_selector,
            R.drawable.function_my_study_partner_selector, R.drawable.function_my_friends_selector,
            R.drawable.function_my_white_wall_selector, R.drawable.function_my_message_selector,
            R.drawable.function_study_data_statics_selector, R.drawable.function_my_medal_selector,R.drawable.function_shop_selector,R.drawable.function_regional_ranking_selector};

    public FunctionRecycleViewAdapter(Context context) {
        mContext=context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_function_container_child, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.functionIconButton.setBackgroundResource(imgIdArr[position]);
        holder.functionNameTextView.setText(textIdArr[position]);
    }

    @Override
    public int getItemCount() {
        return imgIdArr.length;
    }

    class Holder extends RecyclerView.ViewHolder {
        private Button functionIconButton;

        private TextView  functionNameTextView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            functionIconButton = itemView.findViewById(R.id.funcaton_container_iv_child_function_icon);
            functionNameTextView = itemView.findViewById(R.id.function_container_tv_child_function_name);
        }
    }
}
