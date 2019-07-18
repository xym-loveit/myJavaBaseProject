package com.xym.myJava.jdk8.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 组合式异步编程
 *
 * @author xym
 * @create 2019-07-16 0:11
 */
public class Test {

    private static void doSomethingElse() {
        System.out.println("执行其他任务---");
    }

    private final static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"), new Shop("ShopEasy"));

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), (r) -> {
        Thread thread = new Thread(r);
        //所有线程执行完毕，守护线程自动退出
        thread.setDaemon(true);
        return thread;
    });

    public static void main(String[] args) {

        //future4BeforeJdk8();
        //testFuture();
        //fetchPrice();
        //supplyAsync();

        long start = System.nanoTime();
        System.out.println(Shop.findPricesFutureV2("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        //long start = System.nanoTime();
        //System.out.println(Shop.findPricesSequenceV2("myPhone27S"));
        //long duration = (System.nanoTime() - start) / 1_000_000;
        //System.out.println("Done in " + duration + " msecs");


        //
        //long start = System.nanoTime();
        //System.out.println(Shop.findPricesFuture("myPhone27S"));
        //long duration = (System.nanoTime() - start) / 1_000_000;
        //System.out.println("Done in " + duration + " msecs");

        //long start = System.nanoTime();
        //System.out.println(Shop.findPricesParallel("myPhone27S"));
        //long duration = (System.nanoTime() - start) / 1_000_000;
        //System.out.println("Done in " + duration + " msecs");

        //long start2 = System.nanoTime();
        //System.out.println(Shop.findPricesSequence("myPhone27S"));
        //long duration2 = (System.nanoTime() - start2) / 1_000_000;
        //System.out.println("Done in " + duration2 + " msecs");
    }

    private static void supplyAsync() {
        Shop shop = new Shop("电脑城");
        Future<Double> dell = shop.getPriceAsync2("dell", true);
        try {
            System.out.println(dell.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void fetchPrice() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        //调用方法后立马返回
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product", true);
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");

        // 执行更多任务，比如查询其他商店
        doSomethingElse();
        // 在计算商品价格的同时
        try {
            //堵塞耗时
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    /**
     * 折扣服务
     */
    private static class Discount {
        public enum Code {
            /*没有折扣*/NONE(0), /*白银折扣*/SILVER(5), /*黄金折扣*/GOLD(10), /*白金折扣*/PLATINUM(15), /*钻石折扣*/DIAMOND(20);
            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }

        }

        /**
         * 通过Quote对象算出最终价格
         *
         * @param quote
         * @return
         */
        public static String applyDiscount(Quote quote) {
            return quote.getShopName() + " price is " +
                    Discount.apply(quote.getPrice(),
                            quote.getDiscountCode());
        }

        private static double apply(double price, Code code) {
            delay();
            return price * (100 - code.percentage) / 100;
        }

        /**
         * 模拟延迟方法
         */
        public static void delay() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 最后
     */
    public static class Quote {
        private final String shopName;
        private final double price;
        private final Discount.Code discountCode;

        public Quote(String shopName, double price, Discount.Code discountCode) {
            this.shopName = shopName;
            this.price = price;
            this.discountCode = discountCode;
        }

        /**
         * 字符串转为Quote对象
         *
         * @param
         * @return
         */
        public static Quote parse(String s) {
            String[] split = s.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Discount.Code discountCode = Discount.Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }

        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getDiscountCode() {
            return discountCode;
        }
    }


    /**
     * 在线商店类
     */
    private static class Shop {
        private String name;

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        /**
         * 带有折扣版本的查询价格服务
         *
         * @param product
         * @return
         */
        public String getPriceV2(String product) {
            double price = calculatePrice(product, false);
            Discount.Code code = Discount.Code.values()[
                    random.nextInt(Discount.Code.values().length)];
            return String.format("%s:%.2f:%s", name, price, code);
        }


        /**
         * 通过产品名称查询商店的名称、该商店中指定商品的价格（异步并发执行）
         * <p>java8 实战---------------p237（那幅图解释的很到位）
         * 注意这里一共使用了2条流操作，如果使用单条stream达不到异步执行的效果
         *
         * @param product
         * @return
         */
        public static List<String> findPricesFuture(String product) {
            List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                    new Shop("LetsSaveBig"),
                    new Shop("MyFavoriteShop"),
                    new Shop("BuyItAll"), new Shop("ShopEasy"));

            //System.out.println("------------------");

            //使用 CompletableFuture
            //以异步方式调用同步api计算每种商品的价格
            List<CompletableFuture<String>> futures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",
                    shop.getName(), shop.getPrice(product)), executor)).collect(Collectors.toList());
            //等待所有异
            //步操作结束
            //注意这里一共使用了2条流操作，如果使用单条stream达不到异步执行的效果
            return futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }


        /**
         * 通过产品名称查询商店的名称、该商店中指定商品的价格（并行执行）
         *
         * @param product
         * @return
         */
        public static List<String> findPricesParallel(String product) {
            List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                    new Shop("LetsSaveBig"),
                    new Shop("MyFavoriteShop"),
                    new Shop("BuyItAll"), new Shop("ShopEasy"));
            //顺序执行，各个操作之间被堵塞
            return shops.stream().parallel().map(shop -> String.format("%s price is %.2f",
                    shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        }

        /**
         * 通过产品名称查询商店的名称、该商店中指定商品的价格（顺序执行）
         *
         * @param product
         * @return
         */
        public static List<String> findPricesSequence(String product) {
            List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                    new Shop("LetsSaveBig"),
                    new Shop("MyFavoriteShop"),
                    new Shop("BuyItAll"), new Shop("ShopEasy"));
            //顺序执行，各个操作之间被堵塞
            return shops.stream().map(shop -> String.format("%s price is %.2f",
                    shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
        }

        /**
         * 通过产品名称查询商店的名称、该商店中指定商品的价格（顺序执行）
         *
         * @param product
         * @return
         */
        public static List<String> findPricesSequenceV2(String product) {

            //顺序执行，各个操作之间被堵塞
            //1、第一个操作将每个 shop 对象转换成了一个字符串，该字符串包含了该 shop 中指定商品的
            //价格和折扣代码
            //2、第二个操作对这些字符串进行了解析，在 Quote 对象中对它们进行转换
            //3、最终，第三个 map 会操作联系远程的 Discount 服务，计算出最终的折扣价格，并返回该
            //价格及提供该价格商品的 shop
            return shops.stream().map(shop -> shop.getPriceV2(product)).map(Quote::parse).map(Discount::applyDiscount)
                    .collect(Collectors.toList());
        }

        /**
         * 通过产品名称查询商店的名称、该商店中指定商品的价格（异步执行）
         *
         * @param product
         * @return
         */
        public static List<String> findPricesFutureV2(String product) {
            List<CompletableFuture<String>> priceFutures = shops.stream().
                    map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceV2(product), executor)).
                    map(future -> future.thenApply(Quote::parse)).
                    map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                    .collect(Collectors.toList());
            return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }

        /**
         * 通过工厂方法精简
         *
         * @param product
         * @return
         */
        public Future<Double> getPriceAsync2(String product, boolean excep) {
            return CompletableFuture.supplyAsync(() -> calculatePrice(product, excep));
        }

        /**
         * 根据产品异步返回价格
         *
         * @param product
         * @return
         */
        public Future<Double> getPriceAsync(String product, boolean excep) {
            //创建 CompletableFuture对象，它会包含计算的结果
            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
            //在另一个线程中以异步方式执行计算
            new Thread(() -> {
                try {
                    //需长时间计算的任务结束并得出结果时，设置Future的返回值
                    double price = calculatePrice(product, excep);
                    //如果价格计算正常结束，完成Future操作并设置商品价格
                    futurePrice.complete(price);
                } catch (Exception e) {
                    //否则就抛出导致失败的异常，完成这次Future操作
                    futurePrice.completeExceptionally(e);
                }
            }).start();
            //无需等待还没结束的计
            //算，直接返回 Future 对象
            return futurePrice;
        }

        private Random random = new Random();

        /**
         * 根据产品获取价格的方法
         *
         * @param product
         * @return
         */
        private double calculatePrice(String product, boolean excep) {
            delay();
            if (excep) {
                throw new RuntimeException("制造一个异常看看get方法会不会永远堵塞");
            }
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

        /**
         * 同步堵塞方法
         *
         * @param product
         * @return
         */
        private double getPrice(String product) {
            delay();
            return random.nextDouble() * product.charAt(0) + product.charAt(1);
        }

        /**
         * 模拟延迟方法
         */
        public static void delay() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void testFuture() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                //throw new RuntimeException("抛个异常试试看");
            }
            return "success";
        });

        System.out.println("继续执行-----------");
        while (!future.isDone()) {
            //System.out.println("----");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void future4BeforeJdk8() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        Future<String> future1 = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(1);
                return "success";
            }
        });

        try {
            System.out.println(future1.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        if (((ThreadPoolExecutor) service).getCompletedTaskCount() == ((ThreadPoolExecutor) service).getTaskCount()) {
            service.shutdown();
        }
    }
}
