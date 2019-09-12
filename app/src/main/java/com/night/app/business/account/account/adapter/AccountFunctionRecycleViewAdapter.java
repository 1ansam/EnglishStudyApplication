package com.night.app.business.account.account.adapter;

import com.night.app.R;
import com.night.app.base.adapter.BaseRecyclerAdapter;
import com.night.model.wrapper.CommonFunctionWrapper;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AccountFunctionRecycleViewAdapter extends BaseRecyclerAdapter<CommonFunctionWrapper> {

    public AccountFunctionRecycleViewAdapter(Context context, List<CommonFunctionWrapper> data) {
        super(context, data);
    }

    @Override
    public void bindData(BaseViewHolder holder, CommonFunctionWrapper commonFunctionWrapper) {
        Button btnLetter = (Button) holder.getView(R.id.common_grid_btn_letter);
        TextView tvName = (TextView) holder.getView(R.id.common_grid_tv_name);
        LinearLayout layout=(LinearLayout)holder.getView(R.id.common_grid_layout);

        btnLetter.setText(commonFunctionWrapper.getTitleIconId());
        tvName.setText(commonFunctionWrapper.getTitleNameId());
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.common_item_grid_child;
    }
}
