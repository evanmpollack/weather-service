package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;

public interface GeocodingService {
    Coordinate encode(String address);
    // String or RelativeLocation?
    // Two doubles or Coordinate?
    String decode(double latitude, double longitude);
}
