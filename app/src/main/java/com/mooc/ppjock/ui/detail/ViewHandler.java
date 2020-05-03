package com.mooc.ppjock.ui.detail;

import android.content.Intent;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class ViewHandler {

    private final FragmentActivity mActivity;
    private final FeedDetailViewModel mDetailViewModel;

    public ViewHandler(FragmentActivity activity) {
        mActivity = activity;
        mDetailViewModel = ViewModelProviders.of(activity).get(FeedDetailViewModel.class);
    }

    public void bindInitData() {
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
