package com.lbb.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedisUtils {

    @Resource
    private JedisPool jedisPool;

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }
    }

    public String set(String key, Object value) {
        return this.set(key, value, null);
    }

    public String set(String key, Object value, Long expireTime) {
        Jedis jedis = null;
        try {
            String valueStr = value instanceof String ? value.toString() : JSONObject.toJSONString(value);

            jedis = jedisPool.getResource();
            if (value != null) {
                return jedis.set(key, valueStr);
            } else {
                return jedis.set(key, valueStr, "nx", "ex", expireTime);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            jedis.close();
        }
    }

    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0L;
        } finally {
            jedis.close();
        }
    }
}
