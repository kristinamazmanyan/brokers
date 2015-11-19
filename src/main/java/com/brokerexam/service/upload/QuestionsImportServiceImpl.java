package com.brokerexam.service.upload;

import com.brokerexam.domain.exam.Question;
import com.brokerexam.repository.exam.ExamDao;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;

@Service("questionsImportService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class QuestionsImportServiceImpl implements QuestionsImportService{

    static final String QUESTION = "Question";
    static final String ANSWER_1 = "Answer_1";
    static final String ANSWER_2 = "Answer_2";
    static final String ANSWER_3 = "Answer_3";
    static final String ANSWER_4 = "Answer_4";
    static final String RIGHT_ANSWER = "Right_Answer";
    static final String TYPE = "Type";
    static final String SECTION = "Section";



    @Autowired
    @Qualifier("examDao")
    ExamDao examDao;


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean importExamFile(InputStream inputStream, long examId) throws Exception {
        List<Question> importBeans = new ArrayList<Question>();
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
                Question importBean = parseExamRow(row, columnNameMap);
                importBeans.add(importBean);
            }
        }

        if (importBeans.isEmpty()) {
            return false;
        }

        examDao.importExamQuestions(importBeans, examId);

        return true;
    }

    private Question parseExamRow(Row row, Map<Integer, String> columnNameMap)
            throws Exception {
        int colIndex = 0;
        int cellType = 0;
        String columnName = null;
        Cell cell = null;
        Question importBean = new Question();
        Iterator<Cell> cells = row.cellIterator();

        while (cells.hasNext()) {
            colIndex++;
            cell = (Cell) cells.next();
            cellType = cell.getCellType();

            if (Cell.CELL_TYPE_BLANK == cellType) {
                continue;
            }

            columnName = columnNameMap.get(colIndex);

            if (QUESTION.equals(columnName)) {
                importBean.setQuestion(getString(cell, cellType));
            } else if (ANSWER_1.equals(columnName)) {
                importBean.setAnswerA(getString(cell, cellType));
            } else if (ANSWER_2.equals(columnName)) {
                importBean.setAnswerB(getString(cell, cellType));
            } else if (ANSWER_3.equals(columnName)) {
                importBean.setAnswerC(getString(cell, cellType));
            }else if (ANSWER_4.equals(columnName)) {
                importBean.setAnswerD(getString(cell, cellType));
            }else if (RIGHT_ANSWER.equals(columnName)) {
                importBean.setRightAnswer(getInt(cell, cellType));
            } else {
                throw new Exception();
            }

        }

        return importBean;
    }
    private int getInt(Cell cell, int cellType) {
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        String value = cell.getRichStringCellValue().getString();
        Integer result = Integer.valueOf(value.trim());
        return result;
    }


    private String getString(Cell cell, int cellType) {
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        }
        String value = cell.getRichStringCellValue().getString();
        return value.trim();
    }

    public ExamDao getExamDao() {
        return examDao;
    }

    public void setExamDao(ExamDao examDao) {
        this.examDao = examDao;
    }
}
