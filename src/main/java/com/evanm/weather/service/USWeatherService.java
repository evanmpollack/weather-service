package com.evanm.weather.service;

// import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import com.evanm.weather.domain.RelativeLocation;
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
    public WeatherDTO getWeatherByAddress(String address, String format) {
        RelativeLocation coordinateLocation = geocodingService.encode(address);
        
        
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WeatherDTO getWeatherByCoordinate(String coordinate, String format) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
