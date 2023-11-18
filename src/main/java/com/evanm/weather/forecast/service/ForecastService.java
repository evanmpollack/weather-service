package com.evanm.weather.forecast.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.NWSProperties;
import com.evanm.weather.geocoding.dto.Point;
import com.evanm.weather.geocoding.service.GeocodingService;

@Service
public class ForecastService {
    private RestTemplate restTemplate;
    private NWSUriComponentDirector uriComponentDirector;
    private GeocodingService geocodingService;

    // code smell, service shouldn't know about properties --> is this the best place to store user agent?
    public ForecastService(RestTemplateBuilder builder, NWSUriComponentDirector director, GeocodingService geocoder, NWSProperties properties) {
        restTemplate = builder
            .defaultHeader("User-Agent", properties.getUserAgent())
            .build();
        uriComponentDirector = director;
        geocodingService = geocoder;
    }

    public String getForecastByAddressString(String address) {
        try {
            Point location = geocodingService.encode(address);
            String uri = uriComponentDirector.getPointUri(location).toUriString();
            ResponseEntity<String> res = restTemplate.getForEntity(uri, String.class);
            if (res.getStatusCode().isError()) { throw new Exception("callout to NWS API failed"); }

            return res.getBody();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public void getForecastByPoint(Point point) {
        // TODO: Implement
    }
}
