package com.xym.myJava.thread.cnblogs_skywang12345.chapter2;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-24 17:24
 */
public class Demo {

    private int anInt;

    public Demo(int anInt) {
        this.anInt = anInt;
    }

    public void update(int a, int u) {
        if (a == anInt) {
            this.anInt = u;
        }
    }

    final int getAndSet(int newValue) {
        int oldValue = this.anInt;
        this.anInt = newValue;
        return oldValue;
    }


    static class A {
        private long a; // not initialized value

        public A() {
            this.a = 1; // initialization
        }

        public long a() {
            return this.a;
        }
    }

    public static void main(String[] args) {
        //Unsafe unsafe = getUnsafe();
        //A o1 = new A(); // constructor
        //System.out.println(o1.a());

        //A o2 = A.class.newInstance(); // reflection
        //System.out.println(o2.a());

        //A o3 = (A) unsafe.allocateInstance(A.class); // unsafe
        //A o4 = (A) unsafe.allocateInstance(A.class); // unsafe
        //System.out.println(o3.a());
        //System.out.println(o3==o4);

        //Test01 guard = new Test01();
        //guard.giveAccess();   // false, no access
        ////guard.ACCESS_ALLOWED;
        //// bypass
        //Field f = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
        //unsafe.putInt(guard, unsafe.objectFieldOffset(f), 42); // memory corruption
        //
        //System.out.println(guard.giveAccess());

        //System.out.println(sizeOf(new A()));
        getUnsafe().throwException(new IOException());
        //throw new IOException("");
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long sizeOf(Object o) {
        Unsafe u = getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize / 8) + 1) * 8;   // padding
    }
}
