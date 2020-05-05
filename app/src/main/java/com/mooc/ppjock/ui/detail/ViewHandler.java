package com.mooc.ppjock.ui.detail;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.mooc.ppjock.databinding.LayoutFeedAuthorBindingImpl;
import com.mooc.ppjock.model.Feed;

public class ViewHandler {

    private final FragmentActivity mActivity;
    private final FeedDetailViewModel mDetailViewModel;
    private Feed mFeed;

    public ViewHandler(FragmentActivity activity) {
        mActivity = activity;
        mDetailViewModel = ViewModelProviders.of(activity).get(FeedDetailViewModel.class);
    }

    public void bindInitData(Feed feed) {

    }
    
    private void showCommentDialog(){
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onBackPressed() {
    }
}
