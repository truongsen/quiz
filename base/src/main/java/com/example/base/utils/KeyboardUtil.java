package com.example.base.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public final class KeyboardUtil {

    public static void requestKeyboard(final Activity activity, int editViewId) {
        requestKeyboard(activity.findViewById(editViewId));
    }

    public static void requestKeyboard(final View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (keyboard != null) {
                    keyboard.showSoftInput(view, 0);
                }
            }
        }, 200);
    }

    public static void requestKeyboardForce(final View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public static void hideKeyboard(final Activity activity, int viewId) {
        final View view = activity.findViewById(viewId);
        hideKeyboard(view);
    }

    public static void hideKeyboard(View view) {
        if (view == null) {
            return;
        }
        InputMethodManager im = (InputMethodManager)
                view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            View f = activity.getCurrentFocus();
            if (imm != null && null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom(f.getClass())) {
                imm.hideSoftInputFromWindow(f.getWindowToken(), 0);
            } else {
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        } catch (Exception ignored) {

        }
    }
}
