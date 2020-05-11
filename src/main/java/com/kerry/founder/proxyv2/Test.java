package com.kerry.founder.proxyv2;

import com.alibaba.fastjson.JSON;
import com.kerry.founder.proxyv2.annotation.AfterAnnotation;
import com.kerry.founder.proxyv2.annotation.AspectAnnotation;
import com.kerry.founder.proxyv2.annotation.BeforeAnnotation;
import com.kerry.founder.proxyv2.aspect.MyCustomAspect;
import com.kerry.founder.proxyv2.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/11
 * **********学海无涯苦作舟**********
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        List<Class<?>> classes = ClassUtil.getClasses("com.kerry.founder.proxyv2.aspect");
        if (CollectionUtils.isEmpty(classes)) {
            System.out.println("classes is null...");
            return;
        }
        Class<MyCustomAspect> aspect = null;
        for (Class<?> clazz : classes) {
            if (clazz.getAnnotation(AspectAnnotation.class) != null) {
                aspect = (Class<MyCustomAspect>) clazz;
                break;
            }
        }
        if (aspect == null) {
            System.out.println("aspect is null...");
            return;
        }
        System.out.println(aspect);

        Map<String, Map<String, String>> methodsMap = ClassUtil.getMethod(aspect.newInstance());
        System.out.println(JSON.toJSON(methodsMap));

    }




}
