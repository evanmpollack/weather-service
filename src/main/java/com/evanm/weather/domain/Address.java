package com.evanm.weather.domain;

public class Address {
    private String district;
    private String city;
    private String state;

    public Address() {}

    public Address(String district, String city, String state) {
        this.district = district;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(city).append(", ").append(state);

        return (district == null) ? sb.toString() : sb.insert(0, ", ").insert(0, district).toString();
    }
}
