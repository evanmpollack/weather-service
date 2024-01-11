package com.evanm.weather.domain;

public class Temperature {
    private double value;
    private char unit;
    private String trend;

    public Temperature(double value, char unit, String trend) {
        this.value = value;
        this.unit = unit;
        this.trend = trend;
    }

    public double getValue() {
        return value;
    }

    public char getUnit() {
        return unit;
    }

    public String getTrend() {
        return trend;
    }
}
