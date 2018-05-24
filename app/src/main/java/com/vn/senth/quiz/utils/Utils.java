package com.vn.senth.quiz.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by SenTH on 5/24/2018.
 */

public class Utils {
    private static boolean isClicked = false;
    private static final Handler CLICK_HANDLER = new Handler(
            Looper.getMainLooper());
    private static final long CLICK_DELAY = 1000;

    public static boolean isClickable() {
        synchronized (CLICK_HANDLER) {
            if (!isClicked) {
                isClicked = true;
                CLICK_HANDLER.postDelayed(() -> isClicked = false, CLICK_DELAY);
                return true;
            }
            return false;
        }
    }

}
