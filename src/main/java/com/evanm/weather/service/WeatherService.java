package com.evanm.weather.service;

import com.evanm.weather.domain.Weather;
import com.evanm.weather.dto.WeatherDTO;

public interface WeatherService {
    Weather getWeatherByCoordinate(String coordinate, String format) throws Exception;
    Weather getWeatherByAddress(String address, String format) throws Exception;
}
