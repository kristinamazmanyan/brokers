package com.brokerexam.repository.dictionaries;

import com.brokerexam.domain.exam.ExamTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamTimeMapper implements RowMapper<ExamTime> {


    public static final String ID = "id";
    public static final String TIME = "time";
    public static final String Q_COUNT = "q_count";



    public ExamTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamTime et = new ExamTime();
        et.setId(rs.getInt(ID));
        et.setTime(rs.getInt(TIME));
        et.setQ_count(rs.getInt(Q_COUNT));
        return et;

    }

}
