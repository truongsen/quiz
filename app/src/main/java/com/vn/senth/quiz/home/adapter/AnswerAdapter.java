package com.vn.senth.quiz.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vn.core.base.BaseAdapter;
import com.vn.core.base.BaseHolder;
import com.vn.core.base.OnItemClickListener;
import com.vn.senth.quiz.R;
import com.vn.senth.quiz.home.holder.AnswerHolder;

import java.util.List;

/**
 * Created by SenTH on 5/24/2018.
 */

public class AnswerAdapter extends BaseAdapter<String> {
    private AnswerHolder answerChoose;
    private OnItemClickListener onItemClickListener;

    public AnswerAdapter(Context context, List<String> listItem) {
        super(context, listItem);
    }

    @Override
    public BaseHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflate.inflate(R.layout.item_answer, parent, false);
        return new AnswerHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onBindHolder(BaseHolder holder, int position) {
        String answer = mListItems.get(position);
        AnswerHolder viewHolder = (AnswerHolder) holder;
        viewHolder.setData(answer);
        viewHolder.itemView.setOnClickListener(view -> {
            if (answerChoose != null)
                answerChoose.setBackground(false);
            answerChoose = viewHolder;
            answerChoose.setBackground(true);
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position);
        });
    }
}
