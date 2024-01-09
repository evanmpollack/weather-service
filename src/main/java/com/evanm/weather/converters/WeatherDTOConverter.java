package com.evanm.weather.converters;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.evanm.weather.domain.Weather;
import com.evanm.weather.dto.WeatherDTO;

@Component
public class WeatherDTOConverter implements DTOConverter<WeatherDTO, Weather> {
    private ModelMapper modelMapper;
    
    public WeatherDTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WeatherDTO toDTO(Weather entity) {
        return modelMapper.map(entity, WeatherDTO.class); 
    }

    @Override
    public Weather toEntity(WeatherDTO dto) {
        return modelMapper.map(dto, Weather.class);
    }
}
