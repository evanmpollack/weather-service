package com.evanm.weather.geocoding.service;

public enum HereApiService {
    GEOCODING("geocode"),
    REVERSE_GEOCODING("revgeocode");

    public final String service;

    private HereApiService(String service) {
        this.service = service;
    }
}