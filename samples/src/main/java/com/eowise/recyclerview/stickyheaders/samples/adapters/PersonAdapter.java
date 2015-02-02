package com.eowise.recyclerview.stickyheaders.samples.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.eowise.recyclerview.stickyheaders.samples.R;
import com.eowise.recyclerview.stickyheaders.samples.data.PersonDataProvider;
import com.eowise.recyclerview.stickyheaders.samples.listeners.OnEditListener;
import com.eowise.recyclerview.stickyheaders.samples.listeners.OnRemoveListener;

import java.util.List;

/**
 * Created by aurel on 22/09/14.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnRemoveListener, OnEditListener {

    private List<String> items;
    private PersonDataProvider personDataProvider;
    private Context mContext;

    public PersonAdapter(Context context, PersonDataProvider personDataProvider) {
        this.mContext = context;
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

    @Override
    public void onEdit(final int position) {
        final EditText edit = new EditText(mContext);
        edit.setTextColor(Color.BLACK);
        edit.setText(personDataProvider.getItems().get(position));
        new AlertDialog.Builder(mContext).setTitle(R.string.edit).setView(edit).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = edit.getText().toString();
                personDataProvider.update(position, name);
                notifyItemChanged(position);
            }
        }).create().show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView label;
        private OnRemoveListener removeListener;
        private OnEditListener editListener;

        public ViewHolder(View itemView, PersonAdapter personAdapter) {
            super(itemView);
            this.label = (TextView) itemView.findViewById(R.id.name);
            this.removeListener = personAdapter;
            this.editListener = personAdapter;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            removeListener.onRemove(getPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            editListener.onEdit(getPosition());
            return true;
        }
    }


}
