package com.xym.myJava.cache;

/**
 * 数据加载接口
 *
 * @author xym
 * @create 2018-07-19 10:42
 */
@FunctionalInterface
public interface CacheLoader<K, V> {

    /**
     * 定义加载数据的方法
     *
     * @param key
     * @return
     */
    V load(K key);

}
