package com.kerry.founder.framework.annotation;

import com.kerry.founder.framework.BeanFactoryAbs;
import org.springframework.stereotype.Component;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
@Component
public class AnnotationBean extends BeanFactoryAbs {
    @Override
    public void say() {
        System.out.println("hi~ this is annotationBean say hi...");
    }
}
