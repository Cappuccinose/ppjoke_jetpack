package com.mooc.ppjock.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
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
}
