package com.brokerexam.repository.quiz;

import com.brokerexam.domain.quiz.QuizResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizResultMapper implements RowMapper<QuizResult> {

    public static final String QUESTION = "question";
    public static final String ANSWER = "answer";
    public static final String ANSWERA = "answer_a";
    public static final String ANSWERB = "answer_b";
    public static final String ANSWERC = "answer_c";
    public static final String ANSWERD = "answer_d";
    public static final String RIGHT_ANSWER = "right_answer";



    public QuizResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuizResult result = new QuizResult();
        result.setQuestion(rs.getString(QUESTION));
        result.setAnswer(rs.getInt(ANSWER));
        result.setAnswerA(rs.getString(ANSWERA));
        result.setAnswerB(rs.getString(ANSWERB));
        result.setAnswerC(rs.getString(ANSWERC));
        result.setAnswerD(rs.getString(ANSWERD));
        result.setRightAnswer(rs.getInt(RIGHT_ANSWER));

        return result;
    }

}
