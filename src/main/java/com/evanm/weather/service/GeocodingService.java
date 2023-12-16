package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

@Service
public interface GeocodingService {
    Coordinate encode(String address);
    // String or RelativeLocation?
    // Two doubles or Coordinate?
    String decode(double latitude, double longitude);
}
