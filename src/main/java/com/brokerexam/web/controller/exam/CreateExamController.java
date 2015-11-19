package com.brokerexam.web.controller.exam;

import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.service.dictionaries.DictionaryService;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.web.util.JsfUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;

@Controller("createExamController")
@Scope("view")
public class CreateExamController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3930803649027958800L;

    private static final Logger LOG = LoggerFactory
            .getLogger(CreateExamController.class);

    private Exam exam = new Exam();

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("dictionaryService")
    private DictionaryService dictionaryService;

    @PostConstruct
    public void init() {

    }

    public void createExam(ActionEvent event) {
        try {

            Date currentDate = new Date();

            long userId = JsfUtils.getUser().getId();

            exam.setUsermod(userId);
            exam.setDatemod(currentDate);

            exam.setArchived(0);

            exam.setName(exam.getName().trim());

            if (TextUtils.isEmpty(exam.getName()))

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }
             if (exam.getStartDate() == null) {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }


            examService.saveExam(exam);

            EventLog.getInstance().write(EventType.CREATE_TEST, exam.getName(),
                    userId, exam.getId());

            String infoMessage = MessageUtil
                    .getMessage("exam.create_exam_msg");
            LOG.debug(infoMessage);
            MessageUtil.addMessage(infoMessage);

            init();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            MessageUtil.addErrorUsingKey("exam.error_create_exam_msg");
        }
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamService getExamService() {
        return examService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public DictionaryService getDictionaryService() {
        return dictionaryService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
