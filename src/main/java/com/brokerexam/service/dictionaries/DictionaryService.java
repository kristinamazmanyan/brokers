package com.brokerexam.service.dictionaries;

import com.brokerexam.domain.exam.CMPosition;
import com.brokerexam.domain.exam.ExamCondition;

import java.util.List;

public interface DictionaryService {
    List<ExamCondition> getExamConditions();
    List<CMPosition> getCMPositions();
}
