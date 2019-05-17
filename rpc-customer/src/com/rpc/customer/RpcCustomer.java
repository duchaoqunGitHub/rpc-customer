package com.rpc.customer;

import com.rpc.handle.ServiceInvokeHandle;

import java.lang.reflect.Proxy;

/**
 * @日期: 2019-05-16 14:44
 * @作者: 杜超群
 * @描述:
 */
public class RpcCustomer {

    public Object getService(Class clazz){
        ServiceInvokeHandle serviceInvokeHandle = new ServiceInvokeHandle("127.0.0.1",9999);
        return Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{clazz},serviceInvokeHandle);
    }

}
