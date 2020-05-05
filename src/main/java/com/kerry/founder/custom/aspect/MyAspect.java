package com.kerry.founder.custom.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/4
 * **********学海无涯苦作舟**********
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(public * com.kerry.founder.custom.service.UserServiceImpl.info(..))")
    public void pointCut() {}

    @Before("execution(public * com.kerry.founder.custom.*.*.*(..))")
    private void doAccessCheck() {
        System.out.println("is do access check...");
    }

    @After("@annotation(com.kerry.founder.custom.annotation.MyAnnotation)")
    private void doProcessCheck() {
        System.out.println("is do process check...");
    }


}
