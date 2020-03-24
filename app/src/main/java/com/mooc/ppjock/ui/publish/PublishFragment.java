package com.mooc.ppjock.ui.publish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mooc.libnavannotation.FragmentDestination;
import com.mooc.ppjock.R;

@FragmentDestination(pageUrl = "main/tabs/publish",asStarter = false)
public class PublishFragment extends Fragment {

    private PublishViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(PublishViewModel.class);
        View root = inflater.inflate(R.layout.fragment_publish, container, false);
        final TextView textView = root.findViewById(R.id.text_publish);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
