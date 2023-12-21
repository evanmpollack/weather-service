package com.evanm.weather.uri;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UriComponentsDirectorFactory {
    private Environment environment;

    public UriComponentsDirectorFactory(Environment environment) {
        this.environment = environment;
    }

    public UriComponentsDirector createDirector(String type) {
        UriComponentsDirector director;
        
        switch (type.toLowerCase()) {
            case "nws":
                director = createNWSUriComponentsDirector();
                break;
            case "here":
                director = createHereUriDirector();
                break;
            default:
                throw new IllegalArgumentException(type + " does not correspond to a concrete UriComponentsDirector implementation");
        }

        return director;
    }

    private HereUriComponentsDirector createHereUriDirector() {
        String apiKey = environment.getProperty("here.apiKey");
        String apiVersion = environment.getProperty("here.apiVersion");
        String baseHost = environment.getProperty("here.baseHost");

        return new HereUriComponentsDirector(apiKey, apiVersion, baseHost);
    }

    private NWSUriComponentsDirector createNWSUriComponentsDirector() {
        String host = environment.getProperty("nws.host");
        String userAgent = environment.getProperty("nws.userAgent");
        
        return new NWSUriComponentsDirector(host, userAgent);
    }
}
