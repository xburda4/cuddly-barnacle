package com.burda.barnacle.interfaces;

import com.burda.barnacle.types.ImportantData;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public interface IDataController {

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ImportantData saveDataToDatabase(@RequestBody ImportantData inputData);

    @RequestMapping(value = "/get", produces =  MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    ImportantData getDataFromDatabase(@RequestBody JsonNode jsonNode);
}
