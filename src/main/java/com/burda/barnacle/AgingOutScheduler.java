package com.burda.barnacle;

import com.burda.barnacle.types.ImportantData;
import com.burda.barnacle.types.ImportantDataRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class AgingOutScheduler {
    private static final Logger log = LoggerFactory.getLogger(AgingOutScheduler.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_EXPIRED_ROWS = "select * from binaryDataTable;";
    private static final String DELETE_ROW = "delete from binaryDataTable where id = %s;";

    @Scheduled(cron = "0 0 */1 * * ?")
    public void checkDBForExpiredData() {
        log.info("Delete all expired rows");
        List<ImportantData> listOfData = jdbcTemplate.query(GET_EXPIRED_ROWS,new ImportantDataRowMapper());
        for (ImportantData impData : listOfData) {
            if(impData.getExpiration().after(Timestamp.from(Instant.now()))) {
                log.info(String.format("Deleted %s from DB", impData.getId()));
                jdbcTemplate.execute(String.format(DELETE_ROW, impData.getId()));
            }
        }
    }
}
