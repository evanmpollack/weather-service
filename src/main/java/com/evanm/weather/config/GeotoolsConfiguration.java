package com.evanm.weather.config;

import org.geotools.geometry.jts.WKTReader2;
import org.geotools.geometry.jts.WKTWriter2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeotoolsConfiguration {
    // Is this what I want?

    @Bean
    public WKTReader2 wktReader2() {
        return new WKTReader2();
    }

    @Bean
    public WKTWriter2 wktWriter2() {
        return new WKTWriter2();
    }
}
