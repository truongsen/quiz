package com.vn.senth.quiz.data;

import com.vn.senth.quiz.model.Quiz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SenTH on 5/24/2018.
 */

public class QuizManager {
    private static QuizManager mInstance;

    private QuizManager() {

    }

    public static QuizManager getInstance() {
        if (mInstance == null)
            mInstance = new QuizManager();
        return mInstance;
    }

    public Quiz getQuiz(int position) {
        if (position >= listQuiz.size())
            return null;
        return listQuiz.get(position);
    }

    List<Quiz> listQuiz = new ArrayList<Quiz>() {{
        add(new Quiz("https://image.ibb.co/nbB4T8/img1.jpg", "Question 1", "Answer 1;Answer 2;Answer 3;Answer 4", 1));
        add(new Quiz("https://image.ibb.co/cp0bao/img3.jpg", "Question 2", "Answer 1;Answer 2;Answer 3;Answer 4", 0));
        add(new Quiz("https://image.ibb.co/nbB4T8/img1.jpg", "Question 3", "Answer 1;Answer 2;Answer 3;Answer 4", 3));
        add(new Quiz("https://image.ibb.co/cp0bao/img3.jpg", "Question 4", "Answer 1;Answer 2;Answer 3;Answer 4", 2));
        add(new Quiz("https://image.ibb.co/nbB4T8/img1.jpg", "Question 5", "Answer 1;Answer 2;Answer 3;Answer 4", 0));
        add(new Quiz("https://image.ibb.co/bXngao/img2.jpg", "Question 6", "Answer 1;Answer 2;Answer 3;Answer 4", 0));
        add(new Quiz("https://image.ibb.co/nbB4T8/img1.jpg", "Question 7", "Answer 1;Answer 2;Answer 3;Answer 4", 1));
        add(new Quiz("https://image.ibb.co/bXngao/img2.jpg", "Question 8", "Answer 1;Answer 2;Answer 3;Answer 4", 1));
        add(new Quiz("https://image.ibb.co/nbB4T8/img1.jpg", "Question 9", "Answer 1;Answer 2;Answer 3;Answer 4", 3));
        add(new Quiz("https://image.ibb.co/bXngao/img2.jpg", "Question 10", "Answer 1;Answer 2;Answer 3;Answer 4", 3));
    }};
}
