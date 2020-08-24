package com.burda.barnacle;

import com.burda.barnacle.types.ImportantData;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);


        SimpleModule module =
                new SimpleModule("ImpDataDeserializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ImportantData.class, new ImpDataDeserializer());
        mapper.registerModule(module);

        return mapper;
    }
}
