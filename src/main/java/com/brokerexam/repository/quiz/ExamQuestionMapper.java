package com.brokerexam.repository.quiz;

import com.brokerexam.domain.exam.Question;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamQuestionMapper implements RowMapper<Question> {

    public static final String ID = "id";
    public static final String QUESTION = "question";
    public static final String ANSWER_A = "answer_a";
    public static final String ANSWER_B = "answer_b";
    public static final String ANSWER_C = "answer_c";
    public static final String ANSWER_D = "answer_d";
    public static final String RIGHT_ANSWER = "right_answer";



    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt(ID));
        question.setQuestion(rs.getString(QUESTION));
        question.setAnswerA(rs.getString(ANSWER_A));
        question.setAnswerB(rs.getString(ANSWER_B));
        question.setAnswerC(rs.getString(ANSWER_C));
        question.setAnswerD(rs.getString(ANSWER_D));
        question.setRightAnswer(rs.getInt(RIGHT_ANSWER));




        return question;
    }

}
