package com.xym.myJava.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-12 16:14
 */
public class Test02 {

    /**
     * ognl context
     */
    private static final Map CONTEXT = Ognl.createDefaultContext(null, new DefaultMemberAccess(true));
    static List<String> banks = null;

    static {
        banks = Arrays.asList("中国银行", "建设银行", "工商银行", "杭州银行", "农业银行", "邮政储蓄", "光大银行", "交通银行");
    }

    public static void main(String[] args) throws IOException, OgnlException {
        //testOther();
        //testListAndMap();
        CONTEXT.put("banks", banks);
        Object expression = Ognl.parseExpression("#banks2={@com.xym.myJava.ognl.Test02@createCard()," +
                "@com.xym.myJava.ognl.Test02@createCard()," +
                "@com.xym.myJava.ognl.Test02@createCard()}");
        Person person = new Person();
        Object value = Ognl.getValue(expression, CONTEXT, person);
        System.out.println(value);
        System.out.println(CONTEXT.entrySet());
        List<Card> cards = (List<Card>) CONTEXT.get("banks2");

        int i = 0;
        for (Card card1 : cards) {
            card1.setName(banks.get(new java.util.Random().nextInt(banks.size())));
            card1.setNo("-->" + (i++));
        }
        person.getCards().addAll(cards);
        //list的投影
        System.out.println(Ognl.getValue("cards.{#this.no}", CONTEXT, person));
        //list的选择
        System.out.println(Ognl.getValue("cards.{? #this.no.indexOf('2')!=-1}", CONTEXT, person));
        System.out.println(person.getCards());
        //选择满足条件的最后一个元素
        System.out.println(Ognl.getValue("cards.{$ #this.no!=null}", CONTEXT, person));
        //选择满足条件的第一个元素
        System.out.println(Ognl.getValue("cards.{^ #this.no!=null}", CONTEXT, person));
    }

    private static Card card = new Card();

    private static Card createCard() throws CloneNotSupportedException {
        return (Card) card.clone();
    }

    private static void testListAndMap() throws OgnlException {
        //[下标]用来操作list
        Object expression = Ognl.parseExpression("#i=0,labels[#i+1]");
        Person person = new Person();
        person.setLabels(Arrays.asList("机灵", "帅气", "可爱"));
        System.out.println(Ognl.getValue(expression, CONTEXT, person));
        System.out.println(Ognl.getValue("labels.size()", CONTEXT, person));

        expression = Ognl.parseExpression("#v1=new com.xym.myJava.ognl.SkillDetail(),#v2=100,#v1.name");
        Ognl.setValue(expression, CONTEXT, person, "123");
        System.out.println(Ognl.getValue("#v1", CONTEXT, person));
        System.out.println(Ognl.getValue("#this", CONTEXT, person));
        //所有的临时变量全都会存放起来，包括#v1,#v2
        System.out.println(CONTEXT.entrySet());
        System.out.println("---" + ((OgnlContext) CONTEXT).getCurrentObject());
        System.out.println("---22" + ((OgnlContext) CONTEXT).getFirstType());
        //System.out.println(CONTEXT.entrySet());
        //通过#{}实例化map,不带#实例化list
        Object object = Ognl.getValue("#v3=new com.xym.myJava.ognl.Skill(),#v3.maps=#{'k1':{#v1},'k2':{#v1}}", CONTEXT, person);
        System.out.println(CONTEXT.entrySet());
        Ognl.getValue("skills={#v3}", CONTEXT, person);
        System.out.println(person.getSkills().get(0).getMaps());
    }

    private static void testOther() throws OgnlException {
        //testBug();
        //testFun();
        CONTEXT.put("content", Arrays.asList(1, 2, 3, 4, 5));
        Person person = new Person();
        //#访问
        Object expression = Ognl.parseExpression("#content.{#this+1}");
        //多个表达式的情况下，会用最后一个表达式当结果(输出[2, 3, 4, 5, 6])（{#this+1}为遍历中的每一个元素）
        expression = Ognl.parseExpression("#temp=123,{#this},{2,3,5}[2],#content=#content.{#this+1},#content");
        Object value = Ognl.getValue(expression, CONTEXT, person);
        System.out.println(value);
        //使用#逐级引用
        expression = Ognl.parseExpression("#temp=123,#c=#content,#c.add(#temp),#c");
        value = Ognl.getValue(expression, CONTEXT, person);
        System.out.println(value);
        expression = Ognl.parseExpression("#temp=123,#c=#content,#c.add(#temp),#c");
        value = Ognl.getValue(expression, CONTEXT, person);
        System.out.println(value);
        //this引用的为root
        expression = Ognl.parseExpression("#this");
        value = Ognl.getValue(expression, CONTEXT, 888);
        System.out.println(value);
        //for (Object o : value) {
        //    System.out.println(o.getClass());
        //}
    }

    private static void testFun() throws OgnlException {
        final Person person = new Person();
        person.setName("admin");
        person.setAge(18);
        //可以以逗号分隔多条命令，#表示临时变量
        Object expression = Ognl.parseExpression("#t1=age,#t2=#t1+1,age=20,@java.lang.Thread@sleep(2000),setAge(#t2)");
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //监控age的值
                System.out.println(person.getAge());
            }
        }).start();
        Ognl.getValue(expression, CONTEXT, person);
        //故意造成主线程中断，表达式照样执行
        Thread.currentThread().interrupt();
    }

    private static void testBug() throws IOException {
        InputStream fis = null;
        try {
            //Process process = Runtime.getRuntime().exec("cmd.exe /c dir d:\\");
            /////取得命令结果的输出流
            //InputStream fis = process.getInputStream();
            ////用一个读输出流类去读
            //InputStreamReader isr = new InputStreamReader(fis, "GBK");
            ////用缓冲器读行
            //BufferedReader br = new BufferedReader(isr);
            //String line = null;
            ////直到读完为止
            //while ((line = br.readLine()) != null) {
            //    System.out.println(line);
            //}
            //br.close();
            //testStatic();
            //带有执行功能的表达式,这个就吊了啊，可以执行系统命令，struts2漏洞如出一辙（如这里将dir换成del那就危险了）
            Object expression = Ognl.parseExpression("#rt=@java.lang.Runtime@getRuntime(),#rt.exec(\"cmd.exe /c dir d:\\\\\")");
            //注意这里的上下文随意指定为123
            Process process = (Process) Ognl.getValue(expression, CONTEXT, 123);
            //System.out.println(Ognl.getValue(expression, CONTEXT, ""));
            /////取得命令结果的输出流
            fis = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            //直到读完为止
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            //testStatic();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
    }

    private static void testStatic() throws OgnlException {
        //testGetAndSet();
        //调用静态属性和方法
        Person person = new Person();
        Object expression = Ognl.parseExpression("@com.xym.myJava.ognl.Person@SEX_ENUM");
        expression = Ognl.parseExpression("@com.xym.myJava.ognl.Person@sayHi('admin')");
        System.out.println(Ognl.getValue(expression, CONTEXT, person));
    }

    private static void testGetAndSet() throws OgnlException {
        //普通属性的get和set操作
        Object expression = Ognl.parseExpression("name");
        Person person = new Person();
        person.setName("xym");
        System.out.println(Ognl.getValue(expression, CONTEXT, person));
        Ognl.setValue(expression, CONTEXT, person, "xym---");
        System.out.println(Ognl.getValue(expression, CONTEXT, person));
        System.out.println(person.getName() + "");
        //直接为list类型属性赋值
        expression = Ognl.parseExpression("labels={\"萝莉控\",\"傻白甜\",\"sb\",\"真话哔哔\",\"一身正气\",\"月光族\"}");
        Ognl.getValue(expression, CONTEXT, person);
        System.out.println(person.getLabels());
        //调用set方法赋值
        expression = Ognl.parseExpression("setLabels({\"大拿\",\"傻白甜\",\"sb\",\"真话哔哔\",\"一身正气\",\"月光族\"})");
        Ognl.getValue(expression, CONTEXT, person);
        System.out.println(person.getLabels());
        //map的表达式为每一个键值对中间采用逗号","连接，键和值之间采用冒号分割
        expression = Ognl.parseExpression("#{'k1':'v1','k2':'v2','k3':'v3','k4':'v4','k5':'v5'}");
        System.out.println(expression.getClass());
        Object object = Ognl.getValue(expression, CONTEXT, person);
        System.out.println(object.getClass());
        System.out.println(Ognl.getValue("['k4']", CONTEXT, object));
        //获取大小，如果带了{}则表示为list集合
        List value = (List) Ognl.getValue("{#this.size,\"kk\"}", CONTEXT, object);
        System.out.println(value);
        //构造一个普通的java对象
        expression = Ognl.parseExpression("new java.lang.String(\"试试看\")");
        Object value1 = Ognl.getValue(expression, CONTEXT, "");
        System.out.println(value1.toString().length());
        System.out.println("试试看".length());
    }
}
