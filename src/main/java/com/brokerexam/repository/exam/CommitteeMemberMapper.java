package com.brokerexam.repository.exam;

import com.brokerexam.domain.exam.CommitteeMember;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommitteeMemberMapper implements RowMapper<CommitteeMember> {
    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String DATEMOD = "datemod";
    public static final String USERMOD = "usermod";
    public static final String POSITION_TYPE = "position_type";
    public static final String POSITION_NAME = "name";
    public static final String EXAM_ID = "exam_id";


    public CommitteeMember mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommitteeMember cm = new CommitteeMember();
        cm.setId(rs.getInt(ID));
        cm.setExamId(rs.getInt(EXAM_ID));
        cm.setFirstName(rs.getString(FIRST_NAME));
        cm.setLastName(rs.getString(LAST_NAME));
        cm.setUsermod(rs.getInt(USERMOD));
        cm.setDatemod(rs.getDate(DATEMOD));
        cm.setPositionType(rs.getInt(POSITION_TYPE));
        cm.setPositionName(rs.getString(POSITION_NAME));
        return cm;
    }
}
