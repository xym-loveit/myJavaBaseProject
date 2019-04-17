package com.xym.myJava.head_first._012.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用jdk动态代理--创建“非拥有者代理类”
 * <p>
 * 非拥有者代理类，即为其他人
 * 1、可以打分
 * 2、可以获取信息
 * 3、但不允许设置其他属性（性别、姓名、爱好）
 *
 * @author xym
 * @create 2019-04-17 17:47
 */
public class NonOwnerInvocationHandler implements InvocationHandler {

    private PersonBean personBean;

    public NonOwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if (method.getName().equals("setHotOrNotRating")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("set")) {
                throw new IllegalAccessException();
            } else if (method.getName().equals("get")) {
                return method.invoke(personBean, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
