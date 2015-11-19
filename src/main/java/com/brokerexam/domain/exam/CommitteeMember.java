package com.brokerexam.domain.exam;

import java.io.Serializable;
import java.util.Date;

public class CommitteeMember implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7860890900029110020L;

    private int id;
    private String firstName;
    private String lastName;
    private int positionType;
    private String positionName;
    private int examId;
    private long usermod;
    private Date datemod;



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public long getUsermod() {
        return usermod;
    }

    public void setUsermod(long usermod) {
        this.usermod = usermod;
    }

    public Date getDatemod() {
        return datemod;
    }
    public void setDatemod(Date datemod) {
        this.datemod = datemod;
    }
    public int getPositionType() {
        return positionType;
    }
    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }
}

