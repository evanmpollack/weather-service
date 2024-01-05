package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.domain.Address;
// import com.evanm.weather.domain.RelativeLocation;
import com.evanm.weather.exceptions.GeocodingServiceException;
import com.evanm.weather.uri.HereUriComponentsDirector;
// import com.evanm.weather.uri.UriComponentsDirector;
import com.evanm.weather.uri.UriComponentsDirectorFactory;

@Service
public class HereGeocodingService implements GeocodingService {
    private RestTemplate restTemplate;

    // Not what I want in long term --> Should be generic
    private HereUriComponentsDirector uriComponentsDirector;

    public HereGeocodingService(RestTemplateBuilder restTemplateBuilder, UriComponentsDirectorFactory directorFactory) {
        restTemplate = restTemplateBuilder.build();

        // Defeats purpose of factory
        uriComponentsDirector = (HereUriComponentsDirector) directorFactory.createDirector("here");
    }
    
    @Override
    public Coordinate encode(String address) throws GeocodingServiceException {
        ResponseEntity<Coordinate> res = restTemplate.getForEntity(uriComponentsDirector.getEncodeUri(address), Coordinate.class);
        if (res.getStatusCode().isError()) { 
            throw new GeocodingServiceException("Address, " + address + ", could not be encoded"); 
        }
        
        return res.getBody();
    }

    @Override
    public Address decode(Coordinate coordinate) throws GeocodingServiceException {
        ResponseEntity<Address> res = restTemplate.getForEntity(uriComponentsDirector.getDecodeUri(coordinate), Address.class);
        if (res.getStatusCode().isError()) { 
            throw new GeocodingServiceException("Coordinate, " + coordinate.toString() + ", could not be decoded");
        }
        
        return res.getBody();
    }
    
}
