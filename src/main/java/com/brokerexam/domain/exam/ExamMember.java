package com.brokerexam.domain.exam;

import java.io.Serializable;
import java.util.Date;

public class ExamMember implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6790110048979630116L;

    private int id;
    private String firstName;
    private String lastName;
    private String surname;
    private String passport;
    private int examId;
    private int status;
    private int permission;
    private String sessionId;
    private long usermod;
    private Date datemod;
    private int result;
    private Date startDate;
    private int testId;
    private String percentStr;
    private String testName;


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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
    public int getResult() {
        return result;
    }
    public void setResult(int result) {
        this.result = result;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getPercentStr() {
        return percentStr;
    }

    public void setPercentStr(String percentStr) {
        this.percentStr = percentStr;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}

