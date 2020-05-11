package com.kerry.founder.proxyv2.aspect;

import com.kerry.founder.proxyv2.annotation.AfterAnnotation;
import com.kerry.founder.proxyv2.annotation.AspectAnnotation;
import com.kerry.founder.proxyv2.annotation.BeforeAnnotation;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/11
 * **********学海无涯苦作舟**********
 */
@AspectAnnotation
public class MyCustomAspect {

    @BeforeAnnotation("com.kerry.founder.proxy.service.impl.*.info()")
    public void doBefore() {
        System.out.println("this is info before annotation...");
    }


    @BeforeAnnotation("com.kerry.founder.proxy.service.impl.*.sayHello()")
    public void doBefore2() {
        System.out.println("this is sayHello before annotation...");
    }

    @AfterAnnotation("com.kerry.founder.proxy.service.impl.*.play()")
    public void doAfter() {
        System.out.println("this is after annotation...");
    }

}
