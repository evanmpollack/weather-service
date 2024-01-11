package com.evanm.weather.domain;

public class Precipitation {
    private double value;
    private String unit;

    public Precipitation(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
