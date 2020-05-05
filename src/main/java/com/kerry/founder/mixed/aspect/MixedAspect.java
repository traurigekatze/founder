package com.kerry.founder.mixed.aspect;

import com.alibaba.fastjson.JSON;
import com.kerry.founder.mixed.service.AnotherService;
import com.kerry.founder.mixed.service.MixedUserService;
import com.kerry.founder.mixed.service.MixedUserServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
@Aspect
public class MixedAspect {

    @DeclareParents(value = "com.kerry.founder.mixed.service.*", defaultImpl = MixedUserServiceImpl.class)
    public static MixedUserService mixedUserService;

    @Pointcut("execution(public * com.kerry.founder.mixed.dao.MixedUserDao.getInfo(..))")
    private void daoCut() {}

    @Before("daoCut()")
    private void daoBefore() {
        System.out.println("this is dao before...");
    }

    @Pointcut("@annotation(com.kerry.founder.mixed.annotation.MixedAnnotation)")
    private void serviceCut() {}

    @Before("serviceCut()")
    private void serviceBefore(JoinPoint joinPoint) {
        System.out.println("this is service before...");
        System.out.println("service params: " + JSON.toJSONString(joinPoint.getArgs()));
        System.out.println("=========================");
    }

}
