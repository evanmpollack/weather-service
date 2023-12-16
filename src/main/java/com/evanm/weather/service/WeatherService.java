package com.evanm.weather.service;

import org.springframework.stereotype.Service;

import com.evanm.weather.dto.WeatherDTO;

@Service
public interface WeatherService {
    WeatherDTO getWeatherByCoordinate(String coordinate);
    WeatherDTO getWeatherByAddress(String address);
}
