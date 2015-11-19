package com.brokerexam.web.controller.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.common.security.UserDetails;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamCondition;
import com.brokerexam.service.dictionaries.DictionaryService;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.web.util.JsfUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("examListController")
@Scope("view")
public class ExamListController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4802232487518860373L;
    protected static final int DEFAULT_PAGE_SIZE = 20;
    private int pageSize = DEFAULT_PAGE_SIZE;

    public static final String EXAM_SEARCH_BEAN = "examSearchBean";
    private long total;

    private ExamSearchBean searchBean;

    private List<SelectItem> examConditionListModel = new ArrayList<SelectItem>();

    private LazyExamDataModel lazyExamDataModel;


    @Autowired
    @Qualifier("userDetails")
    private transient UserDetails userDetails;


    @Autowired
    @Qualifier("examService")
    private transient ExamService examService;

    @Autowired
    @Qualifier("dictionaryService")
    private transient DictionaryService dictionaryService;


    @PostConstruct
    public void init() {


        List<ExamCondition> examConditions = dictionaryService.getExamConditions();
        for (ExamCondition examCondition : examConditions){
            examConditionListModel.add(new SelectItem(examCondition.getId(), examCondition.getName()));
        }

        if (searchBean == null) {
            searchBean = new ExamSearchBean();

        }

        pageSize = DEFAULT_PAGE_SIZE;
        searchBean.setPageSize(pageSize);

        lazyExamDataModel = new LazyExamDataModel();
    }

    public void search(ActionEvent event) {
        pageSize = DEFAULT_PAGE_SIZE;
        lazyExamDataModel = new LazyExamDataModel();
        searchBean.setPageSize(pageSize);
        //searchBean.setArchived(0);

    }

    public void reset(ActionEvent event) {
        searchBean = new ExamSearchBean();
        lazyExamDataModel = new LazyExamDataModel();
        pageSize = DEFAULT_PAGE_SIZE;
        searchBean.setPageSize(pageSize);

    }

    public class LazyExamDataModel extends
            LazyDataModel<Exam> implements Serializable {

        private static final long serialVersionUID = -2641003238642168505L;
        private List<Exam> exams;

        @Override
        public List<Exam> load(int first, int pageSize, String sortField,
                                        SortOrder sortOrder, Map<String, Object> filters) {
            try {
                PagingResult<Exam> pagingResult = examService.search(searchBean, first, pageSize, sortField,
                        sortOrder.name());

                total = pagingResult.getTotalRows();

                exams = pagingResult.getList();
            } catch (Exception e) {
                e.printStackTrace();
                exams = new ArrayList<Exam>();
                total = 0;
            }
            setRowCount((int) total);
            setPageSize(pageSize);
            return exams;
        }


        @Override
        public Object getRowKey(Exam exam) {
            return exam.getId();
        }

        @Override
        public Exam getRowData(String rowKey) {
            if (exams == null)
                return null;
            for (Exam exam : exams) {
                if (String.valueOf(exam.getId()).equals(rowKey))
                    return exam;
            }
            return null;
        }

        public int getRowCount() {
            return (int) total;
        }
    }



    public void deleteExam(ActionEvent event) {
        try {
            String examId = JsfUtils.getRequestParam("examId");
            long userId = JsfUtils.getUser().getId();
            examService.deleteExam(Long.parseLong(examId), userId);

            EventLog.getInstance().write(EventType.DELETE_TEST, "",
                    userId, examId);

        } catch (DataAccessException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error when deleting exam!",
                    null));
        }
    }

    public void archiveExam(ActionEvent event) {
        try {
            String examId = JsfUtils.getRequestParam("examId");
            long userId = JsfUtils.getUser().getId();
            examService.archiveExam(Long.parseLong(examId), userId);

            EventLog.getInstance().write(EventType.ARCHIVE_TEST, "",
                    userId, examId);

        } catch (DataAccessException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error when archiving exam!",
                    null));
        }
    }


    public void downloadResult() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse res = (HttpServletResponse) fc
                .getExternalContext().getResponse();
        res.setContentType("application/force-download");
        String filename = "exam_result";
        filename += ".pdf";
        res.setHeader("Content-disposition", "attachment; filename=" + filename);
        try {
            ServletOutputStream out = res.getOutputStream();
            String examId = JsfUtils.getRequestParam("examId");
            out.write(examService.getExamResultReport(Long.parseLong(examId)));

        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        fc.responseComplete();

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

    public ExamSearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(ExamSearchBean searchBean) {
        this.searchBean = searchBean;
    }


    public LazyExamDataModel getLazyExamDataModel() {
        return lazyExamDataModel;
    }

    public void setLazyExamDataModel(
            LazyExamDataModel lazyExamDataModel) {
        this.lazyExamDataModel = lazyExamDataModel;
    }

    public List<SelectItem> getExamConditionListModel() {
        return examConditionListModel;
    }

    public void setExamConditionListModel(
            List<SelectItem> examConditionListModel) {
        this.examConditionListModel = examConditionListModel;
    }





}

