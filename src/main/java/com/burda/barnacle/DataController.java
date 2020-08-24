package com.burda.barnacle;

import com.burda.barnacle.interfaces.IDataController;
import com.burda.barnacle.types.ImportantData;
import com.burda.barnacle.types.ImportantDataRowMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DataController implements IDataController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SAVE_TO_DB = "insert into binaryDataTable (id, binaryData, expiration) values (?,?,?);";
    private final static String GET_FROM_DB = "select * from binaryDataTable where id = '%s';";

    @Override
    public ImportantData saveDataToDatabase(ImportantData inputData) {
        jdbcTemplate.update(SAVE_TO_DB,inputData.getId(),inputData.getBinaryData(), inputData.getExpiration());
        return inputData;
    }

    @Override
    public ImportantData getDataFromDatabase(JsonNode jsonNode) {
        String key = jsonNode.get("key").asText();
        String query = String.format(GET_FROM_DB, key);
        ImportantData importantData = jdbcTemplate.queryForObject(query, new ImportantDataRowMapper());
        return importantData;
    }
}
