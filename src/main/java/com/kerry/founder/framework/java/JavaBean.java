package com.kerry.founder.framework.java;

import com.kerry.founder.framework.BeanFactoryAbs;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
public class JavaBean extends BeanFactoryAbs {
    @Override
    public void say() {
        System.out.println("hi~ this is javaBean say hi...");
    }
}
