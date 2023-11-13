package com.evanm.weather.geocoding.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        TreeNode root = p.getCodec().readTree(p);
        TreeNode position = root.path("items")
                                .path(0)
                                .path("position");

        // If missing attributes, throw error
        
        Double latitude = Double.valueOf(position.get("lat").toString());
        Double longitude = Double.valueOf(position.get("lng").toString());
        
        return new Point(latitude, longitude);
    }
    
}
