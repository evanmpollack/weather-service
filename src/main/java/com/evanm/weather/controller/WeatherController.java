package com.evanm.weather.controller;

import com.evanm.weather.dto.WeatherDTO;
import com.evanm.weather.service.WeatherService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    
    // Query params or path parameters?

    // String or CoordinateDTO?
    // Query Params for strategy
    @GetMapping("/coordinate/{coordinate}")
    public WeatherDTO getWeatherByCoordinate(@PathVariable String coordinate) {
        return weatherService.getWeatherByCoordinate(coordinate);
    }

    @GetMapping("/address/{address}")
    public WeatherDTO getWeatherByAddress(@PathVariable String address) {
        return weatherService.getWeatherByAddress(address);
    }
}
