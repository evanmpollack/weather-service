package com.evanm.weather.dto;

public class NWSPointResponse {
    private String office;
    private int gridX;
    private int gridY;
    private String dailyForecastUri;
    private String hourlyForecastUri;

    public NWSPointResponse(String office, int gridX, int gridY, String dailyForecastUri, String hourlyForecastUri) {
        this.office = office;
        this.gridX = gridX;
        this.gridY = gridY;
        this.dailyForecastUri = dailyForecastUri;
        this.hourlyForecastUri = hourlyForecastUri;
    }

    public String getOffice() {
        return office;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public String getDailyForecastUri() {
        return dailyForecastUri;
    }

    public String getHourlyForecastUri() {
        return hourlyForecastUri;
    }
}
