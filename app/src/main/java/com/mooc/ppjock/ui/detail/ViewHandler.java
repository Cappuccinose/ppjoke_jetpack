package com.mooc.ppjock.ui.detail;

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
}
