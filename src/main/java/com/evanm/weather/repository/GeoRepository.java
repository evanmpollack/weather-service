package com.evanm.weather.repository;

import java.util.Optional;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.data.repository.CrudRepository;

public interface GeoRepository<T, ID> extends CrudRepository<T, ID>{
   Optional<T> findByCoordinate(Coordinate coordinate); 
}
