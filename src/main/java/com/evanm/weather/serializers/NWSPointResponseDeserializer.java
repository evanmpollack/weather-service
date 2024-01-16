package com.evanm.weather.serializers;

import java.io.IOException;

import com.evanm.weather.dto.NWSPointResponse;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class NWSPointResponseDeserializer extends StdDeserializer<NWSPointResponse> {
    public NWSPointResponseDeserializer() {
        this(null);
    }

    public NWSPointResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public NWSPointResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode props = root.required("properties");

        String office = props.path("cwa").asText(null);
        int gridX = props.path("gridX").asInt();
        int gridY = props.path("gridY").asInt();
        String dailyForecastUri = props.path("forecast").asText(null);
        String hourlyForecastUri = props.path("forecastHourly").asText(null);
        
        return new NWSPointResponse(office, gridX, gridY, dailyForecastUri, hourlyForecastUri);
    }
}
