package com.burda.barnacle.types;

import lombok.Value;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Base64;
import java.util.Calendar;

@Value
public class ImportantData {
    String id;
    String binaryData;
    Timestamp expiration;

    public ImportantData(String id, String binaryData) throws IllegalArgumentException {
        this.id = id;
        Base64.getDecoder().decode(binaryData);
        this.binaryData =  binaryData;
        Timestamp ts = Timestamp.from(Instant.now());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, 7);
        expiration = new Timestamp(cal.getTime().getTime());
    }
}
