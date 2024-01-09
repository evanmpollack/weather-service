package com.evanm.weather.uri;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.web.util.UriComponentsBuilder;

public class NWSUriComponentsDirector implements UriComponentsDirector {
    private final UriComponentsBuilder baseBuilder;

    public NWSUriComponentsDirector(String host) {
        baseBuilder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(host);
    }

    /**
     * 1. getPointUri()
     * 2. getAlertByPointUri()
     * 3. getAlertByAreaUri() -- Needed?
     */

    public String getPointUri(Coordinate coordinate) {
        return baseBuilder.cloneBuilder()
            .pathSegment("points")
            .pathSegment("{latitide},{longitude}")
            .buildAndExpand(coordinate.getX(), coordinate.getY())
            .toUriString();
    }
    
    public String getAlertByPointUri(Coordinate coordinate) {
        return baseBuilder.cloneBuilder()
            .pathSegment("alerts")
            .pathSegment("active")
            .queryParam("point", "{latitude},{longitude}")
            .buildAndExpand(coordinate.getX(), coordinate.getY())
            .toUriString();
    }

    public String getAlertByStateUri(String stateCode) {
        return baseBuilder.cloneBuilder()
            .pathSegment("alerts")
            .queryParam("area", "{state}")
            .buildAndExpand(stateCode)
            .toUriString();
    }
}
