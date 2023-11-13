package com.evanm.weather.geocoding.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PointDeserializer.class)
public class Point {
    private double latitude;
    private double longitude;

    public Point(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // "lat,lng" --> needed?
    // currently used bind path parameter to Point
    public Point(String pointString) {
        String[] pointParts = pointString.split(",");
        this.latitude = Double.valueOf(pointParts[0]);
        this.longitude = Double.valueOf(pointParts[1]);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}