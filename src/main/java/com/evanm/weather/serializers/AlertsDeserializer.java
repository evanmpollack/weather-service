package com.evanm.weather.serializers;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.evanm.weather.domain.Alert;
import com.evanm.weather.domain.Alerts;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class AlertsDeserializer extends StdDeserializer<Alerts> {
    public AlertsDeserializer() {
        this(null);
    }

    public AlertsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Alerts deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        JsonNode features = root.required("features");

        List<Alert> alerts = new ArrayList<>();
        if (!features.isEmpty()) {
            for (JsonNode feature : features) {
                JsonNode props = feature.required("properties");
                
                String event = props.path("event").asText();
                String severity = props.path("severity").asText();
                String urgency = props.path("urgency").asText();
                String certainty = props.path("certainty").asText();
                String headline = props.path("headline").asText();
                String description = props.path("description").asText();

                JsonNode effective = props.path("effective");
                JsonNode ends = props.path("ends");
                ZonedDateTime start = (effective.isMissingNode()) ? ZonedDateTime.parse(effective.asText()) : null;
                ZonedDateTime end = (ends.isMissingNode()) ? ZonedDateTime.parse(ends.asText()) : null;

                Alert alert = new Alert(event, severity, urgency, certainty, headline, description, start, end);
                alerts.add(alert);
            }
        }

        return new Alerts(alerts);
    }
}
