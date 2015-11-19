package com.brokerexam.repository.exam;

import com.brokerexam.domain.exam.ExamMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMemberPdfMapper implements RowMapper<ExamMember> {
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String SURNAME = "surname";
    public static final String PERCENT = "percent";




    public ExamMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamMember examMember = new ExamMember();
        examMember.setId(rs.getInt(ID));
        examMember.setFirstName(rs.getString(FIRST_NAME));
        examMember.setLastName(rs.getString(LAST_NAME));
        examMember.setSurname(rs.getString(SURNAME));
        examMember.setResult(rs.getInt(PERCENT));


        return examMember;
    }
}
