package com.evanm.weather.service;

import org.springframework.stereotype.Service;

import com.evanm.weather.domain.RelativeLocation;

@Service
public class HereGeocodingService implements GeocodingService {

    @Override
    public RelativeLocation encode(String address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encode'");
    }

    @Override
    public RelativeLocation decode(double latitude, double longitude) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decode'");
    }
    
}
