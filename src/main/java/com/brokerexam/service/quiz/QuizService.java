package com.brokerexam.service.quiz;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.exam.Question;
import com.brokerexam.domain.quiz.QuizFinalResult;

import java.util.List;

public interface QuizService {

    List<Question> getQuestions(int examId);
    void saveQuizResult(List<Question> questions, ExamMember examMember, int mode);
    List<Question> getSavedQuestions(ExamMember examMember);
    void saveQuizFinalResult(List<Question> questions, ExamMember examMember);
    QuizFinalResult getQuizFinalResult(int emId, int examId);
    byte[] getQuizResultReport(ExamMember examMember, String percentString, int percent);

}
