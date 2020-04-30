package com.kerry.founder.framework.annotation;

import com.kerry.founder.framework.annotation.ioc.service.PersonalController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
public class AnnotationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        AnnotationBean annotationBean = context.getBean(AnnotationBean.class);
        annotationBean.say();
        PersonalController personalController = context.getBean(PersonalController.class);
        personalController.introduction();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestLazyBean lazyBean = context.getBean(TestLazyBean.class);
        lazyBean.testLazy();
    }

}
