package com.evanm.weather.repository;

import java.util.Optional;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.evanm.weather.domain.Gridpoint;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GridpointRepository implements GeoRepository<Gridpoint, String> {
    private final static String KEY = "gridpoint";
    
    private RedisTemplate<String, Gridpoint> redisTemplate;
    
    public GridpointRepository(RedisTemplate<String, Gridpoint> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Try catch
    
    @Override
    public <S extends Gridpoint> S save(S entity) {
        redisTemplate.opsForHash().put(KEY, entity.getOffice(), entity);

        return entity;
    }

    @Override
    public <S extends Gridpoint> Iterable<S> saveAll(Iterable<S> entities) {
        Map<String, Gridpoint> entityMap = new HashMap<>();
        entities.forEach((e) -> entityMap.put(e.getOffice(), e));
        redisTemplate.opsForHash().putAll(KEY, entityMap);
        
        return entities;
    }

    @Override
    public Optional<Gridpoint> findById(String id) {
        return Optional.of((Gridpoint) redisTemplate.opsForHash().get(KEY, id));
    }

    @Override
    public boolean existsById(String id) {
        return findById(id).isPresent();
    }

    @Override
    public Iterable<Gridpoint> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Iterable<Gridpoint> findAllById(Iterable<String> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllById'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void deleteById(String id) {
        redisTemplate.opsForHash().delete(KEY, id);
    }

    @Override
    public void delete(Gridpoint entity) {
        deleteById(entity.getOffice());
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllById'");
    }

    @Override
    public void deleteAll(Iterable<? extends Gridpoint> entities) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public Optional<Gridpoint> findByCoordinate(Coordinate coordinate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByCoordinate'");
    }
    
}
