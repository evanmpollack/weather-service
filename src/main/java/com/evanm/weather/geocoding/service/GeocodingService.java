package com.evanm.weather.geocoding.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evanm.weather.geocoding.dto.Address;
import com.evanm.weather.geocoding.dto.Point;

@Service
public class GeocodingService {
    private RestTemplate restTemplate;
    private HereUriComponentDirector uriComponentDirector;

    public GeocodingService(RestTemplateBuilder builder, HereUriComponentDirector director) {
        restTemplate = builder.build();
        uriComponentDirector = director;
    }
    
    // Specify exception type
    public Point encode(String address) throws Exception {
        String uri = uriComponentDirector.getEncodeUri(address).toUriString();
        ResponseEntity<Point> res = restTemplate.getForEntity(uri, Point.class);
        if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }
        
        return res.getBody();
    }

    // Specify exception type
    public Address decode(Point point) throws Exception {
        String uri = uriComponentDirector.getDecodeUri(point).toUriString();
        ResponseEntity<Address> res = restTemplate.getForEntity(uri, Address.class);
        if (res.getStatusCode().isError()) { throw new Exception("callout to Here API failed"); }

        return res.getBody();
    }
}
