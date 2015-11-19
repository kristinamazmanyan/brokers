package com.brokerexam.domain.exam;

public class ExamTime {
/**
 *
 */
private static final long serialVersionUID = -2227320979042905680L;

    private int id;
    private int time;
    private int q_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getQ_count() {
        return q_count;
    }

    public void setQ_count(int q_count) {
        this.q_count = q_count;
    }
}

