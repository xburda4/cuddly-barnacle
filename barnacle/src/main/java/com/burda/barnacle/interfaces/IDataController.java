package com.burda.barnacle.interfaces;

import com.burda.barnacle.types.ImportantData;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IDataController {

    @PostMapping("/save")
    String saveDataToDatabase(@RequestBody ImportantData inputData);

    @PostMapping("/get")
    String getDataFromDatabase(@RequestBody JsonNode jsonNode);
}
