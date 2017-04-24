package com.wangsanshi.gank.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider
    };

    private Drawable divider;

    public DividerItemDecoration(Context context) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        this.divider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontalLine(c, parent);
    }

    private void drawHorizontalLine(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();

            int top = childView.getBottom() + params.bottomMargin / 2;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, divider.getIntrinsicHeight());
    }

}
