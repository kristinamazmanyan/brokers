package com.brokerexam.web.controller.exam;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.quiz.QuizFinalResult;
import com.brokerexam.web.util.MessageUtil;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.service.quiz.QuizService;
import com.brokerexam.web.util.JsfUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("examMembersListController")
@Scope("view")
public class ExamMembersListController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7286573376480347487L;

    protected static final int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE;
    public static final String EXAM_MEMBERS_SEARCH_BEAN = "examMembersSearchBean";

    private static final Logger LOG = LoggerFactory
            .getLogger(ExamMembersListController.class);

    private long total;
    private int examId;
    private QuizFinalResult result;
    private UploadedFile membersFile1;

    private ExamMembersSearchBean searchBean = new ExamMembersSearchBean();

    private LazyExamMembersDataModel lazyExamMembersDataModel;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("quizService")
    private QuizService quizService;

    @PostConstruct
    public void init() {

        if (searchBean == null) {
            searchBean = new ExamMembersSearchBean();
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);
        } else {
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);
        }
        lazyExamMembersDataModel = new LazyExamMembersDataModel();
        try {
            String examIdStr = JsfUtils.getRequestParam("id");
            examId = Integer.valueOf(examIdStr);
            searchBean.setExamId(examId);

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void search(ActionEvent event) {
        pageSize = DEFAULT_PAGE_SIZE;
        lazyExamMembersDataModel = new LazyExamMembersDataModel();
        searchBean.setPageSize(pageSize);

    }

    public void reset(ActionEvent event) {
        searchBean = new ExamMembersSearchBean();
        lazyExamMembersDataModel = new LazyExamMembersDataModel();
        pageSize = DEFAULT_PAGE_SIZE;
        searchBean.setPageSize(pageSize);
        searchBean.setExamId(examId);

    }

    public class LazyExamMembersDataModel extends
            LazyDataModel<ExamMember> implements Serializable {

        private static final long serialVersionUID = -2641003238642168505L;
        private List<ExamMember> examMembers;

        @Override
        public List<ExamMember> load(int first, int pageSize, String sortField,
                                          SortOrder sortOrder, Map<String, Object> filters) {
            try {
                PagingResult<ExamMember> pagingResult = examService
                        .searchExamMembers(searchBean, first, pageSize,
                                sortField, sortOrder.name());
                examMembers = pagingResult.getList();
                total = pagingResult.getTotalRows();
            } catch (Exception e) {
                e.printStackTrace();
                examMembers = new ArrayList<ExamMember>();
                total = 0;
            }
            setRowCount((int) total);
            setPageSize(pageSize);
            return examMembers;
        }

        @Override
        public Object getRowKey(ExamMember examMember) {
            return examMember.getId();
        }

        @Override
        public ExamMember getRowData(String rowKey) {
            if (examMembers == null)
                return null;
            for (ExamMember examMember : examMembers) {
                if (String.valueOf(examMember.getId()).equals(rowKey))
                    return examMember;
            }
            return null;
        }

        public int getRowCount() {
            return (int) total;
        }
    }

    public void deleteExamMember(ActionEvent event) {
        try {
            long userId = JsfUtils.getUser().getId();
            String examMemberId = JsfUtils.getRequestParam("examMemberId");
            examService.deleteExamMember(Long.parseLong(examMemberId), userId);

            EventLog.getInstance().write(EventType.DELETE_MEMBER, "",
                    userId, examId);

        } catch (DataAccessException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error when deleting Exam Member!",
                    null));
        }
    }

    public void editExamMember() {
        try {
            String examMemberId = JsfUtils.getRequestParam("examMemberId");
            String examId=JsfUtils.getRequestParam("examId");
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getApplicationContextPath() + "/pages/exams/edit-member.jsf?id=" + examMemberId + "&examId=" + examId);

        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error when editing Exam Member!",
                    null));
        }
    }

    public void activateExamMember() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse res = (HttpServletResponse) fc
                .getExternalContext().getResponse();
        res.setContentType("application/force-download");
        String filename = "applicantresult";
        filename += ".pdf";
        res.setHeader("Content-disposition", "attachment; filename=" + filename);
        try {
            ServletOutputStream out = res.getOutputStream();
            long userId = JsfUtils.getUser().getId();
            String examMemberId = JsfUtils.getRequestParam("examMemberId");
            examService.activateExamMember(Long.parseLong(examMemberId), userId);

            EventLog.getInstance().write(EventType.ACTIVE_MEMBER, "",
                    userId, examId);

            out.write(examService.getMemberReport(Long.parseLong(examMemberId)));

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        fc.responseComplete();


    }

    public void downloadResult() throws IOException{
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse res = (HttpServletResponse) fc
                .getExternalContext().getResponse();
        res.setContentType("application/force-download");
        String filename = "exam_result";
        filename += ".pdf";
        res.setHeader("Content-disposition", "attachment; filename=" + filename);
        try {
            ServletOutputStream out = res.getOutputStream();
            String examMemberId = JsfUtils.getRequestParam("examMemberId");
            result = quizService.getQuizFinalResult(Integer.parseInt(examMemberId), examId);
            ExamMember examMember = examService.getExamMember(Long.parseLong(examMemberId));
            out.write(quizService.getQuizResultReport(examMember, result.getPercentString(), result.getPercent()));

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        fc.responseComplete();

    }

    public void uploadMembersFile1(ActionEvent event) {

        InputStream inputStream = null;

        try {
System.out.println("import1");
            if (membersFile1 == null) {
                System.out.println("import2");
                return;
            }
            System.out.println("import3");
            String fileName = membersFile1.getFileName();
            String suffix = FilenameUtils.getExtension(fileName);

            if (!("xls".equals(suffix) || "xlsx".equals(suffix))) {
                MessageUtil.addErrorUsingKey("common.invalid_excel_file.msg");
                return;
            }
            long userId = JsfUtils.getUser().getId();
            inputStream = membersFile1.getInputstream();
            boolean success = examService.importMembers(inputStream,
                    userId, examId);

            if (success) {
                String infoMessage = MessageUtil.getMessage("exam.import_members_msg");
                MessageUtil.addMessage(infoMessage);
            } else {
                String infoMessage = MessageUtil.getMessage("exam.import_members_fail_msg");
                MessageUtil.addMessage(infoMessage);
            }
        } catch (Exception e) {
            String errorMsg = e.getMessage();
            errorMsg = errorMsg == null ? "" : errorMsg;
            String message = MessageUtil
                    .getMessage("exam.import_members_fail_msg");
            MessageUtil.addError(message + " : " + errorMsg);

        } finally {
            IOUtils.closeQuietly(inputStream);
        }

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public QuizFinalResult getResult() {
        return result;
    }

    public void setResult(QuizFinalResult result) {
        this.result = result;
    }

    public ExamMembersSearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(ExamMembersSearchBean searchBean) {
        this.searchBean = searchBean;
    }

    public LazyExamMembersDataModel getLazyExamMembersDataModel() {
        return lazyExamMembersDataModel;
    }

    public void setLazyExamMembersDataModel(LazyExamMembersDataModel lazyExamMembersDataModel) {
        this.lazyExamMembersDataModel = lazyExamMembersDataModel;
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

    public UploadedFile getMembersFile1() {
        return membersFile1;
    }

    public void setMembersFile1(UploadedFile membersFile1) {
        this.membersFile1 = membersFile1;
    }
}

