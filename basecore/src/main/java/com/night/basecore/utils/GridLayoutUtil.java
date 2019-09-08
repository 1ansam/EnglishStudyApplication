package com.night.basecore.utils;

import android.view.View;
import android.widget.GridLayout;

import java.util.List;

public class GridLayoutUtil {
    public static void setGridLayout(GridLayout layout, List<View> childViewList, int columnNumber) {
        int childCount = childViewList.size();
        for (int i = 0; i < childCount; i++) {
            // 使用spec定义子控件的位置和比重
            GridLayout.Spec rowSpec = GridLayout.spec(getRowIndex(i, columnNumber), 1f);
            GridLayout.Spec columnSpec = GridLayout.spec(getColumnIndex(i, columnNumber), 1f);
            // 将spec传入GridLayout.LayoutParams并设置宽高为0,必须设置宽高,否则视图异常
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            layoutParams.height = 0;
            layoutParams.width = 0;
            layout.addView(childViewList.get(i), layoutParams);
        }

    }

    /**
     * 通过集合中的索引获得网格布局中的行坐标位置
     * @param positionIndex
     * @param columnNumber
     * @return
     */
    public static int getRowIndex(int positionIndex, int columnNumber) {
        return positionIndex / columnNumber;
    }

    /**
     * 通过集合中的索引获得网格布局中的列坐标位置
     * @param positionIndex
     * @param columnNumber
     * @return
     */
    public static int getColumnIndex(int positionIndex, int columnNumber) {
        return positionIndex % columnNumber;
    }

}
