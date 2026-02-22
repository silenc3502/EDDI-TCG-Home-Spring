package com.example.edditcghomespring.redis_cache.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisCacheRepositoryImpl implements RedisCacheRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void setKeyAndValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setKeyAndValue(String key, String value, Duration ttl) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    @Override
    public String getValueByKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    @Override
    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }
}
