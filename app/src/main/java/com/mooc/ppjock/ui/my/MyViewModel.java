package com.mooc.ppjock.ui.my;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my fragment");
    }

    public MutableLiveData<String> getText() {
        return mText;
    }
}
