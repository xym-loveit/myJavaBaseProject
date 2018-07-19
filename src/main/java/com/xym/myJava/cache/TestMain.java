package com.xym.myJava.cache;


import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-19 11:07
 */
public class TestMain {
    public static void main(String[] args) {
        //test01();
        //test02();
        //test03();
        //test04();
        //test05();
        //test06();


    }

    private static void test06() {
        /**
         * 虚引用必须和ReferenceQueue配合才能使用
         *
         * PhantomReference 的get方法始终返回null
         *
         * 当垃圾回收器决定回收PhantomReference对象的时候，会将其插入关联的ReferenceQueue中
         */
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Reference> phantomReference = new PhantomReference<>(new Reference(), queue);
        System.out.println(phantomReference.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
            java.lang.ref.Reference gcedRef = queue.remove();
            System.out.println(gcedRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //获取被垃圾回收的对象
    private static void test05() {
        //被垃圾回收的Reference会被加入与之关联的queue中
        ReferenceQueue<Reference> referenceQueue = new ReferenceQueue<>();
        Reference reference = new Reference();
        //定义WeakReference并且指定关联的Queue
        WeakReference<Reference> weakReference = new WeakReference<>(reference, referenceQueue);
        reference = null;
        System.out.println(weakReference.get());
        //手动执行gc操作
        System.gc();

        try {
            //给gc线程收集垃圾的契机
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            java.lang.ref.Reference<? extends Reference> gcedRef = referenceQueue.remove();
            //被垃圾回收之后会从队列中获得
            System.out.println(gcedRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 弱引用测试
     */
    private static void test04() {
        //无论是yong gc还是 full gc WeakReference都会被回收
        Reference reference = new Reference();
        WeakReference weakReference = new WeakReference<>(reference);
        reference = null;
        System.gc();
    }

    /**
     * 软引用测试
     */
    private static void test03() {
        CacheLoader<Integer, Reference> cacheLoader = new CacheLoader<Integer, Reference>() {
            @Override
            public Reference load(Integer key) {
                return new Reference();
            }
        };

        SoftLRUCache<Integer, Reference> lruCache = new SoftLRUCache<>(200, cacheLoader);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            lruCache.get(i);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("the " + i + " reference stored at cache.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    private static void test02() {
        CacheLoader<Integer, Reference> cacheLoader = new CacheLoader<Integer, Reference>() {
            @Override
            public Reference load(Integer key) {
                return new Reference();
            }
        };

        LRUCache<Integer, Reference> lruCache = new LRUCache<>(200, cacheLoader);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            lruCache.get(i);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("the " + i + " reference stored at cache.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void test01() {
        CacheLoader<String, Reference> cacheLoader = new CacheLoader<String, Reference>() {
            @Override
            public Reference load(String key) {
                return new Reference();
            }
        };
        LRUCache<String, Reference> lruCache = new LRUCache<>(5, cacheLoader);
        lruCache.get("张三");
        lruCache.get("李四");
        lruCache.get("王五");
        lruCache.get("赵6");
        lruCache.get("张一山");
        System.out.println(lruCache.toString());
        lruCache.get("xym");
        System.out.println(lruCache.toString());

        /**
         * LRUCache{keyList=[张三, 李四, 王五, 赵6, 张一山]}
         * LRUCache{keyList=[李四, 王五, 赵6, 张一山, xym]}
         *
         * 观察发现已经满足我们的缓存需求了
         */}

}
