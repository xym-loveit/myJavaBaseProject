package com.xym.myJava.jvms;

/**
 * 通过javap -verbose 类名.class
 * <p>
 * 查看字节码指令，分析try catch finally执行顺序
 *
 * @author xym
 * @create 2019-04-04 17:20
 */
public class FinallyDemo {
    public static void main(String[] args) {
        System.out.println(finallyNotWork());
    }

    public static int finallyNotWork() {
        int temp = 1000;
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("------------------------22222");
            //返回之前先存储++temp
            return ++temp;
        } finally {
            System.out.println("----------------1111--" + temp);
            //被存储在另一个slot上面,和之前temp属于不同的slot
            //在idea里面可以看见背景色为never userd，就知道这里的操作是无效的
            temp = 999;
        }
    }
}

/**
 * 0: sipush        1000  将一个短整型常量(-32768~32767)推送至栈顶
 * 3: istore_0    将栈顶int型数值存入第一个本地变量表
 * 4: new           #3                  // class java/lang/Exception  创建一个对象, 并将其引用引用值压入栈顶
 * 7: dup  复制栈顶数值并将复制值压入栈顶
 * 8: invokespecial #4                  // Method java/lang/Exception."<init>":()V 调用超类构建方法, 实例初始化方法, 私有方法
 * 11: athrow 将栈顶的异常抛出
 * 12: astore_1 将栈顶引用型数值存入第二个本地变量
 * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * 16: iload_0 将第一个int型本地变量推送至栈顶
 * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * 18: sipush        999  将一个短整型常量(-32768~32767)推送至栈顶
 * 21: istore_0 将栈顶int型数值存入第一个本地变量
 * 22: iload_2 将第三个int型本地变量推送至栈顶
 * 23: ireturn 从当前方法返回int
 * 24: astore_3 将栈顶引用型数值存入第四个本地变量
 * 25: sipush        999
 * 28: istore_0
 * 29: aload_3
 * 30: athrow
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 * <p>
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 * <p>
 * <p>
 * * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 * * 16: iload_0 将第一个int型本地变量推送至栈顶
 * * 17: istore_2 将栈顶int型数值存入第三个本地变量
 * <p>
 * * 22: iload_2 将第三个int型本地变量推送至栈顶
 * * 23: ireturn 从当前方法返回int
 */
//////////////////////////////////////////////////////////////////////////////////////
/**
 * 省略无关的字节码指令，可以看出这里返回的是++temp=1001而不是999
 *
 *
 *  * 13: iinc          0, 1  将指定int型变量增加指定值(如i++, i--, i+=2等)
 *  * 16: iload_0 将第一个int型本地变量推送至栈顶
 *  * 17: istore_2 将栈顶int型数值存入第三个本地变量
 *
 *  * 22: iload_2 将第三个int型本地变量推送至栈顶
 *  * 23: ireturn 从当前方法返回int
 *
 */
//////////////////////////////////////////////////////////////////////////////////////