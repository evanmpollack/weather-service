package com.evanm.weather.geocoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.HereProperties;
import com.evanm.weather.geocoding.dto.Address;
import com.evanm.weather.geocoding.dto.Point;

@Service
public class GeocodingService {
    @Autowired
    private HereProperties hereProperties;
    
    private RestTemplate restTemplate;

    public GeocodingService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }
    
    public Point encode(String address) {
        // Here URL builder class needed
        // StringBuilder instead of manual string construction?
        String service = HereApiService.GEOCODING.service;
        String protocol = "https";
        String host = service + "." + hereProperties.getBaseOrigin();
        String path = hereProperties.getApiVersion() + "/" + service;
        // find way to abstract query param names
        String params = "q=" + address + "&apiKey=" + hereProperties.getApiKey();
        String url = protocol + "://" + host + "/" + path + "?" + params;

        try {
            ResponseEntity<Point> res = restTemplate.getForEntity(url, Point.class);
            if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }
            
            return res.getBody();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Address decode(Point p) {
        // Here URL builder class needed
        // StringBuilder instead of manual string construction?
        String service = HereApiService.REVERSE_GEOCODING.service;
        String protocol = "https";
        String host = service + "." + hereProperties.getBaseOrigin();
        String path = hereProperties.getApiVersion() + "/" + service;
        // find way to abstract query param names
        String params = "at=" + p + "&apiKey=" + hereProperties.getApiKey();
        String url = protocol + "://" + host + "/" + path + "?" + params;

        try {
            ResponseEntity<Address> res = restTemplate.getForEntity(url, Address.class);
            if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }
    
            return res.getBody();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
