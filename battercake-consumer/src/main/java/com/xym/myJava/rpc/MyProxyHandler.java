package com.xym.myJava.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-03 17:01
 */
public class MyProxyHandler implements InvocationHandler {
    private String ip;
    private int port;

    public MyProxyHandler(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(this.ip, this.port);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        try {
            output.writeObject(proxy.getClass().getInterfaces()[0]);
            output.writeUTF(method.getName());
            output.writeObject(method.getParameterTypes());
            output.writeObject(args);
            output.flush();
            Object result = input.readObject();
            if (result instanceof Throwable) {
                throw (Throwable) result;
            }
            return result;
        } finally {
            socket.shutdownOutput();
        }
    }
}
