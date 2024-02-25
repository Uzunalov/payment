package com.azericard.apigateway.config.constants;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConstants {

    private String address;
    private String password;
    private int connectionPoolSize;
    private int connectionMinimumIdleSize;
    private long tokenTimeToLive;
}
