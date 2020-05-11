package com.kerry.founder.proxyv2;
import com.kerry.founder.proxy.service.impl.UserServiceImpl;
import com.kerry.founder.proxyv2.handler.CustomInvocationHandler;
import com.kerry.founder.proxyv2.handler.MyInvocationHandler;
import com.kerry.founder.proxy.service.UserService;
import java.lang.Exception;
import java.lang.reflect.Method;
public class $Proxy implements UserService {
    private MyInvocationHandler handler;
    public $Proxy(MyInvocationHandler handler) {
        this.handler = handler;
    }
    @Override
    public String info(String p0) throws Exception {
        Method method = Class.forName("com.kerry.founder.proxy.service.UserService").getDeclaredMethod("info", String.class);
        return (String)handler.invoke(method, new Object[]{p0});
    }
    @Override
    public void sayHello(String p0, Integer p1) throws Exception {
        Method method = Class.forName("com.kerry.founder.proxy.service.UserService").getDeclaredMethod("sayHello", String.class, Integer.class);
        handler.invoke(method, new Object[]{p0, p1});
    }
    @Override
    public void play() throws Exception {
        Method method = Class.forName("com.kerry.founder.proxy.service.UserService").getDeclaredMethod("play");
        handler.invoke(method, new Object[]{});
    }

    public static void main(String[] args) throws Exception {
        $Proxy proxy = new $Proxy(new CustomInvocationHandler(new UserServiceImpl(), "com.kerry.founder.proxyv2.aspect"));
        proxy.info("kerry");
    }
}