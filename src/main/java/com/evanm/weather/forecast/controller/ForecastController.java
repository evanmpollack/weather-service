package com.evanm.weather.forecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evanm.weather.geocoding.dto.Address;
import com.evanm.weather.geocoding.dto.Point;
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

    @GetMapping(value = "/encode/{address}", produces = "application/json")
    public Point getPoint(@PathVariable String address) {
        try {
            return geocoder.encode(address);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/decode/{point}", produces = "application/json")
    public Address getAddress(@PathVariable Point point) {
        try {
            return geocoder.decode(point);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}