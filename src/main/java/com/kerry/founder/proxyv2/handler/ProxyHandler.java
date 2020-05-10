package com.kerry.founder.proxyv2.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */
public class ProxyHandler implements InvocationHandler {

    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy handler jdk...");
        return method.invoke(target, args);
    }

}
