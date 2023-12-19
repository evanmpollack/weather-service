package com.evanm.weather.service;

import com.evanm.weather.dto.WeatherDTO;

public interface WeatherService {
    WeatherDTO getWeatherByCoordinate(String coordinate, String format);
    WeatherDTO getWeatherByAddress(String address, String format);
}
