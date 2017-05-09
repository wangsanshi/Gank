package com.wangsanshi.gank;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private onItemRemoveListener listener;

    public ItemTouchHelperCallback(onItemRemoveListener listener) {
        this.listener = listener;
    }

    /*
     * 设置拖动的方向以及侧滑的方向
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final int dragFlag = 0;//不支持拖拽
            final int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;//支持左右侧滑
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    /*
     * 拖动item回调此方法
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    /*
     * 侧滑回调此方法
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemRemove(viewHolder.getAdapterPosition());
    }
}
