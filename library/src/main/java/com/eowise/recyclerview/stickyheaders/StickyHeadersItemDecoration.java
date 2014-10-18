package com.eowise.recyclerview.stickyheaders;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseLongArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by aurel on 22/09/14.
 */
public class StickyHeadersItemDecoration extends RecyclerView.ItemDecoration {

    private final StickyHeadersAdapter adapter;
    private final RecyclerView.ViewHolder headerViewHolder;
    private final ArrayList<Long> headersIds;
    private final AdapterDataObserver adapterDataObserver;
    private HeaderPosition headerPosition;

    private final int headerHeight;

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, RecyclerView parent) {
        this(adapter, parent, HeaderPosition.TOP);
    }

    public StickyHeadersItemDecoration(StickyHeadersAdapter adapter, RecyclerView parent, HeaderPosition headerPosition) {
        this.adapter = adapter;
        this.headerViewHolder = adapter.onCreateViewHolder(parent);
        this.headerPosition = headerPosition;
        this.headersIds = new ArrayList<Long>();
        this.adapterDataObserver = new AdapterDataObserver(headersIds);

        int widthSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.MATCH_PARENT, View.MeasureSpec.AT_MOST);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.UNSPECIFIED);
        headerViewHolder.itemView.measure(widthSpec, heightSpec);
        headerHeight = headerViewHolder.itemView.getMeasuredHeight();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {


        final int childCount = parent.getChildCount();
        final RecyclerView.LayoutManager lm = parent.getLayoutManager();
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

            RecyclerView.ViewHolder holder = parent.getChildViewHolder(child);

            int position = parent.getChildPosition(child);
            if (position >= 0) {

                float translationY = ViewCompat.getTranslationY(child);
                currentHeaderId = getHeaderId(position);

                if (i == 0 || !currentHeaderId.equals(getHeaderId(position - 1))) {

                    float y = getHeaderY(child, lm.getDecoratedTop(child)) + translationY;


                    if (lastY != null && lastY < y + headerHeight) {
                        y = lastY - headerHeight;
                    }


                    adapter.onBindViewHolder(headerViewHolder, position);

                    c.save();
                    c.translate(0, y);
                    header.draw(c);
                    c.restore();

                    lastY = y;
                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


        int itemPosition = parent.getChildPosition(view);

        if (itemPosition != 0 && getHeaderId(itemPosition) == getHeaderId(itemPosition - 1)) {
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

    public void registerAdapterDataObserver(RecyclerView.Adapter adapter) {
        adapter.registerAdapterDataObserver(adapterDataObserver);
    }

    private float getHeaderY(View item, int top) {

        float y;
        switch (headerPosition) {
            case TOP:
                y = top < 0 ? 0 : top;
                break;
            case BOTTOM:
                // TODO: Use getDecoratedBottom
                y = top + item.getHeight() < headerHeight ? 0 : top + item.getHeight();
                break;
            default:
                y = top < 0 ? 0 : top;
                break;
        }

        return y;
    }

    private long getHeaderId(int dataSetPosition) {
        if (headersIds.size() <= dataSetPosition || headersIds.get(dataSetPosition) == null) {

            for (int i = headersIds.size(); i <= dataSetPosition; i++)
                headersIds.add(null);

            headersIds.set(dataSetPosition, adapter.getHeaderId(dataSetPosition));
        }

        return headersIds.get(dataSetPosition);
    }

    // TODO: rearrange headersIds instead of clearing all items
    private static class AdapterDataObserver extends RecyclerView.AdapterDataObserver {

        private final ArrayList<Long> headersIds;

        public AdapterDataObserver(ArrayList<Long> headersIds) {
            this.headersIds = headersIds;
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (headersIds.size() >= positionStart + itemCount)
                headersIds.remove(positionStart);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            headersIds.clear();
        }
    }

}
