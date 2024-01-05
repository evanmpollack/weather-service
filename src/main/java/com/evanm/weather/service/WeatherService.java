package com.evanm.weather.service;

// import com.evanm.weather.dto.WeatherDTO;

public interface WeatherService {
    String getWeatherByCoordinate(String coordinate, String format) throws Exception;
    String getWeatherByAddress(String address, String format) throws Exception;
}
