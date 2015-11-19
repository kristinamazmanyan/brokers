package com.brokerexam.domain.quiz;

import java.io.Serializable;

public class QuizFinalResult implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 8355190890762394885L;
    private int id;
    private int emId;
    private int examId;
    private int percent;
    private String percentString;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPercent() {
        return percent;
    }
    public void setPercent(int percent) {
        this.percent = percent;
    }
    public String getPercentString() {
        StringBuffer buf = new StringBuffer();
        buf.append(percent);
        buf.append('%');
        return buf.toString();
    }
    public void setPercentString(String percentString) {
        this.percentString = percentString;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}


