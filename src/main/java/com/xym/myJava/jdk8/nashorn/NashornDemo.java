package com.xym.myJava.jdk8.nashorn;

import com.xym.myJava.Person;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-11 10:25
 */
public class NashornDemo {
    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        //ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        //scriptEngine.eval("print('Hello World!');");
        //ScriptEngine engine = new ScriptEngineManager().getEngineByName(
        //        "nashorn");
        //engine.eval(new FileReader("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\resources\\script.js"));
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(
                    "nashorn");
            engine.eval(new FileReader("D:\\workspace\\IdeaProjects\\myJavaBaseProject\\src\\main\\resources\\script.js"));
            Invocable invocable = (Invocable) engine;
            Object result = null;

            result = invocable.invokeFunction("fun1", "Peter Parker");
            System.out.println(result);
            System.out.println(result.getClass());
            invocable.invokeFunction("fun2", new Date());
            invocable.invokeFunction("fun2", LocalDateTime.now());
            invocable.invokeFunction("fun2", new Person());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个静态方法
     *
     * @param name
     * @return
     */
    static String fun1(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }
}
