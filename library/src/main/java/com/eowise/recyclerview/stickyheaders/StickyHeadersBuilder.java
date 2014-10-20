package com.eowise.recyclerview.stickyheaders;

import android.support.v7.widget.RecyclerView;

/**
 * Created by aurel on 16/10/14.
 */
public class StickyHeadersBuilder {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private StickyHeadersAdapter headersAdapter;
    private HeaderPosition headerPosition;

    public StickyHeadersBuilder() {
    }

    public StickyHeadersBuilder setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public StickyHeadersBuilder setStickyHeadersAdapter(StickyHeadersAdapter adapter) {
        return setStickyHeadersAdapter(adapter, HeaderPosition.TOP);
    }

    public StickyHeadersBuilder setStickyHeadersAdapter(StickyHeadersAdapter adapter, HeaderPosition headerPosition) {
        this.headersAdapter = adapter;
        this.headerPosition = headerPosition;
        return this;
    }

    public StickyHeadersBuilder setAdapter(RecyclerView.Adapter adapter) {
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
