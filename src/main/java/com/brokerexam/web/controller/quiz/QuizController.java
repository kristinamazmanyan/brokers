package com.brokerexam.web.controller.quiz;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.exam.Question;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.service.quiz.QuizService;
import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("quizController")
@Scope("view")
public class QuizController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4873153054274618493L;

    List<Question> questions = new ArrayList<Question>();
    long endDate;
    long startDate;
    long finalEnd;
    String alertMsg;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("quizService")
    private QuizService quizService;

    @PostConstruct
    public void init() {
        alertMsg = MessageUtil.getMessage("alert_msg");
        ExamMember examMember = JsfUtils.getExamMember();
        Integer completed = JsfUtils.getCompleted();
        if(completed != null && completed == 1){
            try{
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getApplicationContextPath() + "/pages/quiz/quiz-result.jsf");
            }catch(IOException e){

            }
        }else{
            if(examMember.getStatus() == 1){
                questions = quizService.getQuestions(examMember.getExamId());
                quizService.saveQuizResult(questions, examMember, 0);
                examService.changeStatus(examMember.getId(), 3);
                endDate = examService.initEndTime(examMember).getTime();
            }else {
                questions = quizService.getSavedQuestions(examMember);
                endDate = examService.getEndTime(examMember).getTime();
            }
            //startDate = System.currentTimeMillis();
            initQuestionsNumbers();
            Date currentDate = new Date(System.currentTimeMillis());
            finalEnd = (endDate - currentDate.getTime())/1000;
        }

    }

    public void save(ActionEvent event) throws IOException{
        ExamMember examMember =JsfUtils.getExamMember();
        List<Integer> indexes = new ArrayList<Integer>();
        for (Question question : questions){
            if(question.getUserAnswer() == 0){
                indexes.add(question.getNumber());
            }

        }
        if(indexes.size()>0){
            StringBuffer error = new StringBuffer();
            for(Integer index:indexes){
                error.append(index).append(",");
            }
            error = error.deleteCharAt(error.length()-1);

            MessageUtil.addError(MessageUtil.getMessage("exam.error_fill_exam") + error.toString());
            return;
        }

        quizService.saveQuizFinalResult(questions, examMember);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getApplicationContextPath() + "/pages/quiz/quiz-result.jsf");
        ec.getSessionMap().put("completed", 1);

    }

    public void save1() {
        ExamMember examMember =JsfUtils.getExamMember();
        quizService.saveQuizResult(questions, examMember, 1);

    }

    public void finalSave() {
        try{
            ExamMember examMember =JsfUtils.getExamMember();
            quizService.saveQuizFinalResult(questions, examMember);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getApplicationContextPath() + "/pages/quiz/quiz-result.jsf");
            ec.getSessionMap().put("completed", 1);
        }catch(Exception e){

        }


    }

    private void initQuestionsNumbers(){
        int number=1;
        for(Question question:questions){
            question.setNumber(number++);
        }

    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public long getStartDate() {
        return System.currentTimeMillis();
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getFinalEnd() {
        return finalEnd;
    }

    public void setFinalEnd(long finalEnd) {
        this.finalEnd = finalEnd;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public ExamService getExamService() {
        return examService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public QuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
}
