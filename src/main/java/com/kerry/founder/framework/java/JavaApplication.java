package com.kerry.founder.framework.java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
public class JavaApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        JavaBean javaBean = context.getBean("javaBean", JavaBean.class);
        javaBean.say();
    }

}
