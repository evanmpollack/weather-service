package com.evanm.weather.serializers;

import java.io.IOException;

import com.evanm.weather.domain.Forecast;
import com.evanm.weather.domain.ForecastPeriod;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ForecastSerializer extends StdSerializer<Forecast> {
    public ForecastSerializer() {
        this(null);
    }

    public ForecastSerializer(Class<Forecast> vc) {
        super(vc);
    }

    @Override
    public void serialize(Forecast value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartArray();
        for (ForecastPeriod forecastPeriod : value.getForecast()) {
            gen.writeObject(forecastPeriod);
        }
        gen.writeEndArray();
    }
}
