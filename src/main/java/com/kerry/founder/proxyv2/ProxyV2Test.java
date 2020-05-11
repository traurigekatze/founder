package com.kerry.founder.proxyv2;

import com.kerry.founder.proxy.service.UserService;
import com.kerry.founder.proxy.service.UserServiceImpl;
import com.kerry.founder.proxyv2.factory.ProxyFactoryV2;
import com.kerry.founder.proxyv2.handler.CustomInvocationHandler;
import com.kerry.founder.proxyv2.handler.ProxyHandler;

import java.lang.reflect.Proxy;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */
public class ProxyV2Test {

    public static void main(String[] args) throws Exception {
//        UserService instance = (UserService) Proxy.newProxyInstance(ProxyV2Test.class.getClassLoader(),
//                new Class[]{UserService.class}, new ProxyHandler(new UserServiceImpl()));
//        instance.info("kerry");

        UserService userService = (UserService) ProxyFactoryV2.newInstance(UserService.class, new CustomInvocationHandler(new UserServiceImpl()));
        userService.info("kerry");

        System.out.println();

        userService.sayHello("kerry", 1);

        System.out.println();

        userService.play();

    }
}
