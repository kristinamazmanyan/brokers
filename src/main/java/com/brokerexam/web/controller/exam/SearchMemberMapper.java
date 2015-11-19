package com.brokerexam.web.controller.exam;

import com.brokerexam.domain.exam.ExamMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMemberMapper implements RowMapper<ExamMember> {

    public static final String ID = "id";
    public static final String PASSPORT = "passport";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String SURENAME = "surname";
    public static final String TESTNAME = "testname";
    public static final String STAGE = "stage";
    public static final String STARTDATE = "startdate";
    public static final String TESTID = "testid";
    public static final String PERCENT = "percent";


    public ExamMember mapRow(ResultSet rs, int rowNum) throws SQLException {

        ExamMember member = new ExamMember();
        member.setId(rs.getInt(ID));
        member.setPassport(rs.getString(PASSPORT));
        member.setFirstName(rs.getString(FIRST_NAME));
        member.setLastName(rs.getString(LAST_NAME));
        member.setSurname(rs.getString(SURENAME));
        member.setTestName(rs.getString(TESTNAME));
        member.setStatus(rs.getInt(STAGE));
        member.setStartDate(rs.getDate(STARTDATE));
        member.setTestId(rs.getInt(TESTID));
        member.setResult(rs.getInt(PERCENT));
        if(member.getResult() == 0){
            member.setPercentStr("");
        }else{
            member.setPercentStr(member.getResult() + "%");
        }

        return member;
    }


}

