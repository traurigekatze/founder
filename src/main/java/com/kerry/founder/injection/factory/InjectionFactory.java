package com.kerry.founder.injection.factory;

import com.alibaba.fastjson.JSON;
import com.kerry.founder.injection.exception.BeanException;
import com.kerry.founder.injection.exception.ElementException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/16
 * **********学海无涯苦作舟**********
 */
public class InjectionFactory {

    private static final String CONSTRUCTOR = "constructor-arg";

    private static final String PROPERTY = "property";

    private static final String BY_NAME = "byName";

    private static final String BY_TYPE = "byType";

    private Map<String, Object> beanMap = new HashMap<>();

//    private Map<String, Object> waitMap = new HashMap<>();

    private Set<String> allClazzSet = new HashSet<>();

    public InjectionFactory(String xml) {
        init(xml);
    }

    /**
     * 初始化 xml 中配置的bean
     * 1、支持自动注入：default-autowire=byName/byType
     * 2、支持 setter、constructor 两种注入方式
     * @param xml
     */
    private void init(String xml) {
        String path = this.getClass().getResource("/").getPath();
        File file = new File(path + "//" + xml);
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new ElementException(e.getMessage());
        }
        // step1：获取 beans 节点
        Element root = document.getRootElement();
        // step1.2：如果配置了自动注入方式则记录
        Attribute autowireAttr = root.attribute("default-autowire");
        String autowireType = getAttrVal(autowireAttr);
        // step2：解析 bean 节点
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element beanEle = it.next();
            initBean(beanEle, autowireType);
        }

    }

    private void initBean(Element beanEle, String autowireType) {
        Attribute idAttr = beanEle.attribute("id");
        String id = getAttrVal(idAttr);
        if (StringUtils.isBlank(id)) {
            throw new ElementException("element id must be not null");
        }
        Attribute classAttr = beanEle.attribute("class");
        String clazzAttr = getAttrVal(classAttr);
        if (StringUtils.isBlank(clazzAttr)) {
            throw new ElementException("element class must be not null");
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(clazzAttr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ElementException(e.getMessage());
        }
        allClazzSet.add(clazz.getInterfaces().length > 0 ? clazz.getInterfaces()[0].getName() : clazz.getName());
        // **** 手动注入优先级高于自动注入
        // step1：判断是否有手动注入
        List<Element> elements = beanEle.elements();
        Object instance = null;
        if (!CollectionUtils.isEmpty(elements)) {
            Map<String, Object> constructorMap = new HashMap<>();
            Map<String, Object> propertyMap = new HashMap<>();
            elements.forEach(element -> {
                String name = getAttrVal(element.attribute("name"));
                String value = getAttrVal(element.attribute("value"));
                String ref = getAttrVal(element.attribute("ref"));
                if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(ref)) {
                    throw new ElementException("xml 中 value 和 ref 不能同时有值");
                }
                Object val = StringUtils.isNotBlank(value) ? value : beanMap.get(ref);
                if (CONSTRUCTOR.equals(element.getName())) {
                    constructorMap.put(name, val);
                } else if (PROPERTY.equals(element.getName())) {
                    propertyMap.put(name, val);
                }
            });
            if (!CollectionUtils.isEmpty(constructorMap)) {
                // 有参构造
                int size = constructorMap.keySet().size();
                Object[] parametersCon = new Object[size];
                try {
                    Constructor<?>[] constructors = clazz.getConstructors();
                    Constructor realConstructor = null;
                    for (Constructor<?> constructor : constructors) {
                        if (size == constructor.getParameterCount()) {
                            Parameter[] parameters = constructor.getParameters();
                            for (int i = 0; i < parameters.length; i++) {
                                Parameter parameter = parameters[i];
                                if (!constructorMap.containsKey(parameter.getName())) {
                                    break;
                                }
                                Object o = constructorMap.get(parameter.getName());
                                if (!constructorMap.get(parameter.getName()).getClass().getName().equals(parameter.getType().getName())) {
                                    break;
                                }
                                parametersCon[i] = constructorMap.get(parameter.getName());
                                if (i == size-1) {
                                    realConstructor = constructor;
                                }
                            }
                        }
                    }
                    if (realConstructor == null) {
                        throw new BeanException(id + "未匹配构造方法，需要参数" + size);
                    }
                    instance = realConstructor.newInstance(parametersCon);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BeanException(e.getMessage());
                }
            }
            if (!CollectionUtils.isEmpty(propertyMap)) {
                try {
                    if (instance == null) {
                        instance = clazz.newInstance();

                    }
                    for (String key : propertyMap.keySet()) {
                        Field field = clazz.getDeclaredField(key);
                        field.setAccessible(true);
                        field.set(instance, propertyMap.get(key));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BeanException(e.getMessage());
                }
            }
        } else if (StringUtils.isNotBlank(autowireType)) {
            if (!autowireType.equals(BY_NAME) && !autowireType.equals(BY_TYPE)) {
                throw new ElementException("未知的autowire_type属性");
            }
            try {
                instance = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                boolean flag = false;
                if (fields != null) {
                    if (BY_NAME.equals(autowireType)) {
                        for (Field field : fields) {
                            if (allClazzSet.contains(field.getType().getName())) {
                                Object injectObj = beanMap.get(field.getName());
                                if (injectObj == null && !flag) {
                                    flag = true;
                                }
                                field.setAccessible(true);
                                field.set(instance, injectObj);
                            }
                        }
                    } else {
                        for (Field field : fields) {
                            Class<?> fieldType = field.getType();
                            if (!allClazzSet.contains(fieldType.getName())) {
                                continue;
                            }
                            int cnt = 0;
                            Object injectObj = null;
                            for (String key : beanMap.keySet()) {
                                Class<?>[] interfaces = beanMap.get(key).getClass().getInterfaces();
                                Class<?> temp = interfaces != null && interfaces.length > 0 ? interfaces[0] : beanMap.get(key).getClass();
                                if (temp.getName().equals(fieldType.getName())) {
                                    injectObj = beanMap.get(key);
                                    cnt++;
                                }
                            }
                            if (cnt > 1 || cnt == 0) {
                                throw new BeanException(String.format("需要一个%s，但找到了%s个", field.getName(), cnt));
                            }
                            if (injectObj == null && !flag) {
                                flag = true;
                            }
                            field.setAccessible(true);
                            field.set(instance, injectObj);
                        }
                        if (flag) {
//                            waitMap.put(id, instance);
                            throw new BeanException(String.format("%s not found", id));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeanException(e.getMessage());
            }
        } else {
            // xml 没有配置依赖 且 没有开启自动注入
            try {
                instance = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeanException(e.getMessage());
            }
        }
        if (beanMap.containsKey(id)) {
            throw new BeanException(String.format("重复定义的bean id %s", id));
        }
        beanMap.put(id, instance);
    }

    /**
     * 获取属性value
     * @param attribute
     * @return
     */
    private String getAttrVal(Attribute attribute) {
        return (attribute == null) ? "" : attribute.getValue();
    }

    /**
     * 获取 bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    public <T> T getBean(String name, Class<T> requiredType) {
        Object bean = beanMap.get(name);
        if (bean == null) {
            String msg = "not found bean %s";
            throw new BeanException(String.format(msg, requiredType.getName()));
        }
        if (!requiredType.isInstance(bean)) {
            String msg = "requiredType is %s,but found %s";
            throw new BeanException(String.format(msg, requiredType.getName(), bean.getClass().getName()));
        }
        return (T) bean;
    }

}
