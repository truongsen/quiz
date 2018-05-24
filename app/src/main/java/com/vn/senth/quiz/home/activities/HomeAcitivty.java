package com.vn.senth.quiz.home.activities;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vn.base.utils.StringUtils;
import com.vn.senth.quiz.R;
import com.vn.senth.quiz.base.BaseActivity;
import com.vn.senth.quiz.base.OnItemClickListener;
import com.vn.senth.quiz.data.QuizManager;
import com.vn.senth.quiz.home.adapter.AnswerAdapter;
import com.vn.senth.quiz.model.Quiz;
import com.vn.senth.quiz.utils.QuizConstant;
import com.vn.senth.quiz.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeAcitivty extends BaseActivity implements OnItemClickListener {
    private int quizNumber = 0;
    @BindView(R.id.ivQuestion)
    ImageView ivQuestion;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.rvAnswer)
    RecyclerView rvAnswer;
    private AnswerAdapter adapter;
    private int answerChoose = -1;
    private Quiz currentQuiz;

    public void resetQuestion(Quiz quiz) {
        if (quiz == null)
            Toast.makeText(this, "Congratulation", Toast.LENGTH_SHORT).show();
        answerChoose = -1;
        tvQuestion.setText(quiz.getQuestion());
//        int drawableResourceId = this.getResources().getIdentifier(quiz.getImage(), "drawable", this.getPackageName());
//        ivQuestion.setImageResource(drawableResourceId);
        Glide.with(this).load(quiz.getImage()).into(ivQuestion);
        adapter.resetData();
        adapter.addData(StringUtils.getListBySeparate(quiz.getAnswer(), QuizConstant.QUESTION_SEPARATE_CHARACTER));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        adapter = new AnswerAdapter(this, null);
        adapter.setOnItemClickListener(this);
        rvAnswer.setAdapter(adapter);
    }

    @Override
    public void initData() {
        currentQuiz = QuizManager.getInstance().getQuiz(quizNumber);
        resetQuestion(currentQuiz);
    }

    @OnClick(R.id.btnCheck)
    public void onCheckQuiz() {
        if (!Utils.isClickable())
            return;
        if (answerChoose == -1) {
            Toast.makeText(this, "You must choose answer", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentQuiz.checkAnswer(answerChoose)) {
            Toast.makeText(this, "Correct answer", Toast.LENGTH_SHORT).show();
            quizNumber++;
            currentQuiz = QuizManager.getInstance().getQuiz(quizNumber);
            resetQuestion(currentQuiz);
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(int position) {
        answerChoose = position;
    }
}
