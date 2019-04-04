package com.xym.myJava.jvms;

import java.io.IOException;
import java.io.InputStream;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-01 14:04
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream resource = getClass().getResourceAsStream(fileName);
                if (resource == null) {
                    return super.loadClass(name);
                }
                try {
                    int length = resource.available();
                    byte[] bytes = new byte[length];
                    resource.read(bytes);
                    return defineClass(name, bytes, 0, length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        try {
            Class<?> loadClass = myClassLoader.loadClass("com.xym.myJava.jvms.ClassLoaderTest");
            Object instance = loadClass.newInstance();
            System.out.println(instance.getClass());
            System.out.println(instance instanceof com.xym.myJava.jvms.ClassLoaderTest);
            System.out.println(ClassLoaderTest.class.getClassLoader());
            System.out.println(instance.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
