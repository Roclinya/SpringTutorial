package com.tutorial.SpringTutorial.Config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(
            @Value("${redisson.address:redis://127.0.0.1:6379}") String address,
            @Value("${redisson.password:}") String password,
            @Value("${redisson.database:0}") int database) {

        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(address)
                .setDatabase(database);

        if (StringUtils.hasText(password)) {
            serverConfig.setPassword(password);
        }

        return Redisson.create(config);
    }
}

