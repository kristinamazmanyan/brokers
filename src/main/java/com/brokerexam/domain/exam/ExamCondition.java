package com.brokerexam.domain.exam;

import java.io.Serializable;

public class ExamCondition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2227320979042905680L;

    private int id;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
