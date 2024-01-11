package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.client.RestClientException;

import com.evanm.weather.domain.Forecast;
import com.evanm.weather.exceptions.ForecastServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ForecastService {
    Forecast getForecast(Coordinate coordinate, String format) throws ForecastServiceException, JsonMappingException, RestClientException, JsonProcessingException, IllegalArgumentException;
}
