package com.evanm.weather.converters;

public interface DTOConverter<D, E> {
    D toDTO(E entity);
    E toEntity(D dto);
}
