package com.xym.myJava;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 打包执行一组测试
 *
 * @author xym
 * @create 2018-07-03 17:25
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        /*打包执行一组测试*/
        MyTest.class,
        MyTest2.class
})
public class SuiteTest {


}
