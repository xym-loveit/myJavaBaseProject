package com.xym.myJava.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 演示了缓冲区的rewind（position为0，提供重复读取）、compact（将未读取的数据前移到数组靠前位置，即从0位置往后延续）、put、get方法
 * <p>
 * 注意：所有方法仅仅是修改了position或limit变量值，并不会对数组里面数据产生任何影响
 * <p>
 * compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。
 * limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据
 * <p>
 * mark:方法用来临时标记position的值，当下次再一个适当的时机调用reset方法可以恢复此position
 *
 * @author xym
 * @create 2018-11-16 16:21
 */
public class BufferReadDemo {
    public static void main(String[] args) {
        Charset latin1 = Charset.forName("ISO-8859-1");
        ByteBuffer buffer = ByteBuffer.wrap("hellojavt".getBytes());
        byte[] bytes = new byte[5];
        ByteBuffer buffer1 = buffer.get(bytes);
        System.out.println(new String(bytes));
        //此时缓冲区并未读完（消费完）
        System.out.println(buffer1);
        //position=0等同于回放，重复读取
        buffer.rewind();
        buffer.get(bytes);
        System.out.println(new String(bytes));
        //和clear功能相当，只是clear会将position=0，limit=capacity,会忽略剩下的数据
        //compact则不会,将会保留java四个字符,并且将放在数字前面此时position为8
        buffer.compact();
        buffer.put("abcdf".getBytes());//所以此时值为javtabcdf
        System.out.println("---" + buffer);
        buffer.rewind();// position = 0，limit不变
        //CharBuffer charBuffer = latin1.decode(buffer);
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        // position = 0;
        // limit = capacity;
        //忽略所有数据,为装数据做准备
        buffer.clear();
        buffer.put("1234567".getBytes());
        buffer.rewind();//为读取数据做准备，重置position为0,limit不变
        System.out.println("\n" + latin1.decode(buffer).toString());
        System.out.println(buffer);
        buffer.rewind();
        buffer.get(new byte[2]);//读取2个字节
        System.out.println(buffer);
        buffer.mark();//标记position
        buffer.position(5);//重设position为5
        System.out.println(latin1.decode(buffer).toString());
        buffer.reset();//reset后mark恢复为上次标记,为2，继续读取
        System.out.println(buffer);
        System.out.println(latin1.decode(buffer).toString());
    }
}
