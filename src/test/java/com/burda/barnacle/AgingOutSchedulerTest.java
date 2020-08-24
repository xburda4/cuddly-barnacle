package com.burda.barnacle;

import com.burda.barnacle.types.ImportantData;
import com.burda.barnacle.types.ImportantDataRowMapper;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class AgingOutSchedulerTest {

    @Mocked
    JdbcTemplate jdbcTemplate;

    @Test
    public void testDeleteExpiredData() {
        Timestamp ts = Timestamp.from(Instant.now());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, 80);
        Timestamp pastExpiration = new Timestamp(cal.getTime().getTime());

        List<ImportantData> testData = new LinkedList<>();
        testData.add(new ImportantData("key1","ZWdn", Timestamp.from(Instant.now())));
        testData.add(new ImportantData("key2","ZWdn",pastExpiration));

        new Expectations() {{
            jdbcTemplate.query(anyString,(ImportantDataRowMapper) any);
            result = testData;

            jdbcTemplate.execute(anyString);
        }};

        AgingOutScheduler aos = new AgingOutScheduler();
        aos.checkDBForExpiredData();
    }

    @Test
    public void testNoDataToDelete() {
        List<ImportantData> testData = new LinkedList<>();
        testData.add(new ImportantData("key1","ZWdn", Timestamp.from(Instant.now())));

        new Expectations() {{
            jdbcTemplate.query(anyString,(ImportantDataRowMapper) any);
            result = testData;
        }};

        AgingOutScheduler aos = new AgingOutScheduler();
        aos.checkDBForExpiredData();
    }
}
