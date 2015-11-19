package com.brokerexam.web.controller.exam;

import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.exam.ExamMember;
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

@Controller("editExamMemberController")
@Scope("view")
public class EditExamMemberController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2508746912470652162L;

    private static final Logger LOG = LoggerFactory
            .getLogger(EditExamMemberController.class);

    private ExamMember examMember = new ExamMember();

   private int examId;
    private int examMemberId;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("dictionaryService")
    private DictionaryService dictionaryService;

    @PostConstruct
    public void init() {

        try {
            String examMemberIdStr = JsfUtils.getRequestParam("id");
            examMemberId = Integer.valueOf(examMemberIdStr);
            String examIdStr = JsfUtils.getRequestParam("examId");
            examId = Integer.valueOf(examIdStr);
            examMember = examService.getExamMember(examMemberId);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public void createMember(ActionEvent event) {
        try {
            Date currentDate = new Date();

            long userId = JsfUtils.getUser().getId();

            examMember.setUsermod(userId);
            examMember.setDatemod(currentDate);

            if (TextUtils.isEmpty(examMember.getFirstName()) )

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }

            if (TextUtils.isEmpty(examMember.getLastName()) )

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }
            if (TextUtils.isEmpty(examMember.getPassport()) )

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }



            examService.saveExamMember(examMember);

            String name = examMember.getFirstName() + " " + examMember.getLastName();
            EventLog.getInstance().write(EventType.UPDATE_MEMBER, name,
                    userId, examMember.getId());

            String infoMessage = MessageUtil.getMessage("exam.update_applicant_msg");
            LOG.debug(infoMessage);
            MessageUtil.addMessage(infoMessage);


        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    public ExamMember getExamMember() {
        return examMember;
    }

    public void setExamMember(ExamMember examMember) {
        this.examMember = examMember;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getExamMemberId() {
        return examMemberId;
    }

    public void setExamMemberId(int examMemberId) {
        this.examMemberId = examMemberId;
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

