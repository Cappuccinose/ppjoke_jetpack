package com.mooc.libcommon.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mooc.libcommon.R;
import com.mooc.libcommon.utils.PixUtils;
import com.mooc.libcommon.view.ViewHelper;

public class LoadingDiaLog extends AlertDialog {

    private TextView loadingText;


    protected LoadingDiaLog(Context context) {
        super(context);
    }

    protected LoadingDiaLog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDiaLog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setLoadingText(String loadingText){
        if (this.loadingText != null){
            this.loadingText.setText(loadingText);
        }
    }

    @Override
    public void show() {
        super.show();
        setContentView(R.layout.layout_loading_view);
        loadingText = findViewById(R.id.loading_text);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.CENTER;
        attributes.dimAmount = 0.5f;
        //这个背景必须设置，否则 会出现对话框 宽度很宽
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ViewHelper.setViewOutline(findViewById(R.id.loading_layout), PixUtils.dp2px(10),ViewHelper.RADIUS_ALL);
        window.setAttributes(attributes);
    }
}
