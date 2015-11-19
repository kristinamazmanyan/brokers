package com.brokerexam.web.controller.exam;

import java.io.Serializable;
import java.util.Date;

public class ExamSearchBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3294233457678948783L;

    private int pageSize;
    private String name;
     private Date startDate;
    private Date endDate;
    private int archived;


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }



}
