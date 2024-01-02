package com.evanm.weather.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.evanm.weather.converters.BytesToPolygonConverter;
import com.evanm.weather.converters.PolygonToBytesConverter;
import com.evanm.weather.domain.Gridpoint;

@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    // Needed?
    @Bean
    public RedisTemplate<String, Gridpoint> redisTemplate() {
        RedisTemplate<String, Gridpoint> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        // Serializers have to be set, otherwise Java serialization is used, preprending the serial UUID to the K/V
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Gridpoint.class));
        
        return redisTemplate;
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(
            Arrays.asList(
                new PolygonToBytesConverter(),
                new BytesToPolygonConverter()
            )
        );
    }
}
