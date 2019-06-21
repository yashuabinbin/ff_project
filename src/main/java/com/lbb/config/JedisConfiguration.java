package com.lbb.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        jedisConfig.setTestOnBorrow(true);
        JedisPool jedisPool = new JedisPool(jedisConfig, host, port, 3000, StringUtils.isNotBlank(password) ? password : null);
        return jedisPool;
    }
}
