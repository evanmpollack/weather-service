package com.evanm.weather.domain;

import java.time.ZonedDateTime;

public class ForecastPeriod {
    private int number;
    private String name;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private boolean isDaytime;
    private Temperature temperature;
    private Precipitation precipitation;
    private Wind wind;
    private Dewpoint dewpoint;
    private Humidity humidity;
    private String icon;
    private String shortForecast;
    private String detailedForecast;

    public ForecastPeriod(int number, String name, ZonedDateTime start, ZonedDateTime end, boolean isDaytime, Temperature temperature, Precipitation precipitation, Wind wind, Dewpoint dewpoint, Humidity humidity, String icon, String shortForecast, String detailedForecast) {
        this.number = number;
        this.name = name;
        this.start = start;
        this.end = end;
        this.isDaytime = isDaytime;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.wind = wind;
        this.dewpoint = dewpoint;
        this.humidity = humidity;
        this.icon = icon;
        this.shortForecast = shortForecast;
        this.detailedForecast = detailedForecast;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public boolean isDaytime() {
        return isDaytime;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public Wind getWind() {
        return wind;
    }

    public Dewpoint getDewpoint() {
        return dewpoint;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }
}