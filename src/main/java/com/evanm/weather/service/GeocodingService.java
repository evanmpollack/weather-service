package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;

import com.evanm.weather.domain.RelativeLocation;
import com.evanm.weather.exceptions.GeocodingServiceException;

public interface GeocodingService {
    RelativeLocation encode(String address) throws GeocodingServiceException;
    RelativeLocation decode(Coordinate coordinate) throws GeocodingServiceException;
}
