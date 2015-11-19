package com.brokerexam.repository.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.domain.exam.Question;
import com.brokerexam.web.controller.exam.CommitteeMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamSearchBean;
import com.brokerexam.web.controller.search.MemberSearchBean;

import java.util.Date;
import java.util.List;

public interface ExamDao {

    ExamMember loginExamMember(String passport, String code);

    PagingResult<Exam> search(ExamSearchBean searchBean, int first,
                              int pageSize, String sortField, String sortOrder) throws Exception;

    PagingResult<ExamMember> searchExamMembers(ExamMembersSearchBean searchBean, int first,
                                               int pageSize, String sortField, String sortOrder) throws Exception;

    PagingResult<ExamMember> searchMembers(MemberSearchBean searchBean, int first,
                                               int pageSize, String sortField, String sortOrder) throws Exception;

    PagingResult<CommitteeMember> searchCM(CommitteeMembersSearchBean searchBean, int first,
                                           int pageSize, String sortField, String sortOrder) throws Exception;


    void deleteExam(long id, long userId);

    void deleteExamMember(long id, long userId);

    void archiveExam(long id, long userId);

    void saveExam(Exam exam);

    void saveCM(CommitteeMember committeeMember);

    Exam getExam(long examId);

    void deleteCM(long id);

    CommitteeMember getCommitteeMember(long cmId);

    public byte[] getMemberReport(long id);

    void activateExamMember(long id, long userId);

    boolean saveExamMember(ExamMember examMember);

    ExamMember getExamMember(long examMemberId);

    void importMembers(List<ExamMember> importBeans, long userId, int examId) throws Exception;

    void importExamQuestions(List<Question> questions, long examId);

    void changeStatus(long id, int status);

    Date initEndTime(ExamMember examMember);

    Date getEndTime(ExamMember examMember);

    byte[] getExamResultReport(long examId);


}
