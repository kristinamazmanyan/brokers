package com.brokerexam.service.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.web.controller.exam.ExamSearchBean;
import com.brokerexam.web.controller.search.MemberSearchBean;
import com.brokerexam.web.controller.exam.CommitteeMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamMembersSearchBean;

import java.io.InputStream;
import java.util.Date;

public interface ExamService {

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

    boolean importMembers(InputStream inputStream, long userId, int examId) throws Exception;

    ExamMember loginExamMember(String passport, String code);

    void changeStatus(long id, int status);

    Date initEndTime(ExamMember examMember);

    Date getEndTime(ExamMember examMember);

    byte[] getExamResultReport(long examId);
}
