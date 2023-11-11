package com.evanm.weather;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "here")
public class HereProperties {
    private String apiKey;
    private String apiVersion;
    private String baseOrigin;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getBaseOrigin() {
        return baseOrigin;
    }

    public void setBaseOrigin(String baseOrigin) {
        this.baseOrigin = baseOrigin;
    }
}
