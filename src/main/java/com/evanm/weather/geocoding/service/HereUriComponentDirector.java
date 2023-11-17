package com.evanm.weather.geocoding.service;

import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.evanm.weather.HereProperties;
import com.evanm.weather.geocoding.dto.Point;

@Component
public class HereUriComponentDirector {
    private UriComponentsBuilder builder;

    public HereUriComponentDirector(HereProperties properties) {
        builder = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("{service}." + properties.getBaseHost())
            .pathSegment(properties.getApiVersion())
            .pathSegment("{service}")
            .queryParam("apiKey", properties.getApiKey());
    }

    public UriComponents getEncodeUri(String address) {
        return builder.replaceQueryParam("q", address)
            .buildAndExpand(Collections.singletonMap("service", HereApiServiceOptions.GEOCODE));
    }

    public UriComponents getDecodeUri(Point point) {
        return builder.replaceQueryParam("at", point)
            .buildAndExpand(Collections.singletonMap("service", HereApiServiceOptions.REV_GEOCODE));
    }
}
