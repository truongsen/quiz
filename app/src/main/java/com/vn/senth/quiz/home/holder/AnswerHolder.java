package com.vn.senth.quiz.home.holder;

import android.view.View;
import android.widget.TextView;

import com.vn.core.base.BaseHolder;
import com.vn.core.utils.StringUtils;
import com.vn.senth.quiz.R;

import butterknife.BindView;

/**
 * Created by SenTH on 5/24/2018.
 */

public class AnswerHolder extends BaseHolder<String> {
    @BindView(R.id.answer)
    TextView content;

    public AnswerHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(String data) {
        super.setData(data);
        setBackground(false);
        if (!StringUtils.isEmpty(data)) {
            content.setText(data);
        }
    }

    public void setBackground(boolean isSelected) {
        itemView.setBackgroundResource(isSelected ? R.drawable.bg_answer_choose : R.drawable.bg_answer);
    }
}
