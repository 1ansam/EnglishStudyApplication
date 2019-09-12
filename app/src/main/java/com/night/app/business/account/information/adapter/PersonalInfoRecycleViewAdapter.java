package com.night.app.business.account.information.adapter;

import com.night.app.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonalInfoRecycleViewAdapter extends RecyclerView.Adapter<PersonalInfoRecycleViewAdapter.Holder> {
    private Context   mContext;

    private int[]     titleTextIdArr   = { R.string.personal_info_account, R.string.personal_info_pet_name,R.string.personal_info_level,
            R.string.personal_info_sex, R.string.personal_info_birthday, R.string.personal_info_weixin,
            R.string.personal_info_qq, R.string.personal_info_mail };

    private String[]  bodyTextArr      = { "15851878566", "NightStalker", "LV5","男", "1998-02-24", "未绑定", "2498188261",
            "未绑定" };

    private boolean[] displayImageView = { false, true, false,false, false, true, true, true };

    public PersonalInfoRecycleViewAdapter(Context context) {
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
        if(displayImageView[position]){
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
        private TextView  titileTextView;

        private TextView  bodyTextView;

        private ImageView forwardImageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            titileTextView = itemView.findViewById(R.id.common_item_tv_title);
            bodyTextView = itemView.findViewById(R.id.common_item_tv_body);
            forwardImageView = itemView.findViewById(R.id.common_item_iv_forward);
        }
    }
}
