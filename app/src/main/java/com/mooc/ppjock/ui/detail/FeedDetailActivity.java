package com.mooc.ppjock.ui.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mooc.ppjock.model.Feed;

import java.io.Serializable;

public class FeedDetailActivity extends AppCompatActivity {
    private static final String KEY_FEED = "key_feed";
    public static final String KEY_CATEGORY = "key_category";
    private ViewHandler viewHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Feed feed = (Feed) getIntent().getSerializableExtra(KEY_FEED);
        if (feed  == null){
            finish();
            return;
        }
        if (feed.itemType == Feed.TYPE_IMAGE_TEXT){
            viewHandler = new ImageViewHandler(this);
        }else {
            viewHandler = new VideoViewHandler(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
