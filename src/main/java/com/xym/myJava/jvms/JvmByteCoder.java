package com.xym.myJava.jvms;

/**
 * 字节码指令集，一般指令加载的范围
 * <p>
 * 其中 LineNumberTable:指令用于存储字节码行号和源码行号对应关系，即为：源码-->字节码
 * <p>
 * LocalVariableTable:是方法中使用到的局部变量表
 *
 * @author xym
 * @create 2019-04-04 14:36
 */
public class JvmByteCoder {

    private int a = 10;
    private static int b = 100;


    JvmByteCoder() {
        System.out.println("-----------------------init");
    }

    public static void main(String[] args) {
        // bipush -2 [加载-128~127之间的数]
        int a = -2;
        //iconst_m1 [加载-1~5的数]
        int b = -1;
        //sipush 20000 [加载short范围[-32768~32767]之间的数]
        int e = 20000;
        //ldc [加载int范围之间-2147483648~2147483647的数字]
        int f = 40000;
        //ldc
        int t = 40000;
        //ldc2_w
        long m = Long.MAX_VALUE;
        //ldc2_w
        double d = Double.MAX_VALUE;
        //ldc [加载int范围之间-2147483648~2147483647的数字或者字符串]
        String str = "AAA";
        //ldc [加载int范围之间-2147483648~2147483647的数字或者字符串]
        String chaina = "中国";
        System.out.println(Short.MAX_VALUE + "[--]" + Short.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + "[--]" + Integer.MIN_VALUE);
        //new
        Object obj = new Object();
        //newarray
        int[] array = new int[10];
        //ldc [加载int范围之间-2147483648~2147483647的数字或者字符串]
        long p = 100L;
        //使用l2i转换指令
        int pi = (int) p;
        // invokespecial(用于：调用实例初始化方法、私有方法、父类方法)
        JvmByteCoder coder = new JvmByteCoder();
        //赋值（写入） 使用putfield指令
        coder.a = 80;
        //访问（读取）
        //使用getfield指令
        System.out.println(coder.a);
        //使用putstatic指令
        JvmByteCoder.b = 1000;
        //使用getstatic指令
        System.out.println(JvmByteCoder.b);
        //iadd指令
        System.out.println(a + b);
        //isub指令
        System.out.println(a - b);
        //imul指令
        System.out.println(a * b);
        //idiv指令
        System.out.println(a / b);
        //instanceof指令
        System.out.println(Integer.valueOf(a) instanceof Number);
        //invokespecial(用于：调用实例初始化方法、私有方法、父类方法)
        coder.show("调用指令?");
        //invokevirtual(调用实例方法)
        System.out.println(coder.hashCode());
        // invokestatic（调用静态方法）
        JvmByteCoder.method1();
        coder.method2();
    }


    private void show(String str) {
        System.out.println("Show--" + str);
        //调用父类方法
        System.out.println(super.hashCode());
    }

    /**
     * 类静态方法（ return指令返回void类型）
     */
    private static void method1() {
        System.out.println(" static method ");
    }

    /**
     * 同步方法
     */
    private synchronized void method2() {
        System.out.println("sync method2");
    }
}
