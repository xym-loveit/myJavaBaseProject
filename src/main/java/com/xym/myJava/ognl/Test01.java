package com.xym.myJava.ognl;

import ognl.Ognl;
import ognl.OgnlException;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * ognl测试
 * <p>
 * 三要素：
 * 1、表达式（Expression）（干什么）
 * 2、上下文（Context）（在哪里干，环境）
 * 3、Root对象（Root Object）（对谁干，目标对象）
 *
 * @author xym
 * @create 2019-05-10 15:11
 */
public class Test01 {

    @Test
    public void testGetValue() throws OgnlException {
        // 创建Root对象
        User user = new User();
        user.setId(1L);
        user.setName("downpour");

        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        context.put("introduction", "My name is ");

        // 测试从Root对象中进行表达式计算并获取结果
        Object name = Ognl.getValue("name", context, user);
        assertEquals("downpour", name);

        // 测试从上下文环境中进行表达式计算并获取结果
        Object contextValue = Ognl.getValue("#introduction", context, user);
        assertEquals("My name is ", contextValue);

        // 测试同时从将Root对象和上下文环境作为表达式的一部分进行计算
        Object hello = Ognl.getValue("#introduction + name", context, user);
        assertEquals("My name is downpour", hello);
    }

    @Test
    public void testSetValue() throws Exception {
        // 创建Root对象
        User user = new User();
        user.setId(1L);
        user.setName("downpour");
        //user.setGroup(new Group());
        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        context.put("introduction", "My name is ");
        // 对Root对象进行写值操作
        Ognl.setValue("group.name", context, user, "dev");
        Ognl.setValue("age", context, user, "18");
        System.out.println(Ognl.getRoot(context));
        assertEquals("dev", user.getGroup().getName());
    }

    @Test
    public void testGetStatic() throws OgnlException {
        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        //调用静态属性
        Object expression = Ognl.parseExpression("@com.xym.myJava.ognl.User@NAME");
        // 创建Root对象
        User user = new User();
        System.out.println(Ognl.getValue(expression, context, user));
        //调用静态方法
        expression = Ognl.parseExpression("@com.xym.myJava.ognl.User@say()");
        System.out.println(Ognl.getValue(expression, context, user));
    }


    /**
     * 访问数组
     *
     * @throws OgnlException
     */
    @Test
    public void getArray() throws OgnlException {
        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        // 创建Root对象
        User user = new User();
        //数组中第一个元素
        Object expression = Ognl.parseExpression("strs[0]");
        System.out.println(Ognl.getValue(expression, context, user));
        //数组长度
        expression = Ognl.parseExpression("strs.length");
        System.out.println(Ognl.getValue(expression, context, user));
        //数组长度+1
        expression = Ognl.parseExpression("strs.length.(#this+1)");
        System.out.println(Ognl.getValue(expression, context, user));
        //修改数组
        expression = Ognl.parseExpression("strs[strs.length-1]");
        Ognl.setValue(expression, context, user, "xym");
        expression = Ognl.parseExpression("strs[strs.length-1]");
        System.out.println(Ognl.getValue(expression, context, user));
    }

    @Test
    public void testList() throws OgnlException {
        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        // 创建Root对象
        User user = new User();
        //打印集合size
        Object expression = Ognl.parseExpression("lists.size()");
        System.out.println(Ognl.getValue(expression, context, user));
        //往集合里添加元素
        expression = Ognl.parseExpression("#e0=lists,#e0.add(\"java\")");
        System.out.println(Ognl.getValue(expression, context, user));
        //查看集合元素
        expression = Ognl.parseExpression("lists");
        List<String> sss = (List<String>) Ognl.getValue(expression, context, user);
        for (String s : sss) {
            System.out.print(s);
        }
        //投影
        expression = Ognl.parseExpression("groups.{name}");
        ArrayList<Group> objects = new ArrayList<>();
        user.setGroups(objects);
        objects.add(new Group("g1", "c1"));
        objects.add(new Group("g2", "c2"));
        objects.add(new Group("g3", "c3"));
        objects.add(new Group("g4", "c4"));
        List<String> names = (List<String>) Ognl.getValue(expression, context, user);
        System.out.println("\t" + StringUtils.join(names, "$"));
        //投影2(将group里的name和code用“--”符号链接起来)
        expression = Ognl.parseExpression("groups.{name+'--'+code}");
        List<String> nameAndCode = (List<String>) Ognl.getValue(expression, context, user);
        System.out.println("\t" + StringUtils.join(nameAndCode, "$"));
    }


    @Test
    public void testMap() throws OgnlException {
        // 创建上下文环境
        Map context = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
        // 创建Root对象
        User user = new User();
        HashMap<String, Group> hashMap = new HashMap<>();
        hashMap.put("k1", new Group("g1", "c1"));
        hashMap.put("k2", new Group("g2", "c2"));
        hashMap.put("k3", new Group("g3", "c3"));
        hashMap.put("k4", new Group("g4", "c4"));
        user.setMaps(hashMap);
        //找到k为k4的group对象的code属性
        Object expression = Ognl.parseExpression("maps['k4'].code");
        System.out.println(Ognl.getValue(expression, context, user));
        //ognl为map增加了虚拟属性keys和values
        expression = Ognl.parseExpression("maps.keys");
        Set<String> sets= (Set<String>) Ognl.getValue(expression, context, user);
        System.out.println(sets.stream().collect(Collectors.joining(",")));
    }
}
