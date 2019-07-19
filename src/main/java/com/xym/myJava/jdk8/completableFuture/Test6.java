package com.xym.myJava.jdk8.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-17 22:30
 */
public class Test6 {
    public static void main(String[] args) {
        //testThenCombine();
        //testThenCompose();
        //testThenAcceptBoth();
        //applyToEither();
        //anyOf();
        //allOf();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //所有CompletableFuture都执行完毕
    private static void allOf() {
        Random random = new Random();
        //哪个stage执行的快结果就是那个stage的是，抛出异常也算执行快
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage1---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage1---222");
                //if (true) {
                //    throw new IllegalArgumentException("stage1");
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage1";
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage2---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage2---2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage2".length();
        });
        CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage3---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage3---2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage3".equals("");
        });

        CompletableFuture<Void> future1 = CompletableFuture.allOf(future, future2, future3);
        try {
            System.out.println(future1.get());
            System.out.println(String.join(",", future.isDone() + "", future2.isDone() + "", future3.isDone() + ""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void anyOf() {
        Random random = new Random();
        //哪个stage执行的快结果就是那个stage的是，抛出异常也算执行快
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage1---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage1---222");
                //if (true) {
                //    throw new IllegalArgumentException("stage1");
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage1";
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage2---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage2---2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage2".length();
        });
        CompletableFuture<Boolean> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage3---");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread() + "--stage3---2222");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage3".equals("");
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future, future2, future3);
        try {
            System.out.println(anyOf.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void applyToEither() {
        //哪个stage执行的快结果就是那个stage的是，抛出异常也算执行快
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "--stage1---");
            if (true) {
                //如果这里抛异常，完蛋了。。。。
                //throw new IllegalArgumentException("参数有误！");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "stage1";
            //只有stage1执行完毕才会执行stage2
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage2---");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "stage2";
        }), (src) -> {
            System.out.println("src=" + src);
            return src.length();
        });

        try {
            System.out.println("----------------" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testThenAcceptBoth() {
        //stage1和stage2没有先后顺序，并发执行,最后结果都被action消费
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "--stage1---");
            if (true) {
                //如果这里抛异常，完蛋了。。。。
                throw new IllegalArgumentException("参数有误！");
            }
            return "stage1";
            //只有stage1执行完毕才会执行stage2
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "--stage2---");
            return "stage2";

        }), (s1, s2) -> System.out.println("s1=" + s1 + ",s2=" + s2));

        try {
            System.out.println("testThenAcceptBoth=" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testThenCompose() {
        //stage1和stage2之间有先后顺序
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "stage1---");
            return "stage1";
            //只有stage1执行完毕才会执行stage2
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("stage2");
            return s.length();
        }));
    }

    private static void testThenCombine() {
        //2个异常操作，没有先后顺序，并发执行
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "stage1---");
            return "stage1";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {

                //stage2在stage1前执行
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "stage2---");
            return "stage2";
        }), (s1, s2) -> {
            //只有2个都执行完毕才会执行
            System.out.println("s1=" + s1 + ",s2=" + s2);
            return s1.length() + s2.length();
        });
    }
}
