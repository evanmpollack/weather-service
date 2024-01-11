package com.evanm.weather.serializers;

import java.io.IOException;

import com.evanm.weather.domain.Alert;
import com.evanm.weather.domain.Alerts;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class AlertsSerializer extends StdSerializer<Alerts> {
    public AlertsSerializer() {
        this(null);
    }

    public AlertsSerializer(Class<Alerts> vc) {
        super(vc);
    }

    @Override
    public void serialize(Alerts value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (Alert alert : value.getAlerts()) {
            gen.writeObject(alert);
        }
        gen.writeEndArray();
    }
}
