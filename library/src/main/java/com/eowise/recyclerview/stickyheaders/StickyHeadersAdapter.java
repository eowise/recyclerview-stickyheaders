package com.eowise.recyclerview.stickyheaders;

import android.view.ViewGroup;

public interface StickyHeadersAdapter<ItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder, HeaderViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder> {

    /**
     * Create a header {@link android.support.v7.widget.RecyclerView.ViewHolder ViewHolder} witch encapsulate the header view
     * You can either create a View manually or inflate it from an XML layout file.
     *
     * @param parent the parent {@link android.view.ViewGroup ViewGroup}
     * @return the newly created {@link android.support.v7.widget.RecyclerView.ViewHolder ViewHolder}
     */
    HeaderViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * Binds a header view according to the current item data.
     *
     * @param headerViewHolder the ViewHolder containing the view to bind
     * @param itemViewHolder the current item ViewHolder that can be used to bind the header ViewHolder
     * @param position the current item position
     */
    void onBindViewHolder(HeaderViewHolder headerViewHolder, ItemViewHolder itemViewHolder, int position);

    /**
     * Get the header id associated with the specified item.
     *
     * @param viewHolder the current item ViewHolder
     * @param position the current item position
     * @return the header id for the current item
     */
    long getHeaderId(ItemViewHolder viewHolder, int position);
}
