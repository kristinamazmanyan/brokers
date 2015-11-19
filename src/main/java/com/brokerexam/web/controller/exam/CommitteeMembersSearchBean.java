package com.brokerexam.web.controller.exam;

import java.io.Serializable;

public class CommitteeMembersSearchBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5490093492933583745L;

    private int pageSize;
    private String firstName;
    private String lastName;
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

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}

