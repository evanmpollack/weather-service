package com.evanm.weather.domain;

public class Wind {
    private int minSpeed;
    private int maxSpeed;
    private String direction;
    private String unit;

    public Wind(int minSpeed, int maxSpeed, String direction, String unit) {
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.direction = direction;
        this.unit = unit;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getDirection() {
        return direction;
    }

    public String getUnit() {
        return unit;
    }
}
