package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import com.evanm.weather.domain.Address;
import com.evanm.weather.domain.Alerts;
import com.evanm.weather.domain.Forecast;
import com.evanm.weather.domain.Weather;

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
    public Weather getWeatherByCoordinate(String coordinateString, String format) throws Exception {
        // Formatting decimals shouldn't be in this service
        // CoordinateDTO --> Coordinate, formatting in the process?

        // Sanitize and create coordinate
        String[] coordinateParts = coordinateString.split(",");
        double latitude = Double.valueOf(String.format("%.4f", Double.valueOf(coordinateParts[0])));
        double longitude = Double.valueOf(String.format("%.4f", Double.valueOf(coordinateParts[1])));

        Coordinate coordinate = new Coordinate(latitude, longitude);

        // Decode coordinate
        Address address = geocodingService.decode(coordinate);
        
        // Get 7 day forecast
        Forecast forecast = forecastService.getForecast(coordinate, format);
        
        // Get active alerts
        Alerts activeAlerts = alertService.getAllAlerts(coordinate);

        // Aggregate
        Weather weather = new Weather(address, forecast, activeAlerts);

        return weather;
    }

    @Override
    public Weather getWeatherByAddress(String address, String format) throws Exception {
        // Location aggregate needed? Inputted address doesn't necessarily provide information for webpage
        // Location interface needed instead? should weather know what location data they hold?
        
        Coordinate coordinate = geocodingService.encode(address);
        Forecast forecast = forecastService.getForecast(coordinate, format);
        Alerts alerts = alertService.getAllAlerts(coordinate);

        return new Weather(null, forecast, alerts);
    }
    
}
