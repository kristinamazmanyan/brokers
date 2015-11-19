package com.brokerexam.service.quiz;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.quiz.QuizFinalResult;
import com.brokerexam.repository.quiz.QuizDao;
import com.brokerexam.domain.exam.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("quizService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class QuizServiceImpl implements QuizService{

    @Autowired
    @Qualifier("quizDao")
    private QuizDao quizDao;

    @Override
    public List<Question> getQuestions(int examId) {
        return quizDao.getQuestions(examId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveQuizResult(List<Question> questions, ExamMember examMember, int mode) {
        quizDao.saveQuizResult(questions, examMember, mode);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveQuizFinalResult(List<Question> questions, ExamMember examMember) {
        quizDao.saveQuizFinalResult(questions, examMember);

    }

    @Override
    public QuizFinalResult getQuizFinalResult(int emId, int examId) {
        return quizDao.getQuizFinalResult(emId, examId);
    }

    @Override
    public byte[] getQuizResultReport(ExamMember examMember, String percentString, int percent) {
        return quizDao.getQuizResultReport(examMember, percentString, percent);
    }

    @Override
    public List<Question> getSavedQuestions(ExamMember examMember) {
        return quizDao.getSavedQuestions(examMember);
    }

    public QuizDao getQuizDao() {
        return quizDao;
    }

    public void setQuizDao(QuizDao quizDao) {
        this.quizDao = quizDao;
    }
}
