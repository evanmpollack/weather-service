package com.evanm.weather.domain;

import java.util.ArrayList;
import java.util.List;

public class Alerts {
    private List<Alert> alerts;
    
    public Alerts() {
        alerts = new ArrayList<>();
    }

    public void add(Alert alert) {
        alerts.add(alert);
    }

    @Override
    public String toString() {
        return alerts.toString();
    }
}
