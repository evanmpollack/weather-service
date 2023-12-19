package com.evanm.weather.serializers;

import java.io.IOException;

import org.locationtech.jts.geom.Coordinate;

import com.evanm.weather.domain.RelativeAddress;
import com.evanm.weather.domain.RelativeLocation;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class RelativeLocationDeserializer extends StdDeserializer<RelativeLocation> {
    public RelativeLocationDeserializer() {
        this(null);
    }

    public RelativeLocationDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RelativeLocation deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        
        JsonNode firstItem = root.required(0);
        JsonNode addressRoot = firstItem.required("address");
        JsonNode positionRoot = firstItem.required("position");

        String state = addressRoot.path("state").asText();
        String city = addressRoot.path("city").asText();
        String district = addressRoot.path("district").asText();

        double latitude = positionRoot.path("lat").asDouble();
        double longitude = positionRoot.path("lng").asDouble();

        return new RelativeLocation(
            new Coordinate(latitude, longitude), 
            new RelativeAddress(district, city, state)
        );
    }
}
