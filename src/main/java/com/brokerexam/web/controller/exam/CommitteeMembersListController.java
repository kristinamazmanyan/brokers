package com.brokerexam.web.controller.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.event.EventType;
import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.service.event.EventLog;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.web.util.JsfUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("committeeMembersListController")
@Scope("view")
public class CommitteeMembersListController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4912992878652001196L;

    protected static final int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE;
    public static final String COMMITTEE_MEMBERS_SEARCH_BEAN = "committeeMembersSearchBean";

    private static final Logger LOG = LoggerFactory
            .getLogger(CommitteeMembersListController.class);


    private long total;
    private int examId;

    private CommitteeMembersSearchBean searchBean = new CommitteeMembersSearchBean();
    private LazyTrainingExamsDataModel lazyTrainingExamsDataModel;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @PostConstruct
    public void init() {

        if (searchBean == null) {
            searchBean = new CommitteeMembersSearchBean();
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);
        } else {
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);
        }
        lazyTrainingExamsDataModel = new LazyTrainingExamsDataModel();
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
        lazyTrainingExamsDataModel = new LazyTrainingExamsDataModel();
        searchBean.setPageSize(pageSize);

    }

    public void reset(ActionEvent event) {
        searchBean = new CommitteeMembersSearchBean();
        lazyTrainingExamsDataModel = new LazyTrainingExamsDataModel();
        pageSize = DEFAULT_PAGE_SIZE;
        searchBean.setPageSize(pageSize);
        searchBean.setExamId(examId);

    }

    public class LazyTrainingExamsDataModel extends LazyDataModel<CommitteeMember>
            implements Serializable {

        private static final long serialVersionUID = -2641003238642168505L;
        private List<CommitteeMember> trainingExams;

        @Override
        public List<CommitteeMember> load(int first, int pageSize, String sortField,
                               SortOrder sortOrder, Map<String, Object> filters) {
            try {
                PagingResult<CommitteeMember> pagingResult = examService.searchCM(
                        searchBean, first, pageSize, sortField,
                        sortOrder.name());
                trainingExams = pagingResult.getList();
                total = pagingResult.getTotalRows();
            } catch (Exception e) {
                e.printStackTrace();
                trainingExams = new ArrayList<CommitteeMember>();
                total = 0;
            }
            setRowCount((int) total);
            setPageSize(pageSize);
            return trainingExams;
        }

        @Override
        public Object getRowKey(CommitteeMember trainingExam) {
            return trainingExam.getId();
        }

        @Override
        public CommitteeMember getRowData(String rowKey) {
            if (trainingExams == null)
                return null;
            for (CommitteeMember trainingExam : trainingExams) {
                if (String.valueOf(trainingExam.getId()).equals(rowKey))
                    return trainingExam;
            }
            return null;
        }

        public int getRowCount() {
            return (int) total;
        }
    }

    public void deleteCM(ActionEvent event) {
        try {
            String cmId = JsfUtils.getRequestParam("cmId");
            long userId = JsfUtils.getUser().getId();
            examService.deleteCM(Long.parseLong(cmId));

            EventLog.getInstance().write(EventType.DELETE_CM, "",
                    userId, cmId);

        } catch (DataAccessException e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error when deleting Committee member!",
                    null));
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



    public LazyTrainingExamsDataModel getLazyTrainingExamsDataModel() {
        return lazyTrainingExamsDataModel;
    }

    public void setLazyTrainingExamsDataModel(
            LazyTrainingExamsDataModel lazyTrainingExamsDataModel) {
        this.lazyTrainingExamsDataModel = lazyTrainingExamsDataModel;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public CommitteeMembersSearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(CommitteeMembersSearchBean searchBean) {
        this.searchBean = searchBean;
    }

    public ExamService getExamService() {
        return examService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }
}

