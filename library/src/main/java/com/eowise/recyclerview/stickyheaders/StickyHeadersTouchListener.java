package com.eowise.recyclerview.stickyheaders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by aurel on 08/11/14.
 */
public class StickyHeadersTouchListener implements RecyclerView.OnItemTouchListener {

    private final HeaderStore headerStore;
    private final GestureDetector gestureDetector;
    private OnHeaderClickListener listener;

    public StickyHeadersTouchListener(RecyclerView parent, HeaderStore headerStore) {
        this.headerStore = headerStore;
        this.gestureDetector = new GestureDetector(parent.getContext(), new SingleTapDetector(parent));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView parent, MotionEvent e) {
        return listener != null && gestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView parent, MotionEvent e) {

    }

    public void setListener(OnHeaderClickListener listener) {
        this.listener = listener;
    }

    private class SingleTapDetector extends GestureDetector.SimpleOnGestureListener {

        private final RecyclerView parent;

        public SingleTapDetector(RecyclerView parent) {
            this.parent = parent;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return findItemHolderUnder(e.getX(), e.getY()) != null;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            RecyclerView.ViewHolder holder = findItemHolderUnder(e.getX(), e.getY());
            if (holder != null) {
                View headerView = headerStore.getHeaderViewByItem(holder);
                long headerId = headerStore.getHeaderId(holder.getPosition());
                listener.onHeaderClick(headerView, headerId);
                return true;
            }

            return false;
        }

        private RecyclerView.ViewHolder findItemHolderUnder(float x, float y) {

            for (int i = parent.getChildCount() - 1; i > 0; i--) {
                View item = parent.getChildAt(i);
                RecyclerView.ViewHolder holder = parent.getChildViewHolder(item);

                if (holder != null && headerStore.isHeader(holder)) {
                    if (y < item.getTop() && item.getTop() - headerStore.getHeaderHeight(holder) < y) {
                        return holder;
                    }
                }
            }

            View firstItem = parent.getChildAt(0);

            if (firstItem != null) {
                RecyclerView.ViewHolder holder = parent.getChildViewHolder(firstItem);

                if (holder != null && y < headerStore.getHeaderHeight(holder)) {
                    if (holder.getPosition() == 0 || headerStore.isSticky()) {
                        return holder;
                    }
                }
            }

            return null;
        }
    }
}
