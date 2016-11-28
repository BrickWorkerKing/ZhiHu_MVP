package com.nn.zhihumvp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author LiuZongRui  16/11/16
 */

public class GlideUtil {

    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(imageView);
    }

}
