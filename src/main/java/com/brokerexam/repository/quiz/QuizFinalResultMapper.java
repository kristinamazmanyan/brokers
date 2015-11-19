package com.brokerexam.repository.quiz;

import com.brokerexam.domain.quiz.QuizFinalResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizFinalResultMapper implements RowMapper<QuizFinalResult> {

    public static final String ID = "id";
    public static final String EM_ID = "em_id";
    public static final String EXAM_ID = "exam_id";
    public static final String PERCENT = "percent";


    public QuizFinalResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizFinalResult result = new QuizFinalResult();
        result.setId(rs.getInt(ID));
        result.setEmId(rs.getInt(EM_ID));
        result.setExamId(rs.getInt(EXAM_ID));
        result.setPercent(rs.getInt(PERCENT));

        return result;
    }

}