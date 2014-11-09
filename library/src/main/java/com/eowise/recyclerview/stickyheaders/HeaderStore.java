package com.eowise.recyclerview.stickyheaders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewHelper;
import android.view.View;

import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aurel on 08/11/14.
 */
public class HeaderStore {

    private final RecyclerView parent;
    private final StickyHeadersAdapter adapter;
    private final HashMap<Long, View> headersViewByHeadersIds;
    private final HashMap<Long, Boolean> wasHeaderByItemId;
    private final ArrayList<Boolean> isHeaderByItemPosition;
    private final HashMap<Long, Integer> headersHeightsByItemsIds;
    private boolean isSticky;


    public HeaderStore(RecyclerView parent, StickyHeadersAdapter adapter, boolean isSticky) {
        this.parent = parent;
        this.adapter = adapter;
        this.isSticky = isSticky;
        this.headersViewByHeadersIds = new HashMap<Long, View>();
        this.wasHeaderByItemId = new HashMap<Long, Boolean>();
        this.isHeaderByItemPosition = new ArrayList<Boolean>();
        this.headersHeightsByItemsIds = new HashMap<Long, Integer>();
    }

    public View getHeaderViewByItem(RecyclerView.ViewHolder itemHolder) {
        int itemPosition = RecyclerViewHelper.convertPreLayoutPositionToPostLayout(parent, itemHolder.getPosition());

        if (itemPosition == -1)
            return null;

        long headerId = adapter.getHeaderId(itemPosition);

        if (!headersViewByHeadersIds.containsKey(headerId)) {
            RecyclerView.ViewHolder headerViewHolder = adapter.onCreateViewHolder(parent);

            adapter.onBindViewHolder(headerViewHolder, itemPosition);
            layoutHeader(headerViewHolder.itemView);

            headersViewByHeadersIds.put(headerId, headerViewHolder.itemView);
        }

        return headersViewByHeadersIds.get(headerId);

    }

    public long getHeaderId(int itemPosition) {
        return adapter.getHeaderId(itemPosition);
    }

    public int getHeaderHeight(RecyclerView.ViewHolder itemHolder) {

        if (!headersHeightsByItemsIds.containsKey(itemHolder.getItemId())) {
            View header = getHeaderViewByItem(itemHolder);
            headersHeightsByItemsIds.put(itemHolder.getItemId(), header.getMeasuredHeight());
        }

        return headersHeightsByItemsIds.get(itemHolder.getItemId());
    }

    public boolean isHeader(RecyclerView.ViewHolder itemHolder) {
        int itemPosition = RecyclerViewHelper.convertPreLayoutPositionToPostLayout(parent, itemHolder.getPosition());
        if (isHeaderByItemPosition.size() <= itemPosition) {
            isHeaderByItemPosition.add(itemPosition, itemPosition == 0 || adapter.getHeaderId(itemPosition) != adapter.getHeaderId(itemPosition - 1));
        }
        else if (isHeaderByItemPosition.get(itemPosition) == null) {
            isHeaderByItemPosition.set(itemPosition, itemPosition == 0 || adapter.getHeaderId(itemPosition) != adapter.getHeaderId(itemPosition - 1));
        }

        return isHeaderByItemPosition.get(itemPosition);
    }

    public boolean wasHeader(RecyclerView.ViewHolder itemHolder) {
        if (!wasHeaderByItemId.containsKey(itemHolder.getItemId())) {
            int itemPosition = RecyclerViewHelper.convertPreLayoutPositionToPostLayout(parent, itemHolder.getPosition());

            if (itemPosition == -1) { // we are deleting the last item
                return false;
            }

            wasHeaderByItemId.put(itemHolder.getItemId(), itemPosition == 0 || adapter.getHeaderId(itemPosition) != adapter.getHeaderId(itemPosition - 1));
        }
        return wasHeaderByItemId.get(itemHolder.getItemId());
    }

    public boolean isSticky() {
        return isSticky;
    }

    public void onItemRangeRemoved(int positionStart, int itemCount) {

        if (isHeaderByItemPosition.size() > positionStart + itemCount) {

            for (int i = 0; i < itemCount; i++) {
                RecyclerView.ViewHolder holder = parent.findViewHolderForPosition(positionStart + i);
                if (holder != null) {
                    wasHeaderByItemId.put(holder.getItemId(), isHeaderByItemPosition.get(positionStart + i));
                }
            }

            isHeaderByItemPosition.set(positionStart + itemCount, null);

            for (int i = 0; i < itemCount; i++) {
                isHeaderByItemPosition.remove(positionStart);
            }
        }
    }

    public void onItemRangeInserted(int positionStart, int itemCount) {
        if (isHeaderByItemPosition.size() > positionStart) {
            for (int i = 0; i < itemCount; i++) {
                isHeaderByItemPosition.add(positionStart, null);
            }
        }


        if (isHeaderByItemPosition.size() > positionStart + itemCount) {
            isHeaderByItemPosition.set(positionStart + itemCount, null);
        }
    }

    public void clear() {
        headersViewByHeadersIds.clear();
        isHeaderByItemPosition.clear();
        wasHeaderByItemId.clear();
    }

    private void layoutHeader(View header) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        header.measure(widthSpec, heightSpec);
        header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
    }
}
