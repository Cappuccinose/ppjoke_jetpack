package com.mooc.libcommon.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

public class WindowInsetsNavHostFragment extends NavHostFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        WindowInsetsFrameLayout windowInsetsFrameLayout = new WindowInsetsFrameLayout(inflater.getContext());
        windowInsetsFrameLayout.setId(getId());
        return windowInsetsFrameLayout;
    }
}
