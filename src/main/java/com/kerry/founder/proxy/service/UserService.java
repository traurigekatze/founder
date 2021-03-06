package com.kerry.founder.proxy.service;

/**
 * @author kerryhe
 * @date 2020/5/9
 */
public interface UserService {

    /**
     * say hello
     * @param name
     * @param age
     * @throws Exception
     */
    void sayHello(String name, Integer age) throws Exception;

    /**
     * info
     * @param name
     * @return
     * @throws Exception
     */
    String info(String name) throws Exception;

    /**
     * play
     * @throws Exception
     */
    void play() throws Exception;

}
