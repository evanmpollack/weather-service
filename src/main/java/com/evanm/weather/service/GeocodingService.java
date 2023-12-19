package com.evanm.weather.service;

import com.evanm.weather.domain.RelativeLocation;

public interface GeocodingService {
    RelativeLocation encode(String address);
    RelativeLocation decode(double latitude, double longitude);
}
