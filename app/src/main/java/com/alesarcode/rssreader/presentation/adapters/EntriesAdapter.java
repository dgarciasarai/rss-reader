package com.alesarcode.rssreader.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alesarcode.rssreader.R;
import com.alesarcode.rssreader.presentation.model.EntryModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for entries RecyclerView.
 *
 * @author Sarai Díaz García
 * @version %I%
 */
public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.ViewHolder> {

    private List<EntryModel> entriesCollection;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private OnEntryClickListener onEntryClickListener;

    @Inject
    public EntriesAdapter(Context context) {
        mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.entriesCollection = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.layoutInflater
                .inflate(R.layout.row_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EntryModel entry = entriesCollection.get(position);
        holder.title.setText(entry.getTitle());

        if (entry.getImageUrl() == null) {
            Glide.clear(holder.image);
            holder.image.setImageDrawable(null);
        } else {
            Glide.with(mContext)
                    .load(entry.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EntriesAdapter.this.onEntryClickListener != null) {
                    EntriesAdapter.this.onEntryClickListener.onEntryClicked(entry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return entriesCollection == null ? 0 : entriesCollection.size();
    }

    public void setEntriesCollection(Collection<EntryModel> models) {
        this.entriesCollection = (List<EntryModel>) models;
        this.notifyDataSetChanged();
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        this.onEntryClickListener = onEntryClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
