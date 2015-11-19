package com.brokerexam.repository.quiz;

import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.exam.Question;
import com.brokerexam.domain.quiz.QuizFinalResult;
import com.brokerexam.domain.quiz.QuizResult;
import com.brokerexam.repository.AbstractDaoImpl;
import com.brokerexam.repository.dictionaries.DictionaryDao;
import com.brokerexam.repository.exam.ExamDao;
import com.brokerexam.repository.services.pdf.ExamsPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("quizDao")
@Lazy
public class QuizDaoImpl extends AbstractDaoImpl implements QuizDao{

    @Autowired
    QuizDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Autowired
    @Qualifier("examDao")
    private ExamDao examDao;

    @Autowired
    @Qualifier("dictionaryDao")
    private DictionaryDao dictionaryDao;

    ExamQuestionMapper examQuestionMapper = new ExamQuestionMapper();
    SavedQuestionMapper savedQuestionMapper = new SavedQuestionMapper();
    private QuizFinalResultMapper quizFinalResultMapper = new QuizFinalResultMapper();
    private QuizResultMapper quizResultMapper = new QuizResultMapper();

    private static final String getExamQuestionsSql = "select q.* from exam_questions q, exams c where c.id=q.exam_id and c.id=? and q.quest_index=? order by q.id ";

    @Override
    public List<Question> getQuestions(int examId) {
        int q_index=0;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exam_id", examId);

        Integer quest_index  = parameterJdbcTemplate.queryForObject("select max(quest_index) from exam_questions where exam_id=:exam_id", params, Integer.class);
        if(quest_index != null)
            q_index=quest_index.intValue();

        List<Question> result1 = (List<Question>) getJdbcTemplate().query(
                getExamQuestionsSql, examQuestionMapper, examId, q_index);

        int cnt1 = dictionaryDao.getExamTime(1).getQ_count();

        List<Question> result = new ArrayList<Question>();

        List<Integer> indexes = new ArrayList<Integer>();
        int i = 0;
        while (i < cnt1) {
            int index = (int) (Math.random() * (result1.size() - 1));
            if (!indexes.contains(index)) {
                result.add(result1.get(index));
                indexes.add(index);
                i++;
            }
        }

        return result;
    }

    private static final String createQuizResultSql = "insert into quiz_results "
            + "(id, exam_id, em_id, question_id, answer, right_answer) "
            + "values(?, ?, ?, ?, ?, ?)";

    private static final String createQuizResultSql1 = "insert into quiz_results_1 "
            + "(id, exam_id, em_id, question_id, answer, right_answer) "
            + "values(?, ?, ?, ?, ?, ?)";

    private static final String updateQuizResultSql = "update quiz_results "
            + "set answer=:answer where exam_id = :exam_id and em_id=:em_id and question_id=:question_id";

    @Override
    public void saveQuizResult(List<Question> questions, ExamMember examMember, int mode) {
        if (mode == 0) {
            for (Question question : questions) {
                getJdbcTemplate().update(
                        createQuizResultSql,
                        new Object[] { 0, examMember.getExamId(),
                                examMember.getId(), question.getId(),
                                question.getUserAnswer(),
                                question.getRightAnswer() });
            }

        } else {
            for (Question question : questions) {

                Map<String, Object> params = new HashMap<String, Object>();
                params.put("exam_id", examMember.getExamId());
                params.put("em_id", examMember.getId());
                params.put("question_id", question.getId());
                params.put("answer", question.getUserAnswer());
                parameterJdbcTemplate.update(updateQuizResultSql, params);
            }

        }
    }

    private static final String getSavedQuestionsSql = "select q.*, er.answer from quiz_results er, exam_questions q "
            + "where q.id=er.question_id and er.exam_id=? and er.em_id=?";

    @Override
    public List<Question> getSavedQuestions(ExamMember examMember) {
        List<Question> result = (List<Question>) getJdbcTemplate().query(
                getSavedQuestionsSql, savedQuestionMapper, examMember.getExamId(),
                examMember.getId());
        return result;
    }

    private static final String createQuizFinalResultSql = "insert into quiz_final_result "
            + "(id, exam_id, em_id, percent) " + "values(?, ?, ?, ?)";

    @Override
    public void saveQuizFinalResult(List<Question> questions, ExamMember examMember) {
       int rightAnswerCount = 0;
       if(examMember.getPermission() == 0) {
           for (Question question : questions) {

               if (question.getRightAnswer() == question.getUserAnswer())
                   rightAnswerCount++;

               Map<String, Object> params = new HashMap<String, Object>();
               params.put("exam_id", examMember.getExamId());
               params.put("em_id", examMember.getId());
               params.put("question_id", question.getId());
               params.put("answer", question.getUserAnswer());
               parameterJdbcTemplate.update(updateQuizResultSql, params);
           }
       }else{
           for (Question question : questions) {

               rightAnswerCount++;

               Map<String, Object> params = new HashMap<String, Object>();
               params.put("exam_id", examMember.getExamId());
               params.put("em_id", examMember.getId());
               params.put("question_id", question.getId());
               params.put("answer", question.getRightAnswer());
               parameterJdbcTemplate.update(updateQuizResultSql, params);
           }

           for (Question question : questions) {
               getJdbcTemplate().update(
                       createQuizResultSql1,
                       new Object[]{0, examMember.getExamId(),
                               examMember.getId(), question.getId(),
                               question.getUserAnswer(),
                               question.getRightAnswer()});
           }

       }

        int percent = 0;
        if (questions.size() != 0) {
            percent = Math.round((rightAnswerCount * 100) / questions.size());
        }

        int id = 0;
        getJdbcTemplate().update(createQuizFinalResultSql,
                new Object[] { 0, examMember.getExamId(), examMember.getId(), percent });

        examDao.changeStatus(examMember.getId(), 4);

    }

    private static final String getQuizResultSql = "select * from quiz_final_result res where res.em_id = :emId and res.exam_id = :examId";
    @Override
    public QuizFinalResult getQuizFinalResult(int emId, int examId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("emId", emId);
        params.put("examId", examId);

        QuizFinalResult result = parameterJdbcTemplate.queryForObject(
                getQuizResultSql, params, quizFinalResultMapper);

        return result;
    }

    private static final String commonSql1 = "select q.question, q.answer_a, q.answer_b, q.answer_c, q.answer_d, er.right_answer, er.answer from quiz_results er, exam_questions q where er.question_id=q.id and er.em_id = :emid and er.exam_id = :examid";

    private List<QuizResult> getQuizResult(int emId, int examId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("emid", emId);
        params.put("examid", examId);

        List<QuizResult> list = parameterJdbcTemplate.query(commonSql1, params,
                quizResultMapper);

        return list;
    }

    @Override
    public byte[] getQuizResultReport(ExamMember examMember, String percentString, int percent) {
        try {
            List<QuizResult> list = getQuizResult(examMember.getId(),
                    examMember.getExamId());
            Exam exam = examDao.getExam(examMember.getExamId());
            return ExamsPdfGenerator.generateQuizResultPDF(examMember, list, percentString, percent, exam);
        } catch (Exception e) {

        }
        return null;

    }
}
