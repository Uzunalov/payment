package com.azericard.apigateway.config;

import com.azericard.apigateway.config.constants.RedisConstants;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    private final RedisConstants redisConstants;

    public RedisConfig(RedisConstants redisConstants) {
        this.redisConstants = redisConstants;
    }

    @Bean
    RedissonClient redissonClient() {
        final Config config = new Config();
        config.setCodec(new SerializationCodec());
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(redisConstants.getAddress());
        singleServerConfig.setPassword(redisConstants.getPassword());
        singleServerConfig.setConnectionPoolSize(redisConstants.getConnectionPoolSize());
        singleServerConfig.setConnectionMinimumIdleSize(redisConstants.getConnectionMinimumIdleSize());
        return Redisson.create(config);
    }

}
