package com.eowise.recyclerview.stickyheaders.samples.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.samples.R;
import com.eowise.recyclerview.stickyheaders.samples.data.PersonDataProvider;
import com.eowise.recyclerview.stickyheaders.samples.listeners.OnRemoveListener;

import java.util.List;

/**
 * Created by aurel on 22/09/14.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnRemoveListener {

    private List<String> items;
    private PersonDataProvider personDataProvider;

    public PersonAdapter(PersonDataProvider personDataProvider) {
        this.personDataProvider = personDataProvider;
        this.items = personDataProvider.getItems();

        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).hashCode();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.label.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onRemove(int position) {
        personDataProvider.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView label;
        private OnRemoveListener listener;

        public ViewHolder(View itemView, OnRemoveListener listener) {
            super(itemView);
            this.label = (TextView) itemView.findViewById(R.id.name);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            listener.onRemove(getPosition());
        }
    }


}
