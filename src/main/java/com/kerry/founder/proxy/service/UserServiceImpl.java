package com.kerry.founder.proxy.service;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public class UserServiceImpl implements UserService {

    @Override
    public void sayHello(String name) {
        System.out.println(String.format("this is %s say hello...", name));
    }
}
