package com.brokerexam.web.controller.exam;

import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.service.upload.QuestionsImportService;
import com.brokerexam.web.util.JsfUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import java.io.InputStream;
import java.io.Serializable;

@Controller("examQuestionsUploadController")
@Scope("view")
public class ExamQuestionsUploadController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7386058855817575821L;

    private static final Logger LOG = LoggerFactory
            .getLogger(ExamQuestionsUploadController.class);

    private UploadedFile examFile;
     long examId;

    @Autowired
    @Qualifier("questionsImportService")
    private QuestionsImportService questionsImportService;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @PostConstruct
    public void init() {

        try {
            String examIdStr = JsfUtils.getRequestParam("id");
            examId = Long.valueOf(examIdStr);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void uploadExamFile(ActionEvent event) {

        InputStream inputStream = null;

        try {

            if (examFile == null) {
                return;
            }
            String fileName = examFile.getFileName();
            String suffix = FilenameUtils.getExtension(fileName);
            long userId = JsfUtils.getUser().getId();

            if (!("xls".equals(suffix) || "xlsx".equals(suffix))) {
                MessageUtil.addErrorUsingKey("common.invalid_excel_file.msg");
                return;
            }
            inputStream = examFile.getInputstream();
            boolean success = questionsImportService.importExamFile(
                    inputStream, examId);

            if (success) {
                EventLog.getInstance().write(EventType.UPLOAD_QUESTIONS, "",
                        userId, examId);

                String infoMessage = MessageUtil.getMessage("exam.import_file_msg");
                MessageUtil.addMessage(infoMessage);
            } else {
                String infoMessage = MessageUtil
                        .getMessage("exam.import_file_fail_msg");
                MessageUtil.addMessage(infoMessage);
            }
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            errorMsg = errorMsg == null ? "" : errorMsg;
            String message = MessageUtil.getMessage("exam.import_file_fail_msg");
            MessageUtil.addError(message + " : " + errorMsg);

        } finally {
            IOUtils.closeQuietly(inputStream);
        }

    }


    public UploadedFile getExamFile() {
        return examFile;
    }

    public void setExamFile(UploadedFile examFile) {
        this.examFile = examFile;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public QuestionsImportService getQuestionsImportService() {
        return questionsImportService;
    }

    public void setQuestionsImportService(QuestionsImportService questionsImportService) {
        this.questionsImportService = questionsImportService;
    }

    public ExamService getExamService() {
        return examService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }


}

