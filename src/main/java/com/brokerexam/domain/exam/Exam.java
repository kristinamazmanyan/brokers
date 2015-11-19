package com.brokerexam.domain.exam;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3485170948285244141L;


    private int id;
    private String name;
    private Date startDate;
    private long usermod;
    private Date datemod;
    private int status;
    private int archived;


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



    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

