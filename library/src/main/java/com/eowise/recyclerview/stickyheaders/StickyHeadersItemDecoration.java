package com.eowise.recyclerview.stickyheaders;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aurel on 22/09/14.
 */
public class StickyHeadersItemDecoration extends RecyclerView.ItemDecoration {

    private final StickyHeadersAdapter adapter;
    private final RecyclerView.ViewHolder headerViewHolder;

    private final int itemHeight;

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, RecyclerView parent) {
        this.adapter = adapter;
        this.headerViewHolder = adapter.onCreateViewHolder(parent);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.UNSPECIFIED);
        headerViewHolder.itemView.measure(widthSpec, heightSpec);
        itemHeight = headerViewHolder.itemView.getMeasuredHeight();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        View header = headerViewHolder.itemView;

        if (!header.isLaidOut()) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.UNSPECIFIED);
            header.measure(widthSpec, heightSpec);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            float y = child.getY() < itemHeight ? 0 : child.getY() - itemHeight;
            y = child.getY() + child.getHeight() < itemHeight ? y - (itemHeight - (child.getY() + child.getHeight())) : y;
            final RecyclerView.ViewHolder itemViewHolder = parent.getChildViewHolder(child);

            adapter.onBindViewHolder(headerViewHolder, itemViewHolder, itemViewHolder.getPosition());

            c.save();
            c.translate(0, y);
            header.draw(c);
            c.restore();
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        outRect.set(0, itemHeight, 0, 0);
    }

}
