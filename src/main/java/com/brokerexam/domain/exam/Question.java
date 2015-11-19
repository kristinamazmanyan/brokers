package com.brokerexam.domain.exam;

import java.io.Serializable;

public class Question implements Serializable {

    private static final long serialVersionUID = 7199771021318041242L;

    private int number;
    private int id;
    private int examId;
     private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int rightAnswer;
    private int userAnswer;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
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
    public int getRightAnswer() {
        return rightAnswer;
    }
    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }
    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }


    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}

