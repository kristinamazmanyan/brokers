package com.brokerexam.web.controller.exam;

import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.exam.CMPosition;
import com.brokerexam.service.dictionaries.DictionaryService;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.web.util.JsfUtils;
import com.brokerexam.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller("createCommitteeMemberController")
@Scope("view")
public class CreateCommitteeMemberController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6672466521628587305L;

    private static final Logger LOG = LoggerFactory
            .getLogger(CreateCommitteeMemberController.class);

    private CommitteeMember trainingExaminator = new CommitteeMember();

    private List<SelectItem> examPositionNameListModel = new ArrayList<SelectItem>();
    private int selectedExamPositionType;
    private int examId;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("dictionaryService")
    private DictionaryService dictionaryService;

    @PostConstruct
    public void init() {
        List<CMPosition> trainingExamPositions = dictionaryService
                .getCMPositions();
        examPositionNameListModel.add(new SelectItem("", ""));
        for (CMPosition trainingExamPosition : trainingExamPositions) {
            examPositionNameListModel.add(new SelectItem(trainingExamPosition
                    .getId(), trainingExamPosition.getName()));
        }

        try {
            String examIdStr = JsfUtils.getRequestParam("id");
            examId = Integer.valueOf(examIdStr);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public void createCM(ActionEvent event) {
        try {



            Date currentDate = new Date();

            long userId = JsfUtils.getUser().getId();

            trainingExaminator.setUsermod(userId);
            trainingExaminator.setDatemod(currentDate);

            trainingExaminator.setPositionType(selectedExamPositionType);
            trainingExaminator.setExamId(examId);


            if (TextUtils.isEmpty(trainingExaminator.getFirstName()) )

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }

            if (TextUtils.isEmpty(trainingExaminator.getLastName()) )

            {
                MessageUtil.addErrorUsingKey("exam.error_fill_required_comp_fields");
                return;
            }


            examService.saveCM(trainingExaminator);

            String name = trainingExaminator.getFirstName() + " " + trainingExaminator.getLastName();
            EventLog.getInstance().write(EventType.CREATE_CM, name,
                    userId, trainingExaminator.getId());


            String infoMessage = MessageUtil.getMessage("exam.create_examinator_msg");
            LOG.debug(infoMessage);
            MessageUtil.addMessage(infoMessage);


        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }


    public CommitteeMember getTrainingExaminator() {
        return trainingExaminator;
    }

    public void setTrainingExaminator(CommitteeMember trainingExaminator) {
        this.trainingExaminator = trainingExaminator;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public List<SelectItem> getExamPositionNameListModel() {
        return examPositionNameListModel;
    }

    public void setExamPositionNameListModel(
            List<SelectItem> examPositionNameListModel) {
        this.examPositionNameListModel = examPositionNameListModel;
    }



    public int getSelectedExamPositionType() {
        return selectedExamPositionType;
    }

    public void setSelectedExamPositionType(int selectedExamPositionType) {
        this.selectedExamPositionType = selectedExamPositionType;
    }


}

