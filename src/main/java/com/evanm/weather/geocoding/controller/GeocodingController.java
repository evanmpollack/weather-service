package com.evanm.weather.geocoding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evanm.weather.geocoding.dto.Address;
import com.evanm.weather.geocoding.dto.Point;
import com.evanm.weather.geocoding.service.GeocodingService;

/**
 * For demo/testing purposes only. This service will never be exposed over HTTP
 */


@RestController
@RequestMapping("/geocode")
public class GeocodingController {
    private GeocodingService geocodingService;

    public GeocodingController(GeocodingService service) {
        geocodingService = service;
    }
    
    @GetMapping(produces = "text/plain")
    public String home() {
        return "Geocoding Home";
    }
    
    // Deprecated
    @GetMapping(value = "/encode/{address}", produces = "application/json")
    public Point getPoint(@PathVariable String address) {
        try {
            return geocodingService.encode(address);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Deprecated
    @GetMapping(value = "/decode/{point}", produces = "application/json")
    public Address getAddress(@PathVariable Point point) {
        try {
            return geocodingService.decode(point);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
