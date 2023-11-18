package com.evanm.weather.forecast.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evanm.weather.forecast.service.ForecastService;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    private ForecastService forecastService;

    public ForecastController(ForecastService service) {
        forecastService = service;
    }


    @GetMapping(produces = "text/plain")
    public String home() {
        return "Forecast Home";
    }

    @GetMapping(value = "/pointdata/{address}", produces = "application/json")
    public String getPointData(@PathVariable String address) {
        return forecastService.getForecastByAddressString(address);
    }
}