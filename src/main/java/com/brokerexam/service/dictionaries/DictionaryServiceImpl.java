package com.brokerexam.service.dictionaries;

import com.brokerexam.domain.exam.CMPosition;
import com.brokerexam.domain.exam.ExamCondition;
import com.brokerexam.repository.dictionaries.DictionaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dictionaryService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class DictionaryServiceImpl implements DictionaryService{

    @Autowired
    @Qualifier("dictionaryDao")
    private DictionaryDao dictioanaryDao;



    @Override
    public List<ExamCondition> getExamConditions() {
        return dictioanaryDao.getExamConditions();
    }

    @Override
    public List<CMPosition> getCMPositions() {
        return dictioanaryDao.getCMPositions();
    }
}
