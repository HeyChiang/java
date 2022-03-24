package com.chiang.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.nio.charset.StandardCharsets;

import static io.lettuce.core.ReadFrom.REPLICA_PREFERRED;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    /**
     * Redis 哨兵配置
     */
//    @Bean
//    public RedisConnectionFactory lettuceConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master("redis-master")
//                .sentinel("172.24.0.1", 26379)
//                .sentinel("172.24.0.1", 26380)
//                .sentinel("172.24.0.1", 26381);
//
//        sentinelConfig.setDatabase(2);
//
//        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration
//                .builder()
//                .readFrom(ReadFrom.REPLICA)
//                .build();
//
//        return new LettuceConnectionFactory(sentinelConfig,clientConfiguration);
//    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
