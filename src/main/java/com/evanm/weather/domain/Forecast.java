package com.evanm.weather.domain;

import java.util.List;

public class Forecast {
    private List<ForecastPeriod> forecast;

    public Forecast(List<ForecastPeriod> forecast) {
        this.forecast = forecast;
    }

    public List<ForecastPeriod> getForecast() {
        return forecast;
    }
}
