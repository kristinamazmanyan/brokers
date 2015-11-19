package com.brokerexam.repository.exam;

import com.brokerexam.domain.exam.ExamMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMemberMapper implements RowMapper<ExamMember> {
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String SURNAME = "surname";
    public static final String PASSPORT = "passport";
    public static final String STATUS = "status";
    public static final String PERMISSION = "permission";
    public static final String EXAM_ID = "exam_id";
    public static final String SESSION_ID = "session_id";
    public static final String DATEMOD = "datemod";
    public static final String USERMOD = "usermod";




    public ExamMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamMember examMember = new ExamMember();
        examMember.setId(rs.getInt(ID));
        examMember.setFirstName(rs.getString(FIRST_NAME));
        examMember.setLastName(rs.getString(LAST_NAME));
        examMember.setSurname(rs.getString(SURNAME));
        examMember.setPassport(rs.getString(PASSPORT));
        examMember.setStatus(rs.getInt(STATUS));
        examMember.setExamId(rs.getInt(EXAM_ID));
        examMember.setSessionId(rs.getString(SESSION_ID));
        examMember.setUsermod(rs.getInt(USERMOD));
        examMember.setDatemod(rs.getDate(DATEMOD));
        examMember.setPermission(rs.getInt(PERMISSION));


        return examMember;
    }
}
