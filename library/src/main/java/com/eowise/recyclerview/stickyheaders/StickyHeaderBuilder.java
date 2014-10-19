package com.eowise.recyclerview.stickyheaders;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aurel on 16/10/14.
 */
public class StickyHeaderBuilder {

    private Boolean isAnimationEnabled;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private StickyHeadersAdapter headersAdapter;
    private HeaderPosition headerPosition;

    public StickyHeaderBuilder() {
        this.isAnimationEnabled = false;
    }

    public StickyHeaderBuilder setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public StickyHeaderBuilder setStickyHeadersAdapter(StickyHeadersAdapter adapter) {
        return setStickyHeadersAdapter(adapter, HeaderPosition.TOP);
    }

    public StickyHeaderBuilder setStickyHeadersAdapter(StickyHeadersAdapter adapter, HeaderPosition headerPosition) {
        this.headersAdapter = adapter;
        this.headerPosition = headerPosition;
        return this;
    }

    public StickyHeaderBuilder setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public StickyHeaderBuilder enableAnimations() {
        this.isAnimationEnabled = true;
        return this;
    }

    public StickyHeadersItemDecoration build() {

        // TODO: Throw if adapter doesn't have stables ids

        StickyHeadersItemDecoration decoration =  new StickyHeadersItemDecoration(headersAdapter, recyclerView, headerPosition);


        if (isAnimationEnabled) {
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {

                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    ViewCompat.postOnAnimationDelayed(recyclerView, new InvalidateAnimationRunnable(recyclerView), 50);
                }

                @Override
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    ViewCompat.postOnAnimationDelayed(recyclerView, new InvalidateAnimationRunnable(recyclerView), 50);
                }
            });
        }


        decoration.registerAdapterDataObserver(adapter);

        return decoration;
    }
}
