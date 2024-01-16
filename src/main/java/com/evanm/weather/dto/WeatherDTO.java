package com.evanm.weather.dto;

import java.util.List;

public class WeatherDTO {
    private String address;
    private String forecast;
    private List<String> alerts;

    public WeatherDTO(String address, String forecast, List<String> alerts) {
        this.address = address;
        this.forecast = forecast;
        this.alerts = alerts;
    }

    public String getAddress() {
        return address;
    }

    public String getForecast() {
        return forecast;
    }

    public List<String> getAlerts() {
        return alerts;
    }
}
