package com.kerry.founder.proxyv2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectAnnotation {

    String value() default "";

}
