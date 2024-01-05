package com.evanm.weather.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    private Environment environment;

    public RestTemplateConfiguration(Environment environment) {
        this.environment = environment;
    }
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .defaultHeader("User-Agent", environment.getProperty("userAgent"))
            .build();
    }
}
