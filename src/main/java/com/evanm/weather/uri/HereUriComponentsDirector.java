package com.evanm.weather.uri;

import java.util.Collections;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.util.UriComponentsBuilder;

public class HereUriComponentsDirector implements UriComponentsDirector {
    
    // reduce duplicated builder steps
    
    private final UriComponentsBuilder baseBuilder;
    
    public HereUriComponentsDirector(String apiKey, String apiVersion, String baseHost) {
        baseBuilder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("{service}." + baseHost)
            .pathSegment(apiVersion)
            .pathSegment("{service}")
            .queryParam("apiKey", apiKey);
    }

    public String getEncodeUri(String address) {
        return baseBuilder.cloneBuilder()
            .queryParam("q", address)
            .queryParam("in", "countryCode:USA")
            .buildAndExpand(Collections.singletonMap("service", "geocode"))
            .toUriString();
    }

    public String getDecodeUri(Coordinate coordinate) {
        String stringLat = String.valueOf(coordinate.getX());
        String stringLng = String.valueOf(coordinate.getY());
        
        return baseBuilder.cloneBuilder()
            .queryParam("at", String.join(",", stringLat, stringLng))
            .buildAndExpand(Collections.singletonMap("service", "revgeocode"))
            .toUriString();
    }
}
