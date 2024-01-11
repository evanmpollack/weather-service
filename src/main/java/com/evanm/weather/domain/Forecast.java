package com.evanm.weather.domain;

import java.util.List;

public class Forecast {
    private List<ForecastPeriod> forecast;
    // private Gridpoint gridpoint;

    public Forecast(List<ForecastPeriod> forecast) {
        this.forecast = forecast;
        // this.gridpoint = gridpoint;
    }

    public List<ForecastPeriod> getForecast() {
        return forecast;
    }

    // public Gridpoint getGridpoint() {
    //     return gridpoint;
    // }
}
