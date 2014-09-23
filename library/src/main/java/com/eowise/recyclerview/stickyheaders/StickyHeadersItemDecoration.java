package com.eowise.recyclerview.stickyheaders;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

/**
 * Created by aurel on 22/09/14.
 */
public class StickyHeadersItemDecoration extends RecyclerView.ItemDecoration {

    private final StickyHeadersAdapter adapter;
    private final RecyclerView.ViewHolder headerViewHolder;
    private final HashMap<Integer, Long> headersIds;
    private HeaderPosition headerPosition;

    private final int headerHeight;

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, RecyclerView parent) {
        this(adapter, parent, HeaderPosition.TOP);
    }

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, RecyclerView parent, HeaderPosition headerPosition) {
        this.adapter = adapter;
        this.headerViewHolder = adapter.onCreateViewHolder(parent);
        this.headerPosition = headerPosition;
        this.headersIds = new HashMap<Integer, Long>();

        int widthSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.UNSPECIFIED);
        headerViewHolder.itemView.measure(widthSpec, heightSpec);
        headerHeight = headerViewHolder.itemView.getMeasuredHeight();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        View header = headerViewHolder.itemView;
        Long currentHeaderId;
        Float lastY = null;

        if (!header.isLaidOut()) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.AT_MOST);
            header.measure(widthSpec, heightSpec);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
        }

        for (int i = childCount - 1; i >= 0; i--) {
            final View child = parent.getChildAt(i);
            final RecyclerView.ViewHolder itemViewHolder = parent.getChildViewHolder(child);
            currentHeaderId = getHeaderId(itemViewHolder, itemViewHolder.getPosition());

            if (i == 0 || !currentHeaderId.equals(getHeaderId(parent, i - 1))) {

                float y = getHeaderY(child);

                if (lastY != null && lastY - headerHeight < y) {
                    y = lastY - headerHeight;
                }

                adapter.onBindViewHolder(headerViewHolder, itemViewHolder, itemViewHolder.getPosition());

                c.save();
                c.translate(0, y);
                header.draw(c);
                c.restore();

                lastY = y;
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {

        if (itemPosition != 0 && getHeaderId(parent.findViewHolderForPosition(itemPosition), itemPosition) == getHeaderId(parent.findViewHolderForPosition(itemPosition - 1), itemPosition)) {
            outRect.set(0, 0, 0, 0);
        }
        else {
            switch (headerPosition) {
                case LEFT:
                    outRect.set(headerHeight, 0, 0, 0);
                    break;
                case TOP:
                    outRect.set(0, headerHeight, 0, 0);
                    break;
                case RIGHT:
                    outRect.set(0, 0, headerHeight, 0);
                    break;
                case BOTTOM:
                    outRect.set(0, 0, 0, headerHeight);
                    break;
                case OVERLAY:
                    outRect.set(0, 0, 0, 0);
                    break;
            }
        }

    }

    private float getHeaderY(View item) {

        float y;
        switch (headerPosition) {
            case TOP:
                y = item.getY() < headerHeight ? 0 : item.getY() - headerHeight;
                break;
            case BOTTOM:
                y = item.getY() + item.getHeight() < headerHeight ? 0 : item.getY() + item.getHeight();
                break;
            default:
                y = item.getY() < 0 ? 0 : item.getY();
                break;
        }

        return y;
    }

    private long getHeaderId(RecyclerView parent, int childPosition) {
        final View child = parent.getChildAt(childPosition);
        final RecyclerView.ViewHolder itemViewHolder = parent.getChildViewHolder(child);

        return getHeaderId(itemViewHolder, itemViewHolder.getPosition());
    }

    private long getHeaderId(RecyclerView.ViewHolder itemViewHolder, int dataSetPosition) {
        if (!headersIds.containsKey(dataSetPosition)) {
            headersIds.put(itemViewHolder.getPosition(), adapter.getHeaderId(itemViewHolder, itemViewHolder.getPosition()));
        }

        return headersIds.get(dataSetPosition);
    }

}
