package com.kerry.founder.custom.service;

import com.kerry.founder.custom.annotation.MyAnnotation;
import org.springframework.stereotype.Service;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/3
 * **********学海无涯苦作舟**********
 */
@Service
public class UserServiceImpl implements UserSerivce {

    @Override
    @MyAnnotation
    public void info(Object object) {
        Class<?> clazz = object.getClass();
        if (clazz.isAnnotationPresent(MyAnnotation.class)) {
            System.out.println("is my annotation...");
        } else {
            System.out.println("not is my annotation...");
        }
    }
}
