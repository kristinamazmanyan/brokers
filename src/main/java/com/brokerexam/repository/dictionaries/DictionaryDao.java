package com.brokerexam.repository.dictionaries;

import com.brokerexam.domain.exam.CMPosition;
import com.brokerexam.domain.exam.ExamCondition;
import com.brokerexam.domain.exam.ExamTime;

import java.util.List;

public interface DictionaryDao {

    List<ExamCondition> getExamConditions();
    List<CMPosition> getCMPositions();
    ExamTime getExamTime(long id);
}
