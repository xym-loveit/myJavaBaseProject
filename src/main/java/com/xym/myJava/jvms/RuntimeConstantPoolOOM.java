package com.xym.myJava.jvms;

import java.util.ArrayList;

/**
 * 方法区和运行时常量池（运行时常量池包含在方法区中，在jdk1.7之前）
 * <p>
 * -XX:PermSize=10MB
 * -XX:MaxPermSize=10MB
 *
 * @author xym
 * @create 2019-03-26 14:16
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //保存常量池引用避免GC行为
        ArrayList<String> strs = new ArrayList<>();
        int i = 0;
        while (true) {
            strs.add(String.valueOf(i++).intern());
        }
    }
}
