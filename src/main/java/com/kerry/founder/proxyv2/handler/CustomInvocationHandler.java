package com.kerry.founder.proxyv2.handler;

import java.lang.reflect.Method;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */
public class CustomInvocationHandler implements MyInvocationHandler {

    private Object target;
    public CustomInvocationHandler(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Method method, Object[] args) throws Exception {
        System.out.println("is custom invocation handler...");
        return method.invoke(target, args);
    }

}
