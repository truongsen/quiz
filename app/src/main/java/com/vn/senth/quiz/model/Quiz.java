package com.vn.senth.quiz.model;

/**
 * Created by SenTH on 5/24/2018.
 */

public class Quiz {
    private int number;
    private String type;
    private String answer;
    private String image;
    private String question;
    private int answerCorrect;

    public int getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(int answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public Quiz(String image, String question, String answer, int answerCorrect) {
//        this.number = number;
        this.answer = answer;
        this.image = image;
        this.question = question;
        this.answerCorrect = answerCorrect;
    }

    public boolean checkAnswer(int answerCorrect) {
        if (this.answerCorrect == answerCorrect)
            return true;
        return false;
    }
}
