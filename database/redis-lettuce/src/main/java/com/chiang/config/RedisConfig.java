package com.chiang.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.util.Assert;

/**
 * 哨兵配置
 *
 * @author Chiang
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    /**
     * Lettuce is thread-safe by design which is sufficient for most cases.
     * All Redis user operations are executed single-threaded. Using multiple connections
     * does not impact the performance of an application in a positive way。
     */
    @Bean
    public RedisConnectionFactory lettuceConnectionFactory(RedisProperties redisProperties) {

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master(redisProperties.getSentinel().getMaster());

        for (String node : redisProperties.getSentinel().getNodes()) {
            String[] parts = node.split(":");
            Assert.state(parts.length == 2, "请配置正确的host和port");
            sentinelConfig.sentinel(parts[0],Integer.valueOf(parts[1]));
        }

        sentinelConfig.setDatabase(redisProperties.getDatabase());

        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration
                .builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

        return new LettuceConnectionFactory(sentinelConfig,clientConfiguration);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
