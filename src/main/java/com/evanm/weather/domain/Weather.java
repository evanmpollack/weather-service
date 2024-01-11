package com.evanm.weather.domain;

public class Weather {
    private Address address;
    private Forecast forecast;
    private Alerts alerts;

    public Weather(Address address, Forecast forecast, Alerts alerts) {
        this.address = address;
        this.forecast = forecast;
        this.alerts = alerts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }
}
