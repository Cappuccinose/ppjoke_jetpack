package com.mooc.ppjock.ui.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.Observer;

import com.mooc.libcommon.global.AppGlobals;
import com.mooc.libcommon.utils.FileUtils;
import com.mooc.libcommon.utils.PixUtils;
import com.mooc.libcommon.view.ViewHelper;
import com.mooc.libnetwork.ApiResponse;
import com.mooc.libnetwork.ApiService;
import com.mooc.libnetwork.JsonCallback;
import com.mooc.ppjock.R;
import com.mooc.ppjock.databinding.LayoutCommentDialogBinding;
import com.mooc.ppjock.model.Comment;
import com.mooc.libcommon.dialog.LoadingDiaLog;
import com.mooc.ppjock.ui.publish.CaptureActivity;


import java.util.concurrent.atomic.AtomicInteger;

public class CommentDialog extends AppCompatDialogFragment implements View.OnClickListener {
    private LayoutCommentDialogBinding mBinding;
    private long itemId;
    private commentAddListener mListener;
    private static final String KEY_ITEM_ID = "key_item_id";
    private String filePath;
    private int width, height;
    private boolean isVideo;
    private String coverUrl;
    private String fileUrl;
    private LoadingDiaLog loadingDialog;

    public static CommentDialog newInstance(long itemId) {

        Bundle args = new Bundle();
        args.putLong(KEY_ITEM_ID, itemId);
        CommentDialog fragment = new CommentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.setWindowAnimations(0);

        mBinding = LayoutCommentDialogBinding.inflate(inflater, ((ViewGroup) window.findViewById(android.R.id.content)), false);
        mBinding.commentVideo.setOnClickListener(this);
        mBinding.commentDelete.setOnClickListener(this);
        mBinding.commentSend.setOnClickListener(this);

        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        this.itemId = getArguments().getLong(KEY_ITEM_ID);

        ViewHelper.setViewOutline(mBinding.getRoot(), PixUtils.dp2px(10), ViewHelper.RADIUS_TOP);

        mBinding.getRoot().post(() -> showSoftInputMethod());

        dismissWhenPressBack();
        return mBinding.getRoot();
    }

    private void dismissWhenPressBack() {
        mBinding.inputView.setOnBackKeyEventListener(() -> {
            mBinding.inputView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 200);
            return true;
        });
    }


    private void showSoftInputMethod() {
        mBinding.inputView.setFocusable(true);
        mBinding.inputView.setFocusableInTouchMode(true);
        //请求获得焦点
        mBinding.inputView.requestFocus();
        InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(mBinding.inputView, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_send:
                publishComment();
                break;
            case R.id.comment_video:
                break;
            case R.id.comment_delete:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CaptureActivity.REQ_CAPTURE && resultCode == Activity.RESULT_OK) {
            filePath = data.getStringExtra(CaptureActivity.RESULT_FILE_PATH);
            width = data.getIntExtra(CaptureActivity.RESULT_FILE_WIDTH, 0);
            height = data.getIntExtra(CaptureActivity.RESULT_FILE_HEIGHT, 0);
            isVideo = data.getBooleanExtra(CaptureActivity.RESULT_FILE_TYPE, false);

            if (!TextUtils.isEmpty(filePath)) {
                mBinding.commentExtLayout.setVisibility(View.VISIBLE);
                mBinding.commentCover.setImageUrl(filePath);
                if (isVideo) {
                    mBinding.commentIconVideo.setVisibility(View.VISIBLE);
                }
            }

            mBinding.commentVideo.setEnabled(false);
            mBinding.commentVideo.setAlpha(80);
        }
    }

    private void publishComment() {

        if (TextUtils.isEmpty(mBinding.inputView.getText())) {
            return;
        }

        if (isVideo && !TextUtils.isEmpty(filePath)) {
            FileUtils.generateVideoCover(filePath).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String coverPath) {
                    uploadFile(coverPath, filePath);
                }
            });
        } else if (!TextUtils.isEmpty(filePath)) {
            uploadFile(null, filePath);
        } else {
            publish();
        }
    }

    @SuppressLint("RestrictedApi")
    private void uploadFile(String coverPath, String filePath) {
        //AtomicInteger, CountDownLatch, CyclicBarrier
        showLoadingDialog();
        AtomicInteger count = new AtomicInteger(1);
        if (!TextUtils.isEmpty(coverPath)) {
            count.set(2);
            ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    private void publish() {

    }

    private void showLoadingDialog() {

    }

    private void dismissLoadingDialog() {

    }

    private void onCommentSuccess(Comment body) {

    }

    @Override
    public void dismiss() {
        super.dismiss();
        dismissLoadingDialog();
        filePath = null;
        fileUrl = null;
        coverUrl = null;
        isVideo = false;
        width = 0;
        height = 0;
    }

    @SuppressLint("RestrictedApi")
    private void showToast(String s) {
        //showToast几个可能会出现在异步线程调用
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(AppGlobals.getApplication(), s, Toast.LENGTH_SHORT).show();
        } else {
            ArchTaskExecutor.getMainThreadExecutor().execute(() -> Toast.makeText(AppGlobals.getApplication(), s, Toast.LENGTH_SHORT).show());
        }
    }

    public interface commentAddListener {
        void onAddComment(Comment comment);
    }

    public void setCommentAddListener(commentAddListener listener) {

        mListener = listener;
    }
}
