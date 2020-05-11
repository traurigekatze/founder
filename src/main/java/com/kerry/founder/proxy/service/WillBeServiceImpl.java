package com.kerry.founder.proxy.service;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class WillBeServiceImpl implements UserService {

    private UserService userService;

    public WillBeServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void sayHello(String name, Integer age)  throws Exception {
        System.out.println("will be say hello...");
        userService.sayHello(name, age);
    }

    @Override
    public String info(String name)  throws Exception {
        System.out.println("will be info...");
        return "will " + name;
    }

    @Override
    public void play() throws Exception {
        System.out.println("will be play sth...");
    }
}
