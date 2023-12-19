package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

@Service
public class HereGeocodingService implements GeocodingService {

    @Override
    public Coordinate encode(String address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encode'");
    }

    @Override
    public String decode(double latitude, double longitude) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }
    
}
