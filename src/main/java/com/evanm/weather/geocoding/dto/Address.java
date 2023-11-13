package com.evanm.weather.geocoding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = AddressDeserializer.class)
public class Address {
    private String city;
    private String state;
    private String stateCode;
    private int postalCode;

    public Address(String city, String state, String stateCode, int postalCode) {
        this.city = city;
        this.state = state;
        this.stateCode = stateCode;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public int getPostalCode() {
        return postalCode;
    }
}
