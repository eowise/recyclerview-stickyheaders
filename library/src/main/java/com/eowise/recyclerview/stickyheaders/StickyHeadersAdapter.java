package com.eowise.recyclerview.stickyheaders;

import android.view.ViewGroup;

public interface StickyHeadersAdapter<ItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder, HeaderViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder> {

    HeaderViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(HeaderViewHolder headerViewHolder, ItemViewHolder itemViewHolder, int position);

    long getHeaderId(ItemViewHolder viewHolder, int position);
}
