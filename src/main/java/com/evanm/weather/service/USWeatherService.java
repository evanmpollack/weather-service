package com.evanm.weather.service;

import java.text.DecimalFormat;

import org.locationtech.jts.geom.Coordinate;
// import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import com.evanm.weather.domain.Address;
import com.evanm.weather.domain.Forecast;
// import com.evanm.weather.domain.RelativeLocation;
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
    public String getWeatherByCoordinate(String coordinate, String format) throws Exception {
        // Formatting decimals shouldn't be in this service
        // CoordinateDTO --> Coordinate, formatting in the process?

        // Sanitize coordinate
        String[] coordinateParts = coordinate.split(",");
        double latitude = Double.valueOf(String.format("%.4f", Double.valueOf(coordinateParts[0])));
        double longitude = Double.valueOf(String.format("%.4f", Double.valueOf(coordinateParts[1])));

        System.out.println(latitude);

        Coordinate c = new Coordinate(latitude, longitude);

        // Decode coordinate
        Address address = geocodingService.decode(c);

        System.out.println(address);
        
        // Get weather
        return forecastService.getForecast(c, format);

        
        // Forecast forecast = forecastService.getForecast(format);
        // List<Alert> alerts = alertService.getAllAlerts();
        // RelativeLocation addressLocation = geocodingService.decode(coordinate); --> Async?
        // return mapper.toDTO(RelativeLocation, Forecast, Alerts);
    }

    @Override
    public String getWeatherByAddress(String address, String format) throws Exception {
        System.out.println(address);
        // RelativeLocation location = geocodingService.encode(address);
        // String forecast = forecastService.getForecast(location.getCoordinate(), format);
        // List<Alert> alerts = alertService.getAllAlerts();
        // return mapper.toDTO(RelativeLocation, Forecast, Alerts);
        
        
        // TODO Auto-generated method stub
        return null;
    }
    
}
