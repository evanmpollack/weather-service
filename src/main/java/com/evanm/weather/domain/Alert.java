package com.evanm.weather.domain;

import java.time.ZonedDateTime;

public class Alert {
    private String event;
    private String severity;
    private String urgency;
    private String certainty;
    private String headline;
    private String description;
    private ZonedDateTime start;
    private ZonedDateTime end;

    public Alert(String event, String severity, String urgency, String certainty, String headline, String description, ZonedDateTime start, ZonedDateTime end) {
        this.event = event;
        this.severity = severity;
        this.urgency = urgency;
        this.certainty = certainty;
        this.headline = headline;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public String getEvent() {
        return event;
    }
    
    public String getSeverity() {
        return severity;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getCertainty() {
        return certainty;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return headline;
    }
}
