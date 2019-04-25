package com.xym.myJava.head_first._12.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用jdk动态代理--创建“拥有者代理类”
 * <p>
 * 通过代理类控制权限
 * <p>
 * 拥有者的权限
 * 1、可以自我设置一些属性（姓名、性比、爱好）
 * 2、不能自己往脸上贴金（自己给自己加分）
 * 3、可以调用读取的方法
 *
 * @author xym
 * @create 2019-04-17 17:27
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private PersonBean personBean;

    public OwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            //自己可以访问所有get方法
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
                //但无法使用打分的方法（自己不能给自己打分）
            } else if (method.getName().equals("setHotOrNotRating")) {
                throw new IllegalAccessException();
                //如果是其它的set方法（因为拥有者是自己）则可以使用
            } else if (method.getName().startsWith("set")) {
                return method.invoke(personBean, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
