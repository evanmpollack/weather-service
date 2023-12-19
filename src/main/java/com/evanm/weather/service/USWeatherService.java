package com.evanm.weather.service;

import org.springframework.stereotype.Service;

import com.evanm.weather.dto.WeatherDTO;

@Service
public class USWeatherService implements WeatherService {
    private GeocodingService geocodingService;
    private ForecastService forecastService;
    private AlertService alertService;

    public USWeatherService(GeocodingService geocodingService, ForecastService forecastService, AlertService alertService) {
        this.geocodingService = geocodingService;
        this.forecastService = forecastService;
        this.alertService = alertService;
    }

    @Override
    public WeatherDTO getWeatherByAddress(String address) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeatherDTO getWeatherByCoordinate(String coordinate) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
