package com.xym.myJava.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器
 *
 * @author xym
 * @create 2018-07-17 16:08
 */
public class MyClassLoader extends ClassLoader {

    private final static Path DEFAULT_CLASS_DIR = Paths.get("D:", "classloader1");
    private final Path classDir;

    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    //允许传入指定路径的class路径
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public MyClassLoader(ClassLoader parent, String classDir) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        String classpath = name.replace(".", "/");
        Path resolve = classDir.resolve(Paths.get(classpath + ".class"));
        if (!resolve.toFile().exists()) {
            throw new ClassNotFoundException("the class " + name + "not found");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Files.copy(resolve, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class " + name + " occur error.", e);
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = readClassBytes(name);
        if (null == bytes || bytes.length == 0) {
            throw new ClassNotFoundException("can not load class " + name);
        }
        //调用define 方法定义class
        return this.defineClass(name, bytes, 0, bytes.length);
    }
}
