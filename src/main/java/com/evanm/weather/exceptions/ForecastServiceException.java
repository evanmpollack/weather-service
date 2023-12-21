package com.evanm.weather.exceptions;

public class ForecastServiceException extends Exception {
    public ForecastServiceException(String message) {
        super(message);
    }
}
