package com.cuit;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : TestSentinels
 * @packageName : com.cuit
 * @description : 一般类
 * @date : 2020-06-08 0:37
 **/

public class TestSentinels {
    @SuppressWarnings("resource")
    @Test
    public void testSentinel() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);
        // 哨兵信息
        Set<String> sentinels = new HashSet<>(Arrays.asList("47.94.18.106:26379",
                "123.56.125.48:26379"));
        // 创建连接池
        JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels,jedisPoolConfig,"password");
        // 获取客户端
        Jedis jedis = pool.getResource();
        // 执行两个命令
        jedis.set("mykey", "myvalue");
        String value = jedis.get("mykey");
        System.out.println(value);
    }
}