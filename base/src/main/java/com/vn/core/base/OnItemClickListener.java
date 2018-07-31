package com.vn.core.base;

import android.view.View;

/**
 * Created by SenTH on 5/24/2018.
 */

public interface OnItemClickListener<T> extends View.OnClickListener {
    void onItemClick(int position, T data);
}
