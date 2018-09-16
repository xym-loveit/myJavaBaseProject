package com.xym.myJava.nashorn;

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
 * JavaScript和Java交互
 *
 * @author xym
 * @create 2018-09-14 14:06
 */
public class NashornDemo {
    public static void main(String[] args) {
       /* ScriptEngine nashorn =
                new ScriptEngineManager().getEngineByName("nashorn");
        try {
            nashorn.eval("print('Hello World!');");
        } catch (ScriptException e) {
            e.printStackTrace();
        }*/

        //为了调用函数，你首先需要将脚本引擎转换为 Invocable 。 Invocable 接口
        //由 NashornScriptEngine 实现，并且定义了 invokeFunction 方法来调用指定
        //名称的JavaScript函数。


        ScriptEngine engine = new ScriptEngineManager().getEngineByName(
                "nashorn");
        try {
            engine.eval(new FileReader("src/first.js"));

            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("fun1", "xym");
            System.out.println(result);
            System.out.println(result.getClass());


            invocable.invokeFunction("fun2", new Date());
// [object java.util.Date]
            invocable.invokeFunction("fun2", LocalDateTime.now());
// [object java.time.LocalDateTime]
            invocable.invokeFunction("fun2", new Person("xym", "loveit"));

        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static String fun1(String name) {
        System.out.format("这是Java的地盘，%s", name);
        return "Java端返回了！";
    }
}
