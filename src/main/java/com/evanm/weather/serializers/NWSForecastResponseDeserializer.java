package com.evanm.weather.serializers;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.geotools.data.geojson.GeoJSONReader;
import org.locationtech.jts.geom.Polygon;

import com.evanm.weather.domain.Dewpoint;
import com.evanm.weather.domain.ForecastPeriod;
import com.evanm.weather.domain.Humidity;
import com.evanm.weather.domain.Precipitation;
import com.evanm.weather.domain.Temperature;
import com.evanm.weather.domain.Wind;
import com.evanm.weather.dto.NWSForecastResponse;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class NWSForecastResponseDeserializer extends StdDeserializer<NWSForecastResponse> {
    public NWSForecastResponseDeserializer() {
        this(null);
    }

    public NWSForecastResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public NWSForecastResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        JsonNode root = p.getCodec().readTree(p);
        
        JsonNode props = root.required("properties");
        JsonNode forecastPeriodRoot = props.required("periods");
        List<ForecastPeriod> forecastPeriods = parseForecastPeriods(forecastPeriodRoot);
        
        Polygon bounds = parseGeoJson(root);

        return new NWSForecastResponse(forecastPeriods, bounds);
    }

    private List<ForecastPeriod> parseForecastPeriods(JsonNode forecastPeriodRoot) {
        List<ForecastPeriod> forecastPeriods = new ArrayList<>();
        
        if (!forecastPeriodRoot.isEmpty()) {
            for (JsonNode period : forecastPeriodRoot) {
                // Toplevel field creation
                int number = period.path("number").asInt();
                String name = period.path("name").asText();
                ZonedDateTime start = ZonedDateTime.parse(period.path("startTime").asText());
                ZonedDateTime end = ZonedDateTime.parse(period.path("endTime").asText());
                boolean isDaytime = period.path("isDaytime").asBoolean();
                String icon = period.path("icon").asText();
                String shortForecast = period.path("shortForecast").asText();
                String detailedForecast = period.path("detailedForecast").asText(null);

                // Nested field creation
                // Temperature construction
                double temperatureValue = period.path("temperature").asDouble();
                char temperatureUnit = period.path("temperatureUnit").asText().charAt(0);
                String temperatureTrend = period.path("temperatureTrend").asText(null);
                Temperature temperature = new Temperature(temperatureValue, temperatureUnit, temperatureTrend);

                // Precipitation construction
                JsonNode precipitationJson = period.path("probabilityOfPrecipitation");
                double precipitationValue = precipitationJson.path("value").asDouble();
                String precipitationUnit = precipitationJson.path("unitCode").asText();
                Precipitation precipitation = new Precipitation(precipitationValue, precipitationUnit);

                // Dewpoint construction
                JsonNode dewpointJson = period.path("dewpoint");
                double dewpointValue = dewpointJson.path("value").asDouble();
                String dewpointUnit = dewpointJson.path("unitCode").asText();
                Dewpoint dewpoint = new Dewpoint(dewpointValue, dewpointUnit);

                // Humidity construction
                JsonNode humidityJson = period.path("relativeHumidity");
                double humidityValue = humidityJson.path("value").asDouble();
                String humidityUnit = humidityJson.path("unitCode").asText();
                Humidity humidity = new Humidity(humidityValue, humidityUnit);

                // Wind construction
                String[] speeds = period.path("windSpeed").asText().split(" ");
                int minWindSpeed;
                int maxWindSpeed;
                try {
                    minWindSpeed = Integer.valueOf(speeds[0]);
                    maxWindSpeed = Integer.valueOf(speeds[2]);
                } catch (IndexOutOfBoundsException ioobe) {
                    minWindSpeed = maxWindSpeed = Integer.valueOf(speeds[0]);
                }
                String windUnit = speeds[speeds.length - 1];
                String windDirection = period.path("windDirection").asText();
                Wind wind = new Wind(minWindSpeed, maxWindSpeed, windDirection, windUnit);

                // Forecast period construction and addition
                ForecastPeriod forecastPeriod = new ForecastPeriod(number, name, start, end, isDaytime, temperature, precipitation, wind, dewpoint, humidity, icon, shortForecast, detailedForecast);
                forecastPeriods.add(forecastPeriod);
            }
        }

        return forecastPeriods;
    }

    public Polygon parseGeoJson(JsonNode geometryRoot) {
        String geoJson = geometryRoot.required("geometry").toString();
        return (Polygon) GeoJSONReader.parseGeometry(geoJson);
    }
}
