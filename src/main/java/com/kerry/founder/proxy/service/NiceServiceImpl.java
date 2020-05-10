package com.kerry.founder.proxy.service;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class NiceServiceImpl implements UserService {

    private UserService userService;

    public NiceServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void sayHello(String name) throws Exception {
        userService.sayHello("kerry");
        System.out.println("nice to meet you...");
    }

    @Override
    public String info(String name) throws Exception {
        System.out.println("nice info...");
        return "nice " + name;
    }
}