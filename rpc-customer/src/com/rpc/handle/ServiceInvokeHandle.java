package com.rpc.handle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @日期: 2019-05-16 16:41
 * @作者: 杜超群
 * @描述:
 */
public class ServiceInvokeHandle implements InvocationHandler {

    public ServiceInvokeHandle(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private String ip;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(ip,port);
        //防止流阻塞客户端设置为 先 输出流后 输入流
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        //写入当前代理类的接口Class对象
        out.writeObject(proxy.getClass().getInterfaces()[0]);
        //写入方法名称
        out.writeObject(method.getName());
        //写入参数类型
        out.writeObject(method.getParameterTypes());
        //写入参数值
        out.writeObject(args);
        //通知服务端客户端已写完数据
        socket.shutdownOutput();
        //输出返回信息
        Object object = in.readObject();
        System.out.println(object);
        //关闭socket连接
        socket.close();
        return object;
    }
}
