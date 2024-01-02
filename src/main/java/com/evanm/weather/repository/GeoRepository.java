package com.evanm.weather.repository;

import java.util.Optional;
import org.locationtech.jts.geom.Coordinate;

public interface GeoRepository<T> {
   Optional<T> findOneByCoordinate(Coordinate coordinate); 
}
