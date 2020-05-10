package com.kerry.founder.proxyv2.handler;

import java.lang.reflect.Method;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */
public interface MyInvocationHandler {

    /**
     * invoke
     * @param method
     * @param args
     * @return
     * @throws Exception
     */
    Object invoke(Method method, Object[] args) throws Exception;

}
