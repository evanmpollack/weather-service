package com.evanm.weather.service;

import org.locationtech.jts.geom.Coordinate;

import com.evanm.weather.domain.Alerts;
import com.evanm.weather.exceptions.AlertServiceException;

public interface AlertService {
    Alerts getAllAlerts(Coordinate coordinate) throws AlertServiceException;
}
