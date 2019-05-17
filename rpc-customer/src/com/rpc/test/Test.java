package com.rpc.test;

import com.rpc.customer.RpcCustomer;
import com.rpc.service.UserService;

/**
 * @日期: 2019-05-17 12:02
 * @作者: 杜超群
 * @描述:
 */
public class Test {
    public static void main(String[] args) {
        RpcCustomer customer = new RpcCustomer();
        UserService userService = (UserService) customer.getService(UserService.class);
        String result = userService.test(222);
        System.out.println("返回结果："+result);
    }
}
