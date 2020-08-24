package com.burda.barnacle.types;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImportantDataRowMapper implements RowMapper<ImportantData> {

    @Override
    public ImportantData mapRow(ResultSet rs, int rowNum) throws SQLException {
        ImportantData impData = new ImportantData(
                rs.getString("id"), rs.getString("binaryData"), rs.getTimestamp("expiration")
        );
        return impData;
    }
}
