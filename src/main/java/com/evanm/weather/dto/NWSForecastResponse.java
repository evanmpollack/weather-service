package com.evanm.weather.dto;

import java.util.List;

import org.locationtech.jts.geom.Polygon;

import com.evanm.weather.domain.ForecastPeriod;

public class NWSForecastResponse {
    private List<ForecastPeriod> forecastPeriods;
    private Polygon bounds;

    public NWSForecastResponse(List<ForecastPeriod> forecastPeriods, Polygon bounds) {
        this.forecastPeriods = forecastPeriods;
        this.bounds = bounds;
    }

    public List<ForecastPeriod> getForecastPeriods() {
        return forecastPeriods;
    }

    public Polygon getBounds() {
        return bounds;
    }
}
