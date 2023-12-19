package com.evanm.weather.service;

import com.evanm.weather.dto.WeatherDTO;

public interface WeatherService {
    WeatherDTO getWeatherByCoordinate(String coordinate);
    WeatherDTO getWeatherByAddress(String address);
}
