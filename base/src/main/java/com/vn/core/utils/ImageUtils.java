package com.vn.core.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by SenTH on 5/31/2018.
 */

public class ImageUtils {
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(StringUtils.removeWhiteSpace(url)).into(imageView);
    }
}
