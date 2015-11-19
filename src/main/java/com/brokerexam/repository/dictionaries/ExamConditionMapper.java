package com.brokerexam.repository.dictionaries;

import com.brokerexam.domain.exam.ExamCondition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamConditionMapper implements RowMapper<ExamCondition> {


    public static final String ID = "id";
    public static final String NAME = "name";



    public ExamCondition mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamCondition pos = new ExamCondition();
        pos.setName(rs.getString(NAME));
        pos.setId(rs.getInt(ID));



        return pos;

    }

}