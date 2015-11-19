package com.brokerexam.web.controller.search;

import java.io.Serializable;

public class MemberSearchBean implements Serializable {


    private static final long serialVersionUID = 2718241241966316513L;

    private int pageSize;
    private String firstName;
    private String lastName;
    private String passport;


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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



}

