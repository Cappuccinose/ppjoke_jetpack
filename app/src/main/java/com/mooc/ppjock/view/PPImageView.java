package com.mooc.ppjock.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mooc.libcommon.utils.PixUtils;
import com.mooc.libcommon.view.ViewHelper;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class PPImageView extends AppCompatImageView {
    public PPImageView(Context context) {
        super(context);
    }

    public PPImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PPImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewHelper.setViewOutline(this,attrs,defStyleAttr,0);
    }

    @BindingAdapter(value = {"image_url","isCircle","radius"},requireAll = false)
    public static void setImageUrl(PPImageView view,String imageUrl,boolean isCircle,int radius){
        RequestBuilder<Drawable> builder = Glide.with(view).load(imageUrl);
        if (isCircle){
            builder.transform(new CircleCrop());
        }else if(radius > 0){
            builder.transform(new RoundedCornersTransformation(PixUtils.dp2px(radius),0));
        }

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if (layoutParams != null && layoutParams.width >0 && layoutParams.height > 0){
            builder.override(layoutParams.width,layoutParams.height);
        }
        builder.into(view);
    }

    public void bindData(int widthPx,int heightPx,int marginLeft,String imageUrl){
        bindData(widthPx,heightPx,marginLeft,PixUtils.getScreenWidth(),PixUtils.getScreenWidth(),imageUrl);
    }

    private void bindData(int widthPx, int heightPx, int marginLeft, int maxWidth, int maxHeight, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)){
            setVisibility(GONE);
            return;
        }else{
            setVisibility(VISIBLE);
        }

        if (widthPx <= 0 || heightPx <=0){
            Glide.with(this).load(imageUrl).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    int intrinsicWidth = resource.getIntrinsicWidth();
                    int intrinsicHeight = resource.getIntrinsicHeight();

                    setSize(intrinsicWidth,intrinsicHeight,marginLeft,maxWidth,maxHeight);
                    //TODO
                }
            });
        }
    }

    private void setSize(int width, int height, int marginLeft, int maxWidth, int maxHeight) {
        int finalWidth, finalHeight;
        if (width > height) {
            finalWidth = maxWidth;
            finalHeight = (int) (height / (width * 1.0f / finalWidth));
        } else {
            finalHeight = maxHeight;
            finalWidth = (int) (width / (height * 1.0f / finalHeight));
        }

        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = finalWidth;
        params.height = finalHeight;
        if (params instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) params).leftMargin = height > width ? PixUtils.dp2px(marginLeft) : 0;
        } else if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).leftMargin = height > width ? PixUtils.dp2px(marginLeft) : 0;
        }
        setLayoutParams(params);
    }



}