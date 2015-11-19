package com.brokerexam.repository.exam;

import com.brokerexam.domain.exam.Exam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamMapper implements RowMapper<Exam> {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String START_DATE = "start_date";
    public static final String DATEMOD = "datemod";
    public static final String USERMOD = "usermod";
    public static final String STATUS = "status";
    public static final String ARCHIVED = "archived";


    public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
        Exam exam = new Exam();
        exam.setId(rs.getInt(ID));
        exam.setName(rs.getString(NAME));
        exam.setStartDate(rs.getDate(START_DATE));
        exam.setUsermod(rs.getInt(USERMOD));
        exam.setDatemod(rs.getDate(DATEMOD));
        exam.setStatus(rs.getInt(STATUS));
        exam.setArchived(rs.getInt(ARCHIVED));
        return exam;
    }
}
