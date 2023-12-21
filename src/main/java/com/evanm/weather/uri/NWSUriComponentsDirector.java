package com.evanm.weather.uri;

public class NWSUriComponentsDirector implements UriComponentsDirector {
    private String host;
    private String userAgent;

    public NWSUriComponentsDirector(String host, String userAgent) {
        this.host = host;
        this.userAgent = userAgent;
    }
}
