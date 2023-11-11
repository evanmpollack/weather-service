package com.evanm.weather.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evanm.weather.geocoding.domain.Point;
import com.evanm.weather.geocoding.service.GeocodingService;

@RestController
@RequestMapping("/forecast")
public class ForecastController {
    @Autowired
    private GeocodingService geocoder;


    @GetMapping(produces = "text/plain")
    public String home() {
        return "Forecast Home";
    }
    
    @GetMapping(value = "/hello", produces = "text/plain")
    public String getForecast() {
        return "Hello";
    }

    @GetMapping(value = "/encode/{address}", produces = "application/json")
    public Point getPoint(@PathVariable String address) {
        return geocoder.encode(address);
    }

    @GetMapping(value = "/decode/{point}", produces = "application/json")
    public String getAddress(@PathVariable Point point) {
        return geocoder.decode(point);
    }
}