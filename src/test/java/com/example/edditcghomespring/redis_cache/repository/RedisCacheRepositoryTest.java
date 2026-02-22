package com.example.edditcghomespring.redis_cache.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.redis.test.autoconfigure.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@DataRedisTest
@Testcontainers
@Import(RedisCacheRepositoryImpl.class)
class RedisCacheRepositoryTest {

    @Container
    @ServiceConnection
    static GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:8.2.2-alpine"))
                    .withExposedPorts(6379);

    @Autowired
    private RedisCacheRepository redisCacheRepository;

    @Test
    void 값을_저장하고_조회할_수_있다() {
        redisCacheRepository.setKeyAndValue("key1", "value1");

        String result = redisCacheRepository.getValueByKey("key1");

        assertThat(result).isEqualTo("value1");
    }

    @Test
    void TTL이_만료되면_값은_사라진다() throws InterruptedException {
        redisCacheRepository.setKeyAndValue(
                "ttl:key",
                "value",
                Duration.ofSeconds(1)
        );

        Thread.sleep(1500);

        String result = redisCacheRepository.getValueByKey("ttl:key");

        assertThat(result).isNull();
    }

    @Test
    void 키가_존재하면_exists는_true다() {
        redisCacheRepository.setKeyAndValue("exist:key", "value");

        assertThat(redisCacheRepository.exists("exist:key")).isTrue();
    }

    @Test
    void 키가_없으면_exists는_false다() {
        assertThat(redisCacheRepository.exists("not:exist")).isFalse();
    }

    @Test
    void 없는_키를_삭제해도_예외가_발생하지_않는다() {
        redisCacheRepository.deleteByKey("no:key");

        assertThat(redisCacheRepository.exists("no:key")).isFalse();
    }
}