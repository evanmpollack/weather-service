package com.evanm.weather.serializers;

import java.io.IOException;

import org.locationtech.jts.geom.Coordinate;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CoordinateDeserializer extends StdDeserializer<Coordinate> {
    public CoordinateDeserializer() {
        this(null);
    }

    public CoordinateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Coordinate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode items = root.required("items");

        JsonNode firstItem = items.required(0);
        JsonNode pos = firstItem.required("position");
        
        double latitude = pos.required("lat").asDouble();
        double longitude = pos.required("lng").asDouble();

        return new Coordinate(latitude, longitude);
    }
}
