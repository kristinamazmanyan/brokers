package com.brokerexam.repository.dictionaries;


import com.brokerexam.domain.exam.CMPosition;
import com.brokerexam.domain.exam.ExamCondition;
import com.brokerexam.domain.exam.ExamTime;
import com.brokerexam.repository.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("dictionaryDao")
@Lazy
public class DictionaryDaoImpl extends AbstractDaoImpl implements DictionaryDao{

    @Autowired
    DictionaryDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    private ExamTimeMapper examTimeMapper = new ExamTimeMapper();
    private ExamConditionMapper examConditionMapper = new ExamConditionMapper();
    private CommitteeMemberPositionMapper committeeMemberPositionMapper = new CommitteeMemberPositionMapper();

    private static final String getExamTimeSql = "select * from rf_exam_time where id=:id";
    private static final String getExamConditionsSql = "select * from rf_exam_condition  order by id";
    private static final String getCMPositionsSql = "select * from rf_examinator_position order by id";


    @Override
    public List<ExamCondition> getExamConditions() {
        List<ExamCondition> resultExamCondition = (List<ExamCondition>) getJdbcTemplate().query(getExamConditionsSql,
                examConditionMapper);
        return resultExamCondition;
    }

    @Override
    public List<CMPosition> getCMPositions() {
        List<CMPosition> resultTrainName = (List<CMPosition>) getJdbcTemplate().query(getCMPositionsSql,
                committeeMemberPositionMapper);
        return resultTrainName;
    }

    @Override
    public ExamTime getExamTime(long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        ExamTime resultExamTime = parameterJdbcTemplate.queryForObject(getExamTimeSql,
                params, examTimeMapper);
        return resultExamTime;
    }
}
