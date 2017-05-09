package com.wangsanshi.gank.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

public class ViewUtil {
    /*
     * 判断recyclerview是否到底部
     * @param RecyclerView
     * @return true为已经到底部，false为没有到底部
     */
    public static boolean isSlideToBottom(@NonNull RecyclerView recyclerView) {
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

}
