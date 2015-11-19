package com.brokerexam.web.controller.quiz;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.quiz.QuizFinalResult;
import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.service.quiz.QuizService;
import com.brokerexam.web.util.JsfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;

@Controller("quizResultController")
@Scope("view")
public class QuizResultController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5083905139607485860L;

    private QuizFinalResult result;
    private String resultMsg;


    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("quizService")
    private QuizService quizService;

    @PostConstruct
    public void init() {
        ExamMember examMember = JsfUtils.getExamMember();
        result = quizService.getQuizFinalResult(examMember.getId(), examMember.getExamId());
        String infoMessage = MessageUtil.getMessage("exam.result_msg");
        resultMsg = MessageFormat.format(infoMessage, result.getPercentString());

    }

    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse res = (HttpServletResponse) fc
                .getExternalContext().getResponse();
        res.setContentType("application/force-download");
        String filename = "exam_result";
        filename += ".pdf";
        res.setHeader("Content-disposition", "attachment; filename=" + filename);
        try {
            ServletOutputStream out = res.getOutputStream();
            ExamMember examMember = JsfUtils.getExamMember();
            out.write(quizService.getQuizResultReport(examMember, result.getPercentString(), result.getPercent()));
            examService.changeStatus(examMember.getId(), 5);

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        fc.responseComplete();

    }

    public void logout() throws IOException{
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getApplicationContextPath()+"/");
        ec.getSessionMap().clear();
        HttpSession httpSession = (HttpSession)ec.getSession(false);
        httpSession.invalidate();
    }



    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }


    public ExamService getExamService() {
        return examService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public QuizFinalResult getResult() {
        return result;
    }

    public void setResult(QuizFinalResult result) {
        this.result = result;
    }

    public QuizService getQuizService() {
        return quizService;
    }

    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
}
