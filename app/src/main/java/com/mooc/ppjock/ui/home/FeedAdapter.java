package com.mooc.ppjock.ui.home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mooc.libcommon.extention.AbsPagedListAdapter;
import com.mooc.ppjock.model.Feed;

public class FeedAdapter extends AbsPagedListAdapter<Feed,FeedAdapter.ViewHolder> {

    protected FeedAdapter(@NonNull DiffUtil.ItemCallback<Feed> diffCallback) {
        super(diffCallback);
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
