package com.evanm.weather.config;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evanm.weather.domain.Address;
import com.evanm.weather.domain.Alerts;
import com.evanm.weather.domain.Forecast;
import com.evanm.weather.serializers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        
        // Module registration
        mapper.registerModule(serializerModule());
        mapper.registerModule(new JavaTimeModule());
        
        // Feature configuration
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        return mapper;
    }

    private SimpleModule serializerModule() {
        SimpleModule serializerModule = new SimpleModule();
        
        // Serializers
        serializerModule.addSerializer(Alerts.class, new AlertsSerializer());
        serializerModule.addSerializer(Forecast.class, new ForecastSerializer());

        // Deserializers
        serializerModule.addDeserializer(Address.class, new AddressDeserializer());
        serializerModule.addDeserializer(Coordinate.class, new CoordinateDeserializer());
        serializerModule.addDeserializer(Alerts.class, new AlertsDeserializer());
        serializerModule.addDeserializer(Forecast.class, new ForecastDeserializer());

        return serializerModule;
    }
}
