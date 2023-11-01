package com.evanm.weather.forecast.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    @GetMapping(produces = "text/plain")
    public String home() {
        return "Forecast Home";
    }
    
    @GetMapping(value = "/hello", produces = "text/plain")
    public String getForecast() {
        return "Hello";
    }
}