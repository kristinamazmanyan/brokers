package com.brokerexam.service.exam;

import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.domain.exam.CommitteeMember;
import com.brokerexam.domain.exam.Exam;
import com.brokerexam.domain.exam.ExamMember;
import com.brokerexam.repository.exam.ExamDao;
import com.brokerexam.web.controller.exam.CommitteeMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamMembersSearchBean;
import com.brokerexam.web.controller.exam.ExamSearchBean;
import com.brokerexam.web.controller.search.MemberSearchBean;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;

@Service("examService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ExamServiceImpl implements ExamService{

    static final String PASSPORT = "Passport";
    static final String FIRSTNAME = "Firstname";
    static final String LASTNAME = "Lastname";
    static final String SURNAME = "Surname";

    @Autowired
    @Qualifier("examDao")
    private ExamDao examDao;

    @Override
    public ExamMember loginExamMember(String passport, String code) {
        return examDao.loginExamMember(passport, code);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void changeStatus(long id, int status) {
        examDao.changeStatus(id, status);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Date initEndTime(ExamMember examMember) {
        return examDao.initEndTime(examMember);
    }

    @Override
    public Date getEndTime(ExamMember examMember) {
        return examDao.getEndTime(examMember);
    }

    @Override
    public byte[] getExamResultReport(long examId) {
        return examDao.getExamResultReport(examId);
    }

    @Override
    public PagingResult<Exam> search(ExamSearchBean searchBean, int first,
                                              int pageSize, String sortField, String sortOrder) throws Exception {
        return examDao.search(searchBean, first, pageSize, sortField, sortOrder);

    }

    @Override
    public PagingResult<ExamMember> searchExamMembers(ExamMembersSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        return examDao.searchExamMembers(searchBean, first, pageSize, sortField, sortOrder);
    }

    @Override
    public PagingResult<ExamMember> searchMembers(MemberSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        return examDao.searchMembers(searchBean, first, pageSize, sortField, sortOrder);
    }

    @Override
    public PagingResult<CommitteeMember> searchCM(CommitteeMembersSearchBean searchBean, int first, int pageSize, String sortField, String sortOrder) throws Exception {
        return examDao.searchCM(searchBean, first, pageSize, sortField, sortOrder);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteExam(long id, long userId){
        examDao.deleteExam(id, userId);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteExamMember(long id, long userId){
        examDao.deleteExamMember(id, userId);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void archiveExam(long id, long userId) {
        examDao.archiveExam(id, userId);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveExam(Exam exam){
        examDao.saveExam(exam);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void saveCM(CommitteeMember committeeMember) {
        examDao.saveCM(committeeMember);
    }

    @Override
    public Exam getExam(long examId){
        return examDao.getExam(examId);
    }

    @Override
    public CommitteeMember getCommitteeMember(long cmId) {
        return examDao.getCommitteeMember(cmId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void deleteCM(long id) {
        examDao.deleteCM(id);
    }

    @Override
    public byte[] getMemberReport(long id) {
        return examDao.getMemberReport(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public void activateExamMember(long id, long userId) {
        examDao.activateExamMember(id, userId);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean saveExamMember(ExamMember examMember) {
        return examDao.saveExamMember(examMember);
    }


    @Override
    public ExamMember getExamMember(long examMemberId) {
        return examDao.getExamMember(examMemberId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean importMembers(InputStream inputStream, long userId, int examId) throws Exception {
        List<ExamMember> importBeans = new ArrayList<ExamMember>();
        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        int colIndex = 0;
        Iterator<Cell> cells = headerRow.cellIterator();
        Map<Integer, String> columnNameMap = new HashMap<Integer, String>();

        while (cells.hasNext()) {
            colIndex++;
            Cell cell = (Cell) cells.next();
            String cellValue = cell.getStringCellValue().toString();
            columnNameMap.put(colIndex, cellValue.trim());
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if (row != null) {
                ExamMember importBean = parsePartRow(row, columnNameMap);
                importBeans.add(importBean);
            }
        }

        if (importBeans.isEmpty()) {
            return false;
        }

        examDao.importMembers(importBeans, userId, examId);


        return true;
    }

    private ExamMember parsePartRow(Row row, Map<Integer, String> columnNameMap)
            throws Exception {
        int colIndex = 0;
        int cellType = 0;
        String columnName = null;
        Cell cell = null;
        ExamMember importBean = new ExamMember();
        Iterator<Cell> cells = row.cellIterator();

        while (cells.hasNext()) {
            colIndex++;
            cell = (Cell) cells.next();
            cellType = cell.getCellType();

            if (Cell.CELL_TYPE_BLANK == cellType) {
                continue;
            }

            columnName = columnNameMap.get(colIndex);

            if (FIRSTNAME.equals(columnName)) {
                importBean.setFirstName(getString(cell, cellType));
            } else if (LASTNAME.equals(columnName)) {
                importBean.setLastName(getString(cell, cellType));
            } else if (SURNAME.equals(columnName)) {
                importBean.setSurname(getString(cell, cellType));
            } else if (PASSPORT.equals(columnName)) {
                importBean.setPassport(getString(cell, cellType));
            } else {
                throw new Exception();
            }

        }

        return importBean;
    }

    private String getString(Cell cell, int cellType) {
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        }
        String value = cell.getRichStringCellValue().getString();
        return value.trim();
    }
}
