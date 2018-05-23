package com.vn.base.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by SenTH on 5/23/2018.
 */

public class ViewUtils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Get device screen width in pixel
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenWidthInPixel(Activity activity) {
        Point size = new Point();
        WindowManager w = activity.getWindowManager();
        w.getDefaultDisplay().getSize(size);
        return size.x;
    }

    /**
     * Get device screen height in pixel
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenHeightInPixel(Activity activity) {
        Point size = new Point();
        WindowManager w = activity.getWindowManager();
        w.getDefaultDisplay().getSize(size);
        return size.y;
    }

    public static void setVisibility(int visibility, View... views) {
        for (View item : views)
            item.setVisibility(visibility);
    }
}
