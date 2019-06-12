package com.xym.myJava.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-13 10:54
 */
public class Test03 {
    public static void main(String[] args) throws OgnlException {
        Person person = new Person();
        person.setName("admin".toUpperCase());
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(person, new DefaultMemberAccess(false));
        System.out.println(context.getCurrentObject());
        Object value = Ognl.getValue("{'aaa','bbb','111','222'}.{#this.toUpperCase()}", context, person);
        System.out.println(value + "--" + value.getClass());
        System.out.println(Ognl.getValue("name", context, person));
    }
}
