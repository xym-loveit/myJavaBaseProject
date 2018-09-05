package com.xym.myJava.redis;

import redis.clients.jedis.Jedis;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-05 11:40
 */
public class RedisClient {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //jedis.set("eat", "I want to eat");
        jedis.get("eat");
    }
}
