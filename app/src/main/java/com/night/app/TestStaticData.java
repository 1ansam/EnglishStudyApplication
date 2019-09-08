package com.night.app;

import com.night.app.business.activity.wrapper.ActivityWrapper;

import java.util.ArrayList;
import java.util.List;

public class TestStaticData {

    public static List<ActivityWrapper> getActivityWrapper() {
        String[] titleArr = { "七夕,来啾咪赢华为P30 Pro!", "啾咪亲友团,邀请享好礼!", "啾咪有礼,欢乐领取京东卡", "我爱我家", "新手大礼包,终于等到你" };

        int[] imgIdArr = { R.mipmap.activity_item_background_double_seventh, R.mipmap.activity_item_background_invite,
                R.mipmap.activity_item_background_618, R.mipmap.activity_item_background_lovew_my_family,
                R.mipmap.activity_item_background_novice_gift };

        String[] timeArr = { "活动已结束", "8月8日-9月21日", "活动已结束", "活动长期有效", "活动长期有效" };

        boolean[] activityValidity = { false, true, false, true, true };

        List<ActivityWrapper> list =new ArrayList<>();
        for(int i=0;i<titleArr.length;i++){
            ActivityWrapper wrapper = new ActivityWrapper();
            wrapper.setTitle(titleArr[i]);
            wrapper.setImgId((imgIdArr[i]));
            wrapper.setTime(timeArr[i]);
            wrapper.setValidity(activityValidity[i]);
            list.add(wrapper);
        }
        return list;
    }
}
