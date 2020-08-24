package com.burda.barnacle;

import com.burda.barnacle.interfaces.IDataController;
import com.burda.barnacle.types.ImportantData;
import com.burda.barnacle.types.ImportantDataRowMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DataController implements IDataController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SAVE_TO_DB = "insert into binaryDataTable (id, binaryData, expiration) values (?,?,?);";
    private final static String GET_FROM_DB = "select * from binaryDataTable where id = '%s';";

    @Override
    public String saveDataToDatabase(ImportantData inputData) {
        jdbcTemplate.update(SAVE_TO_DB,inputData.getId(),inputData.getBinaryData(), inputData.getExpiration());
        return inputData.toString();
    }

    @Override
    public String getDataFromDatabase(JsonNode jsonNode) {
        String key = jsonNode.get("key").asText();
        String query = String.format(GET_FROM_DB, key);
        ImportantData importantData = jdbcTemplate.queryForObject(query, new ImportantDataRowMapper());
        if (importantData == null) {
            return null;
        }
        return importantData.toString();
    }
}
