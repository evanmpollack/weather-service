package com.evanm.weather.geocoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.HereProperties;
import com.evanm.weather.geocoding.dto.Point;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeocodingService {
    @Autowired
    private HereProperties hereProperties;
    
    @Autowired
    private ObjectMapper mapper;
    
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

        // Look into custom parser
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);;
            if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }
            
            JsonNode position = mapper.readTree(res.getBody())
                                    .path("items")
                                    .elements()
                                    .next()
                                    .path("position");
            if(position.isMissingNode()) { throw new Exception(service + " API response cannot be parsed"); }

            return mapper.treeToValue(position, Point.class);
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String decode(Point p) {
        // Here URL builder class needed
        // StringBuilder instead of manual string construction?
        String service = HereApiService.REVERSE_GEOCODING.service;
        String protocol = "https";
        String host = service + "." + hereProperties.getBaseOrigin();
        String path = hereProperties.getApiVersion() + "/" + service;
        // find way to abstract query param names
        String params = "at=" + p + "&apiKey=" + hereProperties.getApiKey();
        String url = protocol + "://" + host + "/" + path + "?" + params;

        // Look into custom parser
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
            if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }

            JsonNode address = mapper.readTree(res.getBody())
                                    .path("items")
                                    .elements()
                                    .next()
                                    .path("address");
            if(address.isMissingNode()) { throw new Exception(service + " API response cannot be parsed"); }
    
            return address.toPrettyString();
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
