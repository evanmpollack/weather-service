package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.domain.Forecast;
import com.evanm.weather.exceptions.ForecastServiceException;
import com.evanm.weather.uri.NWSUriComponentsDirector;
import com.evanm.weather.uri.UriComponentsDirectorFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NWSForecastService implements ForecastService {
    private RestTemplate restTemplate;
    // Not what I want
    private NWSUriComponentsDirector uriComponentsDirector;
    private ObjectMapper objectMapper;

    public NWSForecastService(RestTemplate restTemplate, UriComponentsDirectorFactory directorFactory, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        // Defeats purpose of factory
        this.uriComponentsDirector = (NWSUriComponentsDirector) directorFactory.createDirector("nws");
        this.objectMapper = objectMapper;
    }

    @Override
    public String getForecast(Coordinate coordinate, String format) throws ForecastServiceException, JsonMappingException, RestClientException, JsonProcessingException, IllegalArgumentException {
        // Check redis for gridpoint where coordinate within
        
        ResponseEntity<String> pointRes = restTemplate.getForEntity(uriComponentsDirector.getPointUri(coordinate), String.class);
        if (pointRes.getStatusCode().isError()) {
            throw new ForecastServiceException("Failed to get NWS Gridpoint from " + coordinate.toString() + ".");
        }

        JsonNode props = objectMapper.readTree(pointRes.getBody()).required("properties");
        
        String forecastUri;
        if (format.equalsIgnoreCase("hourly")) {
            forecastUri = props.required("forecastHourly").textValue();
        } else {
            forecastUri = props.required("forecast").textValue();
        }
        
        ResponseEntity<String> forecastRes = restTemplate.getForEntity(forecastUri, String.class);
        
        if (forecastRes.getStatusCode().isError()) {
            throw new ForecastServiceException("Failed to get forecast from " + forecastUri + ".");
        }

        return forecastRes.getBody();
    }
    
}
