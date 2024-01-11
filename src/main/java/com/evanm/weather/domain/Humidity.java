package com.evanm.weather.domain;

public class Humidity {
    private double value;
    private String unit;

    public Humidity(double value, String unit) {
        this. value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
