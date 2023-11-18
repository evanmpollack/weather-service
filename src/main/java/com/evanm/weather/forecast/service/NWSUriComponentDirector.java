package com.evanm.weather.forecast.service;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.evanm.weather.NWSProperties;
import com.evanm.weather.geocoding.dto.Point;

@Component
public class NWSUriComponentDirector {
    private UriComponentsBuilder builder;

    public NWSUriComponentDirector(NWSProperties properties) {
        builder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(properties.getHost());
    }

    public UriComponents getPointUri(Point point) {
        return builder.pathSegment("points")
            .pathSegment(point.toString())
            .build();
    }
}