package com.kerry.founder.proxy;

import com.kerry.founder.proxy.factory.ProxyFactory;
import com.kerry.founder.proxy.service.UserService;
import com.kerry.founder.proxy.service.impl.UserServiceImpl;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
//        // step1：simple say hello
//        UserService userService = new UserServiceImpl();
//        userService.sayHello("kerry");
//        System.out.println();
//
//        // step2：say hello before add "will be say hello..."
//        UserService willBeService = new WillBeServiceImpl(userService);
//        willBeService.sayHello("kerry");
//        System.out.println();
//
//        // step3：basic step2，say hello after add "nice to meet you..."
//        UserService niceService = new NiceServiceImpl(willBeService);
//        niceService.sayHello("kerry");
//        System.out.println();

        /**
         * 结论：
         * 静态代理分为两类：继承，聚合
         * 上述代码实例为：聚合方式
         *
         * 代理对象与目标对象 概念相对，一个代理对象也可以成为另一个代理对象的目标对象。
         * 聚合代理方式产生的类比继承会少一点（继承每次都需要新建一个类并重新对应增强方法）
         *
         * step4：通过 proxy factory 动态生成 代理对象，程序中不需要新建类
         */
        UserService newInstance = (UserService) ProxyFactory.newInstance(new UserServiceImpl());
        newInstance.sayHello("kerry", 11);
        System.out.println();
        System.out.println(newInstance.info("kerry"));
    }
}
