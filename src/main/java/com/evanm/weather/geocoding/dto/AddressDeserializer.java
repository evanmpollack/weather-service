package com.evanm.weather.geocoding.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AddressDeserializer extends StdDeserializer<Address> {
    public AddressDeserializer() {
        this(null);
    }
    
    protected AddressDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Address deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode address = root.path("items")
                                .path(0)
                                .path("address");

        // Handle parsing exceptions

        String city = address.get("city").asText();
        String state = address.get("state").asText();
        String stateCode = address.get("stateCode").asText();
        
        String postalString = address.get("postalCode").asText().split("-")[0];
        int postalCode = Integer.valueOf(postalString);

        return new Address(city, state, stateCode, postalCode);
    }
    
}
