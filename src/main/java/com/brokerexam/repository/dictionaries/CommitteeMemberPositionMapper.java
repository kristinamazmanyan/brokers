package com.brokerexam.repository.dictionaries;

import com.brokerexam.domain.exam.CMPosition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommitteeMemberPositionMapper implements RowMapper<CMPosition> {

    public static final String ID = "id";
    public static final String NAME = "name";



    public CMPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
        CMPosition pos = new CMPosition();
        pos.setName(rs.getString(NAME));
        pos.setId(rs.getInt(ID));

        return pos;

    }

}
