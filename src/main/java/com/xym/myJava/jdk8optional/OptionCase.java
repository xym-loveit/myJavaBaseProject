package com.xym.myJava.jdk8optional;

import java.util.Optional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 18:04
 */
public class OptionCase {
    public static void main(String[] args) {

    }

    /**
     * 原始的写法，级联判断，非常麻烦
     *
     * @param user
     * @return
     * @throws Exception
     */
    private static String getCity(User user) throws Exception {
        if (user != null) {
            Address address = user.getAddress();
            if (null != address) {
                if (address.getCity() != null) {
                    return address.getCity();
                }
            }
        }
        throw new Exception("取值错误！");
    }

    /**
     * 使用Optional+lambda表达式实现同样的效果却很简洁
     *
     * @param user
     * @return
     * @throws Exception
     */
    private static String getCity4Jdk8(User user) throws Exception {
        return Optional.ofNullable(user).map(u -> u.getAddress()).map(a -> a.getCity()).orElseThrow(() -> new Exception("取值错误！"));
    }

    /**
     * 原始的写法，判断user不为null执行操作
     *
     * @param user
     */
    private static void method(User user) {
        if (user != null) {
            System.out.println(user);
        }
    }

    /**
     * 新写法很简洁
     *
     * @param user
     */
    private static void method4Jdk8(User user) {
        Optional.ofNullable(user).ifPresent((u) -> {
                    System.out.println(u);
                }
        );
    }

    /**
     * 以前的老写法
     *
     * @param user
     * @return
     * @throws Exception
     */
    public static User getUser(User user) throws Exception {
        if (user != null) {
            String name = user.getName();
            if ("zhangsan".equals(name)) {
                return user;
            }
        } else {
            user = new User();
            user.setName("zhangsan");
            return user;
        }
        return null;
    }

    public static User getUser4Jdk8(User user) throws Exception {
        Optional.ofNullable(user).filter(user1 -> user1.getName().equals("zhangsan")).orElseGet(() -> {
            User user1 = new User();
            user1.setName("zhangsan");
            return user1;
        });
        return null;
    }
}
