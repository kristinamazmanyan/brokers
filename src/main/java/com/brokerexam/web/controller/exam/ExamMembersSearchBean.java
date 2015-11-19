package com.brokerexam.web.controller.exam;

import java.io.Serializable;

public class ExamMembersSearchBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2925279516041016956L;

    private int pageSize;
    private String firstName;
    private String lastName;
    private String passport;
    private int examId;

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}

