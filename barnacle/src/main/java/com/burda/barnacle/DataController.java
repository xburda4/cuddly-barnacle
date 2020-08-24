package com.burda.barnacle;

import com.burda.barnacle.interfaces.IDataController;
import com.burda.barnacle.types.ImportantData;
import com.burda.barnacle.types.ImportantDataRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController implements IDataController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SAVE_TO_DB = "insert into binaryDataTable (id, binaryData, expiration) values (?,?,?);";
    private final static String GET_FROM_DB = "select * from binaryDataTable where id = ?;";

    @Override
    public String saveDataToDatabase(ImportantData inputData) {
        jdbcTemplate.update(SAVE_TO_DB,inputData.getId(),inputData.getBinaryData(), inputData.getExpiration());
        return inputData.toString();
    }

    @Override
    public String getDataFromDatabase(String key) {
        ImportantData importantData = jdbcTemplate.queryForObject(GET_FROM_DB, new Object[]{key} ,new ImportantDataRowMapper());
        return importantData == null ? null : importantData.toString();
    }
}
