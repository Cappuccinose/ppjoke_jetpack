package com.mooc.libcommon.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBar {

    /**
     * 6.0级以上的沉浸式布局
     *
     * @param activity
     */
    public static void fitSystemBar(Activity activity){
        if (Build.VERSION.SDK_INT <Build.VERSION_CODES.M){
            return;
        }

        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }
    /**
     * 6.0及以上的状态栏色调
     *
     * @param activity
     * @param light    true:白底黑字,false:黑底白字
     */
    public static void lightStatusBar(Activity activity,boolean light){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }

        Window window = activity.getWindow();
        View decorView = window.getDecorView();

        int systemUiVisibility = decorView.getSystemUiVisibility();

        if (light){
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//按位或后赋值
        }else{
            systemUiVisibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//按位与后赋值
        }
    }

}
