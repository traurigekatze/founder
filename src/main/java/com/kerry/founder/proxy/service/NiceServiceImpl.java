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
    public void sayHello(String name) {
        userService.sayHello("kerry");
        System.out.println("nice to meet you...");
    }
}
