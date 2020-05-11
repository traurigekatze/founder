package com.kerry.founder.proxyv2.handler;

import com.kerry.founder.proxyv2.annotation.AfterAnnotation;
import com.kerry.founder.proxyv2.annotation.AspectAnnotation;
import com.kerry.founder.proxyv2.annotation.BeforeAnnotation;
import com.kerry.founder.proxyv2.aspect.MyCustomAspect;
import com.kerry.founder.proxyv2.util.ClassUtil;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/10
 * **********学海无涯苦作舟**********
 */
public class CustomInvocationHandler implements MyInvocationHandler {

    private Object target;

    private String pkg;

    private Class<MyCustomAspect> customAspectClass;

    private MyCustomAspect customAspect;

    private Map<String, Map<String, String>> methodsMap;

    public CustomInvocationHandler(Object target) {
        this.target=target;
    }

    public CustomInvocationHandler(Object target, String pkg) {
        this.target=target;
        this.pkg = pkg;
        init();
    }

    private void init() {
        List<Class<?>> classes = ClassUtil.getClasses(pkg);
        if (CollectionUtils.isEmpty(classes)) {
            return;
        }
        try {
            for (Class<?> clazz : classes) {
                if (clazz.getAnnotation(AspectAnnotation.class) != null) {
                    customAspectClass = (Class<MyCustomAspect>) clazz;
                    customAspect = customAspectClass.newInstance();
                    break;
                }
            }
            if (customAspectClass == null) {
                return;
            }
            methodsMap = ClassUtil.getMethod(customAspect);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("获取切面信息异常：" + e);
        } finally {
            if (methodsMap == null) {
                methodsMap = new HashMap<>();
            }
        }
    }

    @Override
    public Object invoke(Method method, Object[] args) throws Exception {
        System.out.println("is custom invocation handler...");
        String pth = target.getClass().getName() + "." + method.getName();
        if (methodsMap.get("before").keySet().contains(pth)) {
            execute(methodsMap.get("before").get(pth), "before");
        }
        Object result = method.invoke(target, args);
        if (methodsMap.get("after").keySet().contains(pth)) {
            execute(methodsMap.get("after").get(pth), "after");
        }
        System.out.println("custom invocation handler is over...");
        return result;
    }

    private void execute(String pth, String type) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = customAspectClass.getDeclaredMethods();
        for (Method method : methods) {
            if ("before".equals(type)) {
                BeforeAnnotation[] annotations = method.getAnnotationsByType(BeforeAnnotation.class);
                for (BeforeAnnotation annotation : annotations) {
                    if (annotation.value().equals(pth)) {
                        method.invoke(customAspect, new Object[]{});
                    }
                }
            } else {
                AfterAnnotation[] annotations = method.getAnnotationsByType(AfterAnnotation.class);
                for (AfterAnnotation annotation : annotations) {
                    if (annotation.value().equals(pth)) {
                        method.invoke(customAspect, new Object[]{});
                    }
                }
            }
        }
    }

}
