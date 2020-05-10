package com.kerry.founder.proxy;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */

import com.kerry.founder.proxy.service.UserService;
import com.kerry.founder.proxy.service.UserServiceImpl;
import com.kerry.founder.proxyv2.handler.CustomInvocationHandler;
import com.kerry.founder.proxyv2.handler.MyInvocationHandler;
import java.lang.Exception;
import java.lang.reflect.Method;
public class $Proxy implements UserService {
    private MyInvocationHandler handler;
    public $Proxy(MyInvocationHandler handler) {
        this.handler = handler;
    }
    @Override
    public String info(String p0) throws Exception {
        Method method = Class.forName("com.kerry.founder.proxy.service.UserService").getDeclaredMethod("info");
        return (String) handler.invoke(method, new Object[]{p0});
    }
    @Override
    public void sayHello(String p0) throws Exception {
        Method method = Class.forName("com.kerry.founder.proxy.service.UserService").getDeclaredMethod("sayHello");
        handler.invoke(method, new Object[]{p0});
    }

    public static void main(String[] args) throws Exception {
        $Proxy proxy = new $Proxy(new CustomInvocationHandler(new UserServiceImpl()));
        proxy.info("info");
    }
}