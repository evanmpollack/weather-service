package com.evanm.weather.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evanm.weather.domain.Gridpoint;

@Repository
public interface GridpointRepository extends CrudRepository<Gridpoint, String>, GeoRepository<Gridpoint> {}