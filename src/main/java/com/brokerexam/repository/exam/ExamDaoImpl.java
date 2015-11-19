package com.brokerexam.repository.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.exam.*;
import com.brokerexam.repository.AbstractDaoImpl;
import com.brokerexam.repository.services.pdf.ExamsPdfGenerator;
import com.brokerexam.web.controller.exam.ExamSearchBean;
import com.brokerexam.web.controller.exam.SearchMemberMapper;
import com.brokerexam.web.controller.search.MemberSearchBean;
import com.brokerexam.repository.dictionaries.DictionaryDao;
import com.brokerexam.web.controller.exam.CommitteeMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamMembersSearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

@Repository("examDao")
@Lazy
public class ExamDaoImpl extends AbstractDaoImpl implements ExamDao {

    @Autowired
    ExamDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Autowired
    @Qualifier("dictionaryDao")
    private DictionaryDao dictionaryDao;

    ExamMapper examMapper = new ExamMapper();
    CommitteeMemberMapper committeeMemberMapper = new CommitteeMemberMapper();
    private ExamMemberMapper examMemberMapper = new ExamMemberMapper();
    private ExamMemberPdfMapper examMemberPdfMapper = new ExamMemberPdfMapper();
    SearchMemberMapper memberMapper = new SearchMemberMapper();

    private static final String searchCommonSql = "select {columns} from exams ex where ex.status<>2 ";
    private static final String columnsSql = "ex.id, ex.name, ex.start_date, ex.status, ex.archived, ex.usermod, ex.datemod  ";

    private static final String loginSql = " select t.id, t.first_name, t.last_name, t.surname, t.passport,  "+
            "t.usermod, t.datemod, t.exam_id, t.session_id, t.status, t.permission from exam_members t "+
            "where (t.status=1 or t.status=3) and t.passport=? and t.session_id=?";


    @Override
    public ExamMember loginExamMember(String passport, String code) {
        ExamMember examMember;
        try {
            String query = loginSql;
            examMember = getJdbcTemplate().queryForObject(query, examMemberMapper, passport, code);

        }
        catch (IncorrectResultSizeDataAccessException e) {
            examMember = null;
        }

        return examMember;
    }

    @Override
    public PagingResult<Exam> search(ExamSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        PagingResult<Exam> pagingResult = new PagingResult<Exam>();

        Map<String, Object> params = new HashMap<String, Object>();
        String whereClause = createWhereClouse(searchBean, params);

        String commonSql = searchCommonSql;

        String searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", columnsSql);

        StringBuilder limit = new StringBuilder();
        limit.append(" limit ").append(pageSize);
        limit.append(" offset ").append(first);

        StringBuilder finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);
        //finalSql.append(createOrderByClause(sortField, sortOrder));
        finalSql.append(" order by ex.id");
        finalSql.append(limit.toString());

        List<Exam> list = parameterJdbcTemplate.query(finalSql.toString(),
                params, examMapper);

        searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", "count(ex.id) as cnt");

        finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);

        long count = parameterJdbcTemplate.queryForObject(finalSql.toString(),
                params, Long.class);

        pagingResult.setTotalRows(count);
        pagingResult.setList(list);

        return pagingResult;
    }

    private String createWhereClouse(ExamSearchBean bean,
                                     Map<String, Object> params) {
        StringBuilder where = new StringBuilder();

        if (hasText(bean.getName())) {
            where.append(" and LOWER(ex.name) like :name");
            params.put(ExamMapper.NAME, bean.getName()
                    .toLowerCase() + "%");
        }

        if (bean.getStartDate() != null) {
            where.append(" and ex.start_date >= :start_date");
            params.put("start_date", bean.getStartDate());
        }

        if (bean.getEndDate() != null) {
            where.append(" and ex.start_date <= :end_date");
            params.put("end_date", bean.getEndDate());
        }


        where.append(" and ex.archived= :archived");
        params.put(ExamMapper.ARCHIVED, bean.getArchived());

        return where.toString();
    }


    private static final String searchExamMembersSql = "select {columns} from exam_members t  where t.status<>2  ";
    private static final String searchExamMembersSql1 = "select {columns} from exam_members t, quiz_final_result fin  where t.id=fin.em_id and t.exam_id=fin.exam_id and t.status>3 and t.exam_id=:id order by fin.id ";

    private static final String examMembersColumnsSql = "t.id, t.first_name, t.last_name, t.surname, t.passport, "+
            "t.usermod, t.datemod, t.exam_id, t.session_id, t.status, t.permission ";
    private static final String examMembersColumnsSql1 = "t.id, t.first_name, t.last_name, t.surname, "+
            "fin.percent ";

    @Override
    public PagingResult<ExamMember> searchExamMembers(ExamMembersSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        PagingResult<ExamMember> pagingResult = new PagingResult<ExamMember>();

        Map<String, Object> params = new HashMap<String, Object>();
        String whereClause = createWhereClouse(searchBean, params);

        String commonSql = searchExamMembersSql;

        String searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", examMembersColumnsSql);

        StringBuilder limit = new StringBuilder();
        limit.append(" limit ").append(pageSize);
        limit.append(" offset ").append(first);

        StringBuilder finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);
        //finalSql.append(createOrderByClause(sortField, sortOrder));
        finalSql.append(" order by t.id");
        finalSql.append(limit.toString());

        List<ExamMember> list = parameterJdbcTemplate.query(
                finalSql.toString(), params, examMemberMapper);

        searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", "count(t.id) as cnt");

        finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);

        long count = parameterJdbcTemplate.queryForObject(finalSql.toString(),
                params, Long.class);

        pagingResult.setTotalRows(count);
        pagingResult.setList(list);

        return pagingResult;
    }

    private String createWhereClouse(ExamMembersSearchBean bean,
                                     Map<String, Object> params) {
        StringBuilder where = new StringBuilder();

        if (bean.getExamId() != 0) {
            where.append(" and exam_id=:exam_id ");
            params.put("exam_id", bean.getExamId());
        }
        if (hasText(bean.getFirstName())) {
            where.append(" and LOWER(t.first_name) like :first_name");
            params.put(ExamMemberMapper.FIRST_NAME, bean.getFirstName()
                    .toLowerCase() + "%");
        }
        if (hasText(bean.getLastName())) {
            where.append(" and LOWER(t.last_name) like :last_name");
            params.put(ExamMemberMapper.LAST_NAME, bean.getLastName()
                    .toLowerCase() + "%");
        }
        if (hasText(bean.getPassport())) {
            where.append(" and LOWER(t.passport) like :passport");
            params.put(ExamMemberMapper.PASSPORT, bean.getPassport()
                    .toLowerCase() + "%");
        }

        return where.toString();
    }

    private static final String searchAllCommonSql = "select * from("+
            " select t.id id, t.first_name firstname, t.last_name lastname, t.surname surname, t.passport passport, tr.name as testname, tr.id testid,  t.status as stage, "+
            " tr.start_date startdate, r.percent from exam_members t "+
            " inner join exams tr on tr.id=t.exam_id  "+
            " left join quiz_final_result r on r.exam_id=tr.id and r.em_id=t.id "+
            " where t.status<>2) total where 1=1 ";;

    @Override
    public PagingResult<ExamMember> searchMembers(MemberSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        PagingResult<ExamMember> pagingResult = new PagingResult<ExamMember>();

        Map<String, Object> params = new HashMap<String, Object>();
        String whereClause = createWhereClouse1(searchBean, params);

        String searchSql = searchAllCommonSql;


        StringBuilder limit = new StringBuilder();
        limit.append(" limit ").append(pageSize);
        limit.append(" offset ").append(first);

        StringBuilder finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);
        finalSql.append(" order by total.passport");
        finalSql.append(limit.toString());

        List<ExamMember> list = parameterJdbcTemplate.query(finalSql.toString(),
                params, memberMapper);

        searchSql = searchAllCommonSql;
        searchSql = searchSql.replace("*", "count(total.id) as cnt");

        finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);

        long count = parameterJdbcTemplate.queryForObject(finalSql.toString(),
                params, Long.class);

        pagingResult.setTotalRows(count);
        pagingResult.setList(list);

        return pagingResult;
    }

    private String createWhereClouse1(MemberSearchBean bean,
                                     Map<String, Object> params) {
        StringBuilder where = new StringBuilder();


        if (hasText(bean.getFirstName())) {
            where.append(" and LOWER(total.firstname) like :firstname");
            params.put(memberMapper.FIRST_NAME, bean.getFirstName()
                    .toLowerCase() + "%");
        }

        if (hasText(bean.getLastName())) {
            where.append(" and LOWER(total.lastname) like :lastname");
            params.put(memberMapper.LAST_NAME, bean.getLastName()
                    .toLowerCase() + "%");
        }

        if (hasText(bean.getPassport())) {
            where.append(" and LOWER(total.passport) like :passport");
            params.put(memberMapper.PASSPORT, bean.getPassport().toLowerCase() + "%");
        }

        return where.toString();
    }

    private static final String cmColumnsSql = "t.id, t.first_name, t.last_name, t.position_type, p.name, t.usermod, t.datemod, t.exam_id  ";
    private static final String searchCMSql = "select {columns} from committee_members t, rf_examinator_position p where p.id=t.position_type   ";


    @Override
    public PagingResult<CommitteeMember> searchCM(CommitteeMembersSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        PagingResult<CommitteeMember> pagingResult = new PagingResult<CommitteeMember>();

        Map<String, Object> params = new HashMap<String, Object>();
        String whereClause = createWhereClouse(searchBean, params);

        String commonSql = searchCMSql;

        String searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", cmColumnsSql);

        StringBuilder limit = new StringBuilder();
        limit.append(" limit ").append(pageSize);
        limit.append(" offset ").append(first);

        StringBuilder finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);
        //finalSql.append(createOrderByClause(sortField, sortOrder));
        finalSql.append(limit.toString());

        List<CommitteeMember> list = parameterJdbcTemplate.query(
                finalSql.toString(), params, committeeMemberMapper);

        searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", "count(t.id) as cnt");

        finalSql = new StringBuilder();
        finalSql.append(searchSql);
        finalSql.append(whereClause);

        long count = parameterJdbcTemplate.queryForObject(finalSql.toString(),
                params, Long.class);

        pagingResult.setTotalRows(count);
        pagingResult.setList(list);

        return pagingResult;
    }

    private String createWhereClouse(CommitteeMembersSearchBean bean,
                                     Map<String, Object> params) {
        StringBuilder where = new StringBuilder();

        if (bean.getExamId() != 0) {
            where.append(" and exam_id=:exam_id ");
            params.put("exam_id", bean.getExamId());
        }
        if (hasText(bean.getFirstName())) {
            where.append(" and LOWER(t.first_name) like :first_name");
            params.put(CommitteeMemberMapper.FIRST_NAME, bean.getFirstName()
                    .toLowerCase() + "%");
        }
        if (hasText(bean.getLastName())) {
            where.append(" and LOWER(t.last_name) like :last_name");
            params.put(CommitteeMemberMapper.LAST_NAME, bean.getLastName()
                    .toLowerCase() + "%");
        }

        return where.toString();
    }

    private static final String deleteExamSql = "update exams ex set usermod = :usermod, datemod = :datemod, status = 2 where id = :id";

    @Override
    public void deleteExam(long id, long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("usermod", userId);
        params.put("datemod", new Timestamp(System.currentTimeMillis()));
        parameterJdbcTemplate.update(deleteExamSql, params);
    }

    private static final String deleteExamMemberSql = "update exam_members t set usermod = :usermod, datemod = :datemod, status = 2 where id = :id";

    @Override
    public void deleteExamMember(long id, long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("usermod", userId);
        params.put("datemod", new Timestamp(System.currentTimeMillis()));
        parameterJdbcTemplate.update(deleteExamMemberSql, params);

    }

    private static final String archiveExamSql = "update exams ex set usermod = :usermod, datemod = :datemod, archived = 1 where id = :id";


    @Override
    public void archiveExam(long id, long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("usermod", userId);
        params.put("datemod", new Timestamp(System.currentTimeMillis()));
        parameterJdbcTemplate.update(archiveExamSql, params);

    }

    private static final String createExamSql = "insert into exams "
            + "(id, name, start_date, status, "
            + " archived, usermod, datemod) "
            + "values(?, ?, ?, ?, ?, ?, ?)";

    private static final String updateExamSql = "update exams "
            + "set name = :name , start_date = :start_date , status=:status, "
            + "archived=:archived, usermod = :usermod, "
            + "datemod = :datemod where id = :id";

    @Override
    public void saveExam(Exam exam) {
        int examId = exam.getId();

        if (examId == 0) {
            getJdbcTemplate().update(
                    createExamSql,
                    new Object[]{
                            examId, exam.getName(), exam.getStartDate(), exam.getStatus(),
                            exam.getArchived(), exam.getUsermod(), exam.getDatemod()});

        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", exam.getId());
            params.put(ExamMapper.NAME, exam.getName());
            params.put(ExamMapper.START_DATE, exam.getStartDate());
            params.put(ExamMapper.STATUS, exam.getStatus());
            params.put(ExamMapper.ARCHIVED, exam.getArchived());
            params.put(ExamMapper.USERMOD, exam.getUsermod());
            params.put(ExamMapper.DATEMOD, exam.getDatemod());
            parameterJdbcTemplate.update(updateExamSql, params);

        }

    }

    private static final String createCMSql = "insert into committee_members "
            + "(id, first_name, last_name, position_type, exam_id, usermod, datemod) "
            + "values(?, ?, ?, ?, ?, ?, ?)";

    private static final String updateCMSql = "update committee_members "
            + "set first_name = :first_name, last_name = :last_name, position_type=:position_type, exam_id=:exam_id, "
            + "usermod = :usermod , datemod = :datemod where id = :id";


    @Override
    public void saveCM(CommitteeMember committeeMember) {

        int committeeMemberId = committeeMember.getId();

        if (committeeMemberId == 0) {
            getJdbcTemplate().update(
                    createCMSql,
                    new Object[]{committeeMemberId,
                            committeeMember.getFirstName(),
                            committeeMember.getLastName(),
                            committeeMember.getPositionType(),
                            committeeMember.getExamId(),
                            committeeMember.getUsermod(),
                            committeeMember.getDatemod()});

        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", committeeMember.getId());
            params.put(CommitteeMemberMapper.FIRST_NAME,
                    committeeMember.getFirstName());
            params.put(CommitteeMemberMapper.LAST_NAME,
                    committeeMember.getLastName());
            params.put(CommitteeMemberMapper.POSITION_TYPE,
                    committeeMember.getPositionType());
            params.put(CommitteeMemberMapper.USERMOD,
                    committeeMember.getUsermod());
            params.put(CommitteeMemberMapper.DATEMOD,
                    committeeMember.getDatemod());
            params.put(CommitteeMemberMapper.EXAM_ID,
                    committeeMember.getExamId());
            parameterJdbcTemplate.update(updateCMSql, params);

        }

    }


    private static final String getExamSql = " select ex.id, ex.name, ex.start_date, "
            + "ex.status, "
            + "ex.usermod, ex.datemod, ex.archived  from exams ex "
            + " where ex.id = :id and ex.status <>2";

    @Override
    public Exam getExam(long examId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", examId);

        Exam exam = parameterJdbcTemplate.queryForObject(getExamSql,
                params, examMapper);
        return exam;
    }

    private static final String deleteCMSql = "delete from committee_members where id = :id";

    @Override
    public void deleteCM(long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        parameterJdbcTemplate.update(deleteCMSql, params);
    }

    private static final String getTrainingExamSql = " select t.id, t.first_name, t.last_name, t.position_type, p.name, t.usermod, t.datemod, t.exam_id from committee_members t, rf_examinator_position p where p.id=t.position_type and t.id = :id";
    @Override
    public CommitteeMember getCommitteeMember(long cmId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", cmId);

        CommitteeMember trainingExam = parameterJdbcTemplate.queryForObject(
                getTrainingExamSql, params, committeeMemberMapper);
        return trainingExam;
    }

    @Override
    public byte[] getMemberReport(long id) {
        ExamMember examMember = getExamMember(id);
        return ExamsPdfGenerator.generateExamMemberPDF(examMember);
    }

    private static final String activateExamMemberSql = "update exam_members t set usermod = :usermod, datemod = :datemod, status = 1 where id = :id";

   @Override
    public void activateExamMember(long id, long userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("usermod", userId);
        params.put("datemod", new Timestamp(System.currentTimeMillis()));
        parameterJdbcTemplate.update(activateExamMemberSql, params);


    }

    private static final String createExamMemberSql = "insert into exam_members "
            + "(id, first_name, last_name, surname, passport, status, exam_id, session_id, usermod, datemod) "
            + "values(?,?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String updateExamMemberSql = "update exam_members "
            + "set first_name = :first_name, last_name = :last_name, surname=:surname, passport=:passport, "
            + "usermod = :usermod , datemod = :datemod where id = :id";

    @Override
    public boolean saveExamMember(ExamMember examMember) {
        int examMemberId = examMember.getId();

        if (examMemberId == 0) {
            int size = getExamMemberByPassport(examMember.getPassport(), examMember.getExamId());
            if(size > 0){
                return false;
            }
            String sessionId = getSessionId().substring(0,8);
            examMember.setSessionId(sessionId);
            getJdbcTemplate().update(
                    createExamMemberSql,
                    new Object[] {
                            examMemberId,
                            examMember.getFirstName(),
                            examMember.getLastName(),
                            examMember.getSurname(),
                            examMember.getPassport(),
                            examMember.getStatus(),
                            examMember.getExamId(),
                            examMember.getSessionId(),
                            examMember.getUsermod(),
                            examMember.getDatemod() });

        } else {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", examMember.getId());
            params.put(ExamMemberMapper.FIRST_NAME,
                    examMember.getFirstName());
            params.put(ExamMemberMapper.LAST_NAME,
                    examMember.getLastName());
            params.put(ExamMemberMapper.SURNAME,
                    examMember.getSurname());
            params.put(ExamMemberMapper.PASSPORT,
                    examMember.getPassport());
            params.put(ExamMemberMapper.USERMOD,
                    examMember.getUsermod());
            params.put(ExamMemberMapper.DATEMOD,
                    examMember.getDatemod());
            parameterJdbcTemplate.update(updateExamMemberSql, params);

        }

        return true;

    }


    private static final String getExamMemberSql = " select t.id, t.first_name, t.last_name, t.surname, t.passport, "+
            "t.usermod, t.datemod, t.exam_id, t.session_id, t.status, t.permission from exam_members t "+
            "where t.id=:id";

    public ExamMember getExamMember(long examMemberId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", examMemberId);

        ExamMember examMember = parameterJdbcTemplate.queryForObject(
                getExamMemberSql, params, examMemberMapper);
        return examMember;


    }


    private static final String getExamMemberSqlByPassport = " select t.id, t.first_name, t.last_name, t.surname, t.passport, "+
            "t.usermod, t.datemod, t.exam_id, t.session_id, t.status, t.permission from exam_members t "+
            "where t.passport=:passport and t.exam_id=:examId";

    private int getExamMemberByPassport(String passport, int examId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("passport", passport);
        params.put("examId", examId);

        List<ExamMember> list = parameterJdbcTemplate.query(
                getExamMemberSqlByPassport, params, examMemberMapper);


        return list.size();


    }


    @Override
    public void importMembers(List<ExamMember> importBeans, long userId, int examId) throws Exception {
        for(ExamMember participant : importBeans){

            Date date = new Date();
            String sessionId = getSessionId().substring(0,8);
            participant.setSessionId(sessionId);
            getJdbcTemplate().update(
                    createExamMemberSql,
                    new Object[] {
                            0,
                            participant.getFirstName(),
                            participant.getLastName(),
                            participant.getSurname(),
                            participant.getPassport(),
                            participant.getStatus(),
                            examId,
                            participant.getSessionId(),
                            participant.getUsermod(),
                            date });

        }
    }

    private static final String createExamQuestionSql = "insert into exam_questions "
            + "(id, exam_id, question, "
            + "answer_a, answer_b, answer_c, answer_d, right_answer, quest_index) "
            + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void importExamQuestions(List<Question> questions, long examId) {

        try {
            int index=0;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("exam_id", examId);
            Integer quest_index = parameterJdbcTemplate.queryForObject("select max(quest_index) from exam_questions where exam_id=:exam_id", params, Integer.class);

            if(quest_index != null)
                index=quest_index.intValue();

            for (Question question : questions) {
               getJdbcTemplate().update(
                        createExamQuestionSql,
                        new Object[]{0, examId,
                                question.getQuestion(),
                                question.getAnswerA(), question.getAnswerB(), question.getAnswerC(), question.getAnswerD(),
                                question.getRightAnswer(), index+1});
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    private static final String changeStatusSql = "update exam_members t set usermod = :usermod, datemod = :datemod, status = :status where id = :id";

    @Override
    public void changeStatus(long id, int status) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("status", status);
        params.put("usermod", 0);
        params.put("datemod", new Timestamp(System.currentTimeMillis()));
        parameterJdbcTemplate.update(changeStatusSql, params);
    }

    private static final String updateEMEndDateSql = "update exam_members set exam_end_time=:exam_end_time where id=:id";

    @Override
    public Date initEndTime(ExamMember examMember) {
        Date currentDate = new Date(System.currentTimeMillis());
        Exam exam = getExam(examMember.getExamId());
        ExamTime examTime = dictionaryDao.getExamTime(1);
        long endTime =currentDate.getTime() + (examTime.getTime()*60*1000);
        Date endDate = new Date(endTime);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("exam_end_time", endDate);
        params.put("id", examMember.getId());
        parameterJdbcTemplate.update(updateEMEndDateSql, params);
        return endDate;
    }

    private static final String getEMEndDateSql = "select exam_end_time from exam_members em where em.id = :id and em.exam_id=:exam_id";

    @Override
    public Date getEndTime(ExamMember examMember) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", examMember.getId());
        params.put("exam_id", examMember.getExamId());
        Date endDate = parameterJdbcTemplate.queryForObject(getEMEndDateSql,
                params, Date.class);
        return endDate;
    }

    @Override
    public byte[] getExamResultReport(long examId) {
        try {
            List<ExamMember> list = getExamMembers(examId);
            Exam exam = getExam(examId);
            List<CommitteeMember> list1 = getCommitteeMembers(examId);
            CommitteeMember head = getCommitteeMemberHead(examId);
            return ExamsPdfGenerator.generateExamAllResultPDF(exam, list, list1, head);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String searchCommitteeMemberSql1 = "select {columns} from committee_members t, rf_examinator_position p  where p.id=t.position_type and t.exam_id=:id and t.position_type=0 ";

    private static final String searchCommitteeMemberSql2 = "select {columns} from committee_members t, rf_examinator_position p  where p.id=t.position_type and t.exam_id=:id and t.position_type=1 ";


    public List<CommitteeMember> getCommitteeMembers(long examId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", examId);

        String commonSql = searchCommitteeMemberSql1;

        String searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", cmColumnsSql);

        List<CommitteeMember> list = parameterJdbcTemplate.query(
                searchSql, params, committeeMemberMapper);

        return list;


    }

    public CommitteeMember getCommitteeMemberHead(long examId){
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", examId);

            String commonSql = searchCommitteeMemberSql2;

            String searchSql = commonSql;
            searchSql = searchSql.replace("{columns}", cmColumnsSql);

            CommitteeMember head = parameterJdbcTemplate.queryForObject(
                    searchSql, params, committeeMemberMapper);
            return head;
        }catch(Exception e){
            return null;
        }



    }

    public List<ExamMember> getExamMembers(long examId){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", examId);

        String commonSql = searchExamMembersSql1;

        String searchSql = commonSql;
        searchSql = searchSql.replace("{columns}", examMembersColumnsSql1);

        List<ExamMember> list = parameterJdbcTemplate.query(
                searchSql, params, examMemberPdfMapper);

        return list;


    }

    private String  getSessionId ()
    {
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
    }

    private boolean hasText(Object ob) {
        if (!(ob instanceof String)) {
            return false;
        }
        String value = (String) ob;
        return (value.trim().length() > 0);
    }
}
