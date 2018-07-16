package com.xym.myJava;

import java.util.Collections;
import java.util.Map;

public class EnhancedMap<K, V extends Number> {

    Map<K, V> map;

    public EnhancedMap(Map<K, V> map) {
        this.map = Collections.unmodifiableMap(map);
    }

    /**
     * 复合操作使得原本安全的容器变为不安全
     *
     * @param k
     * @param v
     * @return
     */
    public V putIfAbsent(K k, V v) {
        V old = map.get(k);
        if (old != null) {
            return old;
        }
        return map.put(k, v);
    }

    public V put(K k, V v) {
        return map.put(k, v);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
