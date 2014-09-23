package com.eowise.recyclerview.stickyheaders.samples.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.StickyHeadersAdapter;
import com.eowise.recyclerview.stickyheaders.samples.R;

/**
 * Created by aurel on 23/09/14.
 */
public class PersonInitialAdapter implements StickyHeadersAdapter<PersonAdapter.ViewHolder, PersonInitialAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_header, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder headerViewHolder, PersonAdapter.ViewHolder itemViewHolder, int position) {
        headerViewHolder.letter.setText(itemViewHolder.label.getText().subSequence(0, 1));
    }

    @Override
    public long getHeaderId(PersonAdapter.ViewHolder viewHolder, int position) {
        return viewHolder.label.getText().charAt(0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView letter;

        public ViewHolder(View itemView) {
            super(itemView);
            letter = (TextView) itemView.findViewById(R.id.letter);
        }
    }
}
