package com.brokerexam.web.controller.search;

import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.quiz.QuizFinalResult;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.common.security.UserDetails;
import com.brokerexam.service.dictionaries.DictionaryService;
import com.brokerexam.service.exam.ExamService;
import com.brokerexam.service.quiz.QuizService;
import com.brokerexam.web.util.JsfUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
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

@Controller("memberSearchController")
@Scope("view")
public class MemberSearchController implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6544576803939040324L;
    protected static final int DEFAULT_PAGE_SIZE = 10;
    private int pageSize = DEFAULT_PAGE_SIZE;

    public static final String MEMBER_SEARCH_BEAN = "memberSearchBean";

    private static final Logger LOG = LoggerFactory
            .getLogger(MemberSearchController.class);

    private long total;
    private MemberSearchBean searchBean = new MemberSearchBean();
    private LazyMemberSearchDataModel lazyMemberSearchDataModel;

    private List<SelectItem> searchTypeListModel = new ArrayList<SelectItem>();

    @Autowired
    @Qualifier("dictionaryService")
    private DictionaryService dictionaryService;

    @Autowired
    @Qualifier("examService")
    private ExamService examService;

    @Autowired
    @Qualifier("userDetails")
    private transient UserDetails userDetails;

    @Autowired
    @Qualifier("quizService")
    private QuizService quizService;


    @PostConstruct
    public void init() {

        try {


        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        if (searchBean == null) {
            searchBean = new MemberSearchBean();
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);

        } else {
            pageSize = DEFAULT_PAGE_SIZE;
            searchBean.setPageSize(pageSize);

        }
        lazyMemberSearchDataModel = new LazyMemberSearchDataModel();

    }

    public void search(ActionEvent event) {
        pageSize = DEFAULT_PAGE_SIZE;
        lazyMemberSearchDataModel = new LazyMemberSearchDataModel();
        searchBean.setPageSize(pageSize);

    }

    public void reset(ActionEvent event) {
        searchBean = new MemberSearchBean();
        lazyMemberSearchDataModel = new LazyMemberSearchDataModel();
        pageSize = DEFAULT_PAGE_SIZE;
        searchBean.setPageSize(pageSize);


    }

    public class LazyMemberSearchDataModel extends LazyDataModel<ExamMember>
            implements Serializable {

        private static final long serialVersionUID = -2641003238642168505L;
        private List<ExamMember> members;

        @Override
        public List<ExamMember> load(int first, int pageSize, String sortField,
                                     SortOrder sortOrder, Map<String, Object> filters) {
            try {
                PagingResult<ExamMember> pagingResult = examService.searchMembers(
                        searchBean, first, pageSize, sortField,
                        sortOrder.name());
                members = pagingResult.getList();
                total = pagingResult.getTotalRows();
            } catch (Exception e) {
                e.printStackTrace();
                members = new ArrayList<ExamMember>();
                total = 0;
            }
            setRowCount((int) total);
            setPageSize(pageSize);
            return members;
        }

        @Override
        public Object getRowKey(ExamMember member) {
            return member.getId();
        }

        @Override
        public ExamMember getRowData(String rowKey) {
            if (members == null)
                return null;
            for (ExamMember member : members) {
                if (String.valueOf(member.getId()).equals(rowKey))
                    return member;
            }
            return null;
        }

        public int getRowCount() {
            return (int) total;
        }
    }


    public void downloadResultExam() throws IOException {
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
            String examId = JsfUtils.getRequestParam("examId");
            QuizFinalResult result = quizService.getQuizFinalResult(Integer.parseInt(examMemberId), Integer.parseInt(examId));
            ExamMember examMember = examService.getExamMember(Long.parseLong(examMemberId));
            out.write(quizService.getQuizResultReport(examMember, result.getPercentString(), result.getPercent()));

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

    public MemberSearchBean getSearchBean() {
        return searchBean;
    }

    public void setSearchBean(MemberSearchBean searchBean) {
        this.searchBean = searchBean;
    }

    public LazyMemberSearchDataModel getLazyMemberSearchDataModel() {
        return lazyMemberSearchDataModel;
    }

    public void setLazyMemberSearchDataModel(
            LazyMemberSearchDataModel lazyMemberSearchDataModel) {
        this.lazyMemberSearchDataModel = lazyMemberSearchDataModel;
    }

    public List<SelectItem> getSearchTypeListModel() {
        return searchTypeListModel;
    }

    public void setSearchTypeListModel(List<SelectItem> searchTypeListModel) {
        this.searchTypeListModel = searchTypeListModel;
    }



}


