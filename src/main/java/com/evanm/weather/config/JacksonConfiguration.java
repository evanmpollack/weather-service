package com.evanm.weather.config;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evanm.weather.domain.Address;
import com.evanm.weather.serializers.AddressDeserializer;
import com.evanm.weather.serializers.CoordinateDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Address.class, new AddressDeserializer());
        module.addDeserializer(Coordinate.class, new CoordinateDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        
        return mapper;
    }
}
