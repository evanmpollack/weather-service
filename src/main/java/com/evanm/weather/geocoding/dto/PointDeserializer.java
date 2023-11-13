package com.evanm.weather.geocoding.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PointDeserializer extends StdDeserializer<Point> {
    public PointDeserializer() {
        this(null);
    }
    
    protected PointDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Point deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode position = root.path("items")
                                .path(0)
                                .path("position");

        // If missing attributes, throw error
        
        Double latitude = position.get("lat").asDouble();
        Double longitude = position.get("lng").asDouble();
        
        return new Point(latitude, longitude);
    }
    
}
