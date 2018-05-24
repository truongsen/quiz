package com.vn.senth.quiz.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by SenTH on 5/24/2018.
 */

public class BaseHolder<T> extends RecyclerView.ViewHolder {
    protected Context mContext;
    protected T mData;

    public BaseHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    protected void setData(T data) {
        this.mData = data;
        if (data == null)
            return;
    }

}
