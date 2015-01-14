package com.eowise.recyclerview.stickyheaders;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by aurel on 22/09/14.
 */
public class StickyHeadersItemDecoration extends RecyclerView.ItemDecoration {

    private final HeaderStore headerStore;
    private final AdapterDataObserver adapterDataObserver;
    private boolean overlay;
    private DrawOrder drawOrder;

    public StickyHeadersItemDecoration(HeaderStore headerStore) {
        this(headerStore, false);
    }

    public StickyHeadersItemDecoration(HeaderStore headerStore, boolean overlay) {
        this(headerStore, overlay, DrawOrder.OverItems);
    }

    public StickyHeadersItemDecoration(HeaderStore headerStore, boolean overlay, DrawOrder drawOrder) {
        this.overlay = overlay;
        this.drawOrder = drawOrder;
        this.headerStore = headerStore;
        this.adapterDataObserver = new AdapterDataObserver();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (drawOrder == DrawOrder.UnderItems) {
            drawHeaders(c, parent, state);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (drawOrder == DrawOrder.OverItems) {
            drawHeaders(c, parent, state);
        }
    }

    private void drawHeaders(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        final RecyclerView.LayoutManager lm = parent.getLayoutManager();
        Float lastY = null;

        for (int i = childCount - 1; i >= 0; i--) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)child.getLayoutParams();
            RecyclerView.ViewHolder holder = parent.getChildViewHolder(child);

            if (!lp.isItemRemoved() && !lp.isViewInvalid()) {

                float translationY = ViewCompat.getTranslationY(child);

                if ((i == 0 && headerStore.isSticky()) || headerStore.isHeader(holder)) {

                    View header = headerStore.getHeaderViewByItem(holder);

                    if (header.getVisibility() == View.VISIBLE) {

                        int headerHeight = headerStore.getHeaderHeight(holder);
                        float y = getHeaderY(child, lm) + translationY;

                        if (headerStore.isSticky() && lastY != null && lastY < y + headerHeight) {
                            y = lastY - headerHeight;
                        }

                        c.save();
                        c.translate(0, y);
                        header.draw(c);
                        c.restore();

                        lastY = y;
                    }
                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)view.getLayoutParams();
        RecyclerView.ViewHolder holder = parent.getChildViewHolder(view);
        boolean isHeader = lp.isItemRemoved() ? headerStore.wasHeader(holder) : headerStore.isHeader(holder);


        if (overlay || !isHeader) {
            outRect.set(0, 0, 0, 0);
        }
        else {
            //TODO: Handle layout direction
            outRect.set(0, headerStore.getHeaderHeight(holder), 0, 0);
        }
    }

    public void registerAdapterDataObserver(RecyclerView.Adapter adapter) {
        adapter.registerAdapterDataObserver(adapterDataObserver);
    }

    private float getHeaderY(View item, RecyclerView.LayoutManager lm) {
        return  headerStore.isSticky() && lm.getDecoratedTop(item) < 0 ? 0 : lm.getDecoratedTop(item);
    }


    private class AdapterDataObserver extends RecyclerView.AdapterDataObserver {

        public AdapterDataObserver() {
        }
        
        @Override
        public void onChanged() {
            headerStore.clear();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            headerStore.onItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            headerStore.onItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            headerStore.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }

      @Override
      public void onItemRangeChanged(int positionStart, int itemCount) {
            headerStore.onItemRangeChanged(positionStart, itemCount);
      }
    }

}
