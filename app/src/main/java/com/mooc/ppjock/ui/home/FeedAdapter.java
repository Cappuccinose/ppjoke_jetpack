package com.mooc.ppjock.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mooc.libcommon.extention.AbsPagedListAdapter;
import com.mooc.ppjock.model.Feed;

public class FeedAdapter extends AbsPagedListAdapter<Feed,FeedAdapter.ViewHolder> {

    private final String mCategory;
    private Context mContext;
    private final LayoutInflater inflater;

    protected FeedAdapter(Context context, String category) {
        super(new DiffUtil.ItemCallback<Feed>() {
            @Override
            public boolean areItemsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.equals(newItem);
            }
        });

        inflater = LayoutInflater.from(mContext);
        mContext = context;
        mCategory = category;

    }

    @Override
    protected int getItemViewType2(int position) {
        Feed feed = getItem(position);
        if (feed.itemType == Feed.TYPE_IMAGE_TEXT){

        }else if (feed.itemType == Feed.TYPE_VIDEO){

        }
        return super.getItemViewType2(position);
    }

    @Override
    protected FeedAdapter.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindViewHolder2(FeedAdapter.ViewHolder holder, int position) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
