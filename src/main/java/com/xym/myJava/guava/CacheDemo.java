package com.xym.myJava.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-19 16:20
 */
public class CacheDemo {

    private static final CacheLoader<Long, User> USER_CACHE_LOADER = new CacheLoader<Long, User>() {
        @Override
        public User load(Long key) {
            //模拟从数据库/redis中加载数据
            User user = new User();
            user.setUserId(key);
            user.setName(Thread.currentThread().getName() + "-" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "-" + key);

            System.out.println("load: " + user);
            return user;
        }
    };

    private static final LoadingCache<Long, User> USER_LOADING_CACHE = CacheBuilder.newBuilder().
            expireAfterAccess(60, TimeUnit.SECONDS).
            expireAfterWrite(600, TimeUnit.SECONDS).
            refreshAfterWrite(2, TimeUnit.SECONDS).
            build(USER_CACHE_LOADER);


    private static final Random RANDOMS = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws Exception {
        monitorTask();

        while (true) {
            //System.out.println();
            USER_CACHE_LOADER.load(Long.valueOf(RANDOMS.nextInt(5000)));
            TimeUnit.MILLISECONDS.sleep(500);
        }

    }

    static void monitorTask() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println(USER_LOADING_CACHE.size());
            }
        }, 2000, 1000);
    }

    static class User {
        private Long userId;
        private String name;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
