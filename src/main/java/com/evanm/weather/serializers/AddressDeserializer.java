package com.evanm.weather.serializers;

import java.io.IOException;

import com.evanm.weather.domain.Address;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AddressDeserializer extends StdDeserializer<Address> {
    public AddressDeserializer() {
        this(null);
    }
    
    public AddressDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Address deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode items = root.required("items");
        JsonNode firstItem = items.required(0);
        JsonNode address = firstItem.required("address");
        
        JsonNode districtNode = address.path("district");
        String district = (!districtNode.isMissingNode()) ? districtNode.asText() : null;
        
        String city = address.required("city").asText();
        String state = address.required("state").asText();

        return new Address(district, city, state);
    }
    
}
