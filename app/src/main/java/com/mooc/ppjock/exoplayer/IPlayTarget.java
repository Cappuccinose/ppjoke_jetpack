package com.mooc.ppjock.exoplayer;

import android.view.ViewGroup;

public interface IPlayTarget {

    ViewGroup getOwner();

    //活跃状态视频可播放
    void onActive();

    //非活跃状态，暂停
    void inActive();

    boolean isPlaying();

}
