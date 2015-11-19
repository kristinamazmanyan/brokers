package com.brokerexam.domain.quiz;

import java.io.Serializable;

public class QuizResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2936388497067918932L;

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int answer;
    private int rightAnswer;

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public int getRightAnswer() {
        return rightAnswer;
    }
    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    public String getAnswerA() {
        return answerA;
    }
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
}

