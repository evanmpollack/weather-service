package com.evanm.weather.domain;

import java.io.Serializable;

import org.locationtech.jts.geom.Polygon;

public class Gridpoint implements Serializable {
    private String office;
    private int gridX;
    private int gridY;
    private String dailyForecastUri;
    private String hourlyForecastUri; 
    private Forecast dailyForecast;
    private Forecast hourlyForecast;
    private Polygon bounds;

    public Gridpoint() {}

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public String getDailyForecastUri() {
        return dailyForecastUri;
    }

    public void setDailyForecastUri(String dailyForecastUri) {
        this.dailyForecastUri = dailyForecastUri;
    }

    public String getHourlyForecastUri() {
        return hourlyForecastUri;
    }

    public void setHourlyForecastUri(String hourlyForecastUri) {
        this.hourlyForecastUri = hourlyForecastUri;
    }

    public Forecast getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(Forecast dailyForecast) {
        this.dailyForecast = dailyForecast;
    }

    public Forecast getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(Forecast hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }
}
