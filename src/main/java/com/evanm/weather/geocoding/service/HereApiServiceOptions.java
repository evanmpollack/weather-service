package com.evanm.weather.geocoding.service;

public enum HereApiServiceOptions {
    GEOCODE,
    REV_GEOCODE;

    @Override
    public String toString() {
        return super.toString()
            .toLowerCase()
            .replaceAll("_", "");
    }
}