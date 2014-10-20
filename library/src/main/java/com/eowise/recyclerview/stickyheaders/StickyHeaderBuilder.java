package com.eowise.recyclerview.stickyheaders;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aurel on 16/10/14.
 */
public class StickyHeaderBuilder {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private StickyHeadersAdapter headersAdapter;
    private HeaderPosition headerPosition;

    public StickyHeaderBuilder() {
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

    public StickyHeadersItemDecoration build() {

        // TODO: Throw if adapter doesn't have stables ids

        StickyHeadersItemDecoration decoration =  new StickyHeadersItemDecoration(headersAdapter, recyclerView, headerPosition);

        decoration.registerAdapterDataObserver(adapter);

        return decoration;
    }
}
