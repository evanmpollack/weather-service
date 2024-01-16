package com.evanm.weather.controller;


import com.evanm.weather.domain.Weather;
import com.evanm.weather.dto.WeatherDTO;
import com.evanm.weather.service.WeatherService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final static String DEFAULT_FORMAT = "daily";
    
    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // String or CoordinateDTO?
    // Query Params for strategy
    @GetMapping("/coordinate/{coordinate}")
    public Weather getWeatherByCoordinate(@PathVariable String coordinate, @RequestParam(required = false, defaultValue = DEFAULT_FORMAT) String format) {
        try {
            return weatherService.getWeatherByCoordinate(coordinate, format);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/address/{address}")
    public Weather getWeatherByAddress(@PathVariable String address, @RequestParam(required = false, defaultValue = DEFAULT_FORMAT) String format) {
        try {
            return weatherService.getWeatherByAddress(address, format);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
