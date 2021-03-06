package com.vn.core.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vn.core.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SenTH on 5/24/2018.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    protected Context mContext;
    protected List<T> mListItems = new ArrayList<>();
    protected LayoutInflater mLayoutInflate;
    protected OnItemClickListener<T> mItemClickListener;

    public BaseAdapter(Context context, List<T> listItem) {
        this.mContext = context;
        mLayoutInflate = LayoutInflater.from(context);
        if (!CollectionUtils.isListEmpty(listItem))
            this.mListItems.addAll(listItem);
//            this.mListItems = listItem;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return mListItems == null ? 0 : mListItems.size();
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.setData(mListItems.get(position));
        holder.itemView.setOnClickListener(mItemClickListener);
        onBindHolder(holder, position);
    }

    public abstract BaseHolder onCreateHolder(ViewGroup parent, int viewType);

    public abstract void onBindHolder(BaseHolder holder, int position);

    public void resetData() {
        mListItems.clear();
        notifyDataSetChanged();
    }

    public void addData(T data) {
        mListItems.add(data);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addData(List<T> listData) {
        int sizeBefore = getItemCount();
        mListItems.addAll(listData);
        notifyItemRangeInserted(sizeBefore, getItemCount() - 1);
    }

    public void addData(int index, List<T> listData) {
        if (CollectionUtils.isListEmpty(listData))
            return;
        mListItems.addAll(index, listData);
        notifyItemRangeInserted(index, listData.size());
    }

    public void removeData(int position) {
        if (position >= getItemCount())
            return;
        mListItems.remove(position);
        notifyDataSetChanged();
    }

    protected T getItem(int pos) {
        if (pos >= getItemCount())
            return null;
        return mListItems.get(pos);
    }

    public void setItemClickListener(OnItemClickListener<T> listener) {
        mItemClickListener = listener;
    }
}
