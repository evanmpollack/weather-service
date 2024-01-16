package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.domain.Forecast;
import com.evanm.weather.domain.Gridpoint;
import com.evanm.weather.dto.NWSForecastResponse;
import com.evanm.weather.dto.NWSPointResponse;
import com.evanm.weather.exceptions.ForecastServiceException;
import com.evanm.weather.uri.NWSUriComponentsDirector;
import com.evanm.weather.uri.UriComponentsDirectorFactory;

@Service
public class NWSForecastService implements ForecastService {
    private RestTemplate restTemplate;
    // Not what I want
    private NWSUriComponentsDirector uriComponentsDirector;

    public NWSForecastService(RestTemplate restTemplate, UriComponentsDirectorFactory directorFactory) {
        this.restTemplate = restTemplate;
        // Defeats purpose of factory
        this.uriComponentsDirector = (NWSUriComponentsDirector) directorFactory.createDirector("nws");
    }

    @Override
    public Forecast getForecast(Coordinate coordinate, String format) throws ForecastServiceException {
        // Check redis for gridpoint where coordinate within
        
        ResponseEntity<NWSPointResponse> pointRes = restTemplate.getForEntity(uriComponentsDirector.getPointUri(coordinate), NWSPointResponse.class);
        if (pointRes.getStatusCode().isError()) {
            throw new ForecastServiceException("Failed to get NWS Gridpoint from " + coordinate.toString() + ".");
        }
        NWSPointResponse nwsPointResponse = pointRes.getBody();
        
        String forecastUri = (format.equals("hourly")) ? nwsPointResponse.getHourlyForecastUri() : nwsPointResponse.getDailyForecastUri();
        
        ResponseEntity<NWSForecastResponse> forecastRes = restTemplate.getForEntity(forecastUri, NWSForecastResponse.class);
        if (forecastRes.getStatusCode().isError()) {
            throw new ForecastServiceException("Failed to get forecast from " + forecastUri + ".");
        }
        NWSForecastResponse nwsForecastResponse = forecastRes.getBody();
        Forecast forecast = new Forecast(nwsForecastResponse.getForecastPeriods());
        
        // Build & Store Gridpoint
        Gridpoint gridpoint = new Gridpoint();
        gridpoint.setOffice(nwsPointResponse.getOffice());
        gridpoint.setGridX(nwsPointResponse.getGridX());
        gridpoint.setGridY(nwsPointResponse.getGridY());
        gridpoint.setDailyForecastUri(nwsPointResponse.getDailyForecastUri());
        gridpoint.setHourlyForecastUri(nwsPointResponse.getHourlyForecastUri());
        
        if (format.equals("hourly")) {
            gridpoint.setHourlyForecast(forecast);
        } else {
            gridpoint.setDailyForecast(forecast);
        }

        gridpoint.setBounds(nwsForecastResponse.getBounds());

        return forecast;
    }
    
}
