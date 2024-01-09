package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.domain.Alerts;
import com.evanm.weather.exceptions.AlertServiceException;
import com.evanm.weather.uri.NWSUriComponentsDirector;
import com.evanm.weather.uri.UriComponentsDirectorFactory;

@Service
public class NWSAlertService implements AlertService {
    private RestTemplate restTemplate;

    // Not what I want
    private NWSUriComponentsDirector uriComponentsDirector;
    
    public NWSAlertService(RestTemplate restTemplate, UriComponentsDirectorFactory directorFactory) {
        this.restTemplate = restTemplate;
        uriComponentsDirector = (NWSUriComponentsDirector) directorFactory.createDirector("nws");
    }
    
    @Override
    public Alerts getAllAlerts(Coordinate coordinate) throws AlertServiceException {
        ResponseEntity<Alerts> res = restTemplate.getForEntity(uriComponentsDirector.getAlertByPointUri(coordinate), Alerts.class);
        if (res.getStatusCode().isError()) {
            throw new AlertServiceException("Failed to get alerts by coordinate, " + coordinate + ".");
        }

        return res.getBody();
    }
    
}
