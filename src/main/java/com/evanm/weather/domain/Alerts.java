package com.evanm.weather.domain;

import java.util.List;

public class Alerts {
    private List<Alert> alerts;
    
    public Alerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
