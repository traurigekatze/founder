package com.kerry.founder.framework.annotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author kerryhe
 * @date 2020/4/29
 */
@Component
//@Lazy
public class TestLazyBean {

    public TestLazyBean() {
        System.out.println("test lazy init...");
    }

    public void testLazy() {
        System.out.println("this is test lazy...");
    }

}
