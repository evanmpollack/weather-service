package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;

import com.evanm.weather.domain.Address;
import com.evanm.weather.exceptions.GeocodingServiceException;

public interface GeocodingService {
    // RelativeLocation encode(String address) throws GeocodingServiceException;
    // RelativeLocation decode(Coordinate coordinate) throws GeocodingServiceException;
    Coordinate encode(String address) throws GeocodingServiceException;
    Address decode(Coordinate coordinate) throws GeocodingServiceException;
}
