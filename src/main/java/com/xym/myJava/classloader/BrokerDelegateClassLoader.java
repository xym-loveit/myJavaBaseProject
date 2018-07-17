package com.xym.myJava.classloader;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 17:13
 */
public class BrokerDelegateClassLoader extends MyClassLoader {

    private final static Path DEFAULT_CLASS_DIR = Paths.get("D:", "classloader1");
    private final Path classDir;

    public BrokerDelegateClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    //允许传入指定路径的class路径
    public BrokerDelegateClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public BrokerDelegateClassLoader(ClassLoader parent, String classDir) {
        super(parent, classDir);
        this.classDir = Paths.get(classDir);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        //根据类的全路径名称进行加锁，确保每一个类在多线程的情况下只被加载一次
        synchronized (getClassLoadingLock(name)) {
            //到已加载的缓存中查看该类是否已经被加载，如果已加载则直接返回
            Class<?> klass = findLoadedClass(name);
            if (klass == null) {
                //若缓存中没有被加载的类，则需要对其进行说一次加载，如果类的全路径以java和javax开头，
                // 则直接委托给系统类加载器对其进行加载
               /* if (name.startsWith("java.") || name.startsWith("javax")) {
                    klass = getSystemClassLoader().loadClass(name);
                } else {*/
                    //如果类不是以java和javax开头，则尝试用我们自定义的类加载器进行加载
                    klass = this.findClass(name);
                //}

                if (klass == null) {
                    //如果自定义加载仍旧没有完成对类的加载，则委托给其父加载器加载或者系统类加载器进行加载
                    if (getParent() != null) {
                        klass = getParent().loadClass(name);
                    } else {
                        klass = getSystemClassLoader().loadClass(name);
                    }
                }
            }

            //经过若干次的尝试之后，如果还无法对类进行加载，则抛出无法找到类的异常
            if (klass == null) {
                throw new ClassNotFoundException("the class " + name + " not found");
            }
            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }
    }
}
