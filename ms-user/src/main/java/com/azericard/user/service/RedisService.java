package com.azericard.user.service;

import com.azericard.user.config.constants.RedisConstants;
import com.azericard.user.model.UserAccessTokenDto;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RedisService {

    private final RedissonClient redissonClient;
    private final RedisConstants redisConstants;

    public RedisService(RedissonClient redissonClient, RedisConstants redisConstants) {
        this.redissonClient = redissonClient;
        this.redisConstants = redisConstants;
    }

    public void save(String name, UserAccessTokenDto tokenPairDto) {
        saveOrUpdate(name, tokenPairDto);
    }

    public void update(String name, UserAccessTokenDto tokenPairDto) {
        saveOrUpdate(name, tokenPairDto);
    }

    public UserAccessTokenDto read(String name) {
        RBucket<UserAccessTokenDto> bucket = redissonClient.getBucket(name);
        return bucket.get();
    }

    public void delete(String name) {
        RBucket<UserAccessTokenDto> bucket = redissonClient.getBucket(name);

        if (Objects.isNull(bucket)) {
            log.warn("{} bucket not found", name);
            return;
        }

        bucket.delete();
    }

    private void saveOrUpdate(String name, UserAccessTokenDto tokenPairDto) {
        RBucket<UserAccessTokenDto> bucket = redissonClient.getBucket(name);
        bucket.set(tokenPairDto, redisConstants.getTokenTimeToLive(), TimeUnit.SECONDS);
    }

}
