package com.mooc.ppjock.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mooc.libcommon.extention.AbsPagedListAdapter;
import com.mooc.ppjock.R;
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
            return R.layout.layout_feed_type_image;
        }else if (feed.itemType == Feed.TYPE_VIDEO){
            return R.layout.layout_feed_type_video;
        }
        return 0;
    }

    @Override
    protected FeedAdapter.ViewHolder onCreateViewHolder2(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new ViewHolder(binding.getRoot(),binding);
    }

    @Override
    protected void onBindViewHolder2(FeedAdapter.ViewHolder holder, int position) {
        Feed feed = getItem(position);
        holder.bindData(feed);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView,ViewDataBinding binding) {
            super(itemView);
        }

        public void bindData(Feed feed) {
        }
    }
}
