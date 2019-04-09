package com.xym.myJava.head_first._03;

import java.io.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-09 17:27
 */
public class DemoTest {
    public static void main(String[] args) {
        //test01();
    }

    private static void test01() {
        try {
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\resources\\file.txt")));
            int b = -1;
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
