package com.kerry.founder.basic.maps;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试map some 操作方法
 * @author kerryhe
 * @date 2020/4/17
 */
@Slf4j
public class TestMaps {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Map<String, String> map = new HashMap<>(2);
        // 获取map扩容时临界阈值  阈值 = 容量 * 加载因子
        // 默认容量 为16，加载因子 默认为0.75
        Field threshold = map.getClass().getDeclaredField("threshold");
        Field size = map.getClass().getDeclaredField("size");
        Method capacity = map.getClass().getDeclaredMethod("capacity");
        threshold.setAccessible(true);
        size.setAccessible(true);
        capacity.setAccessible(true);
        // 未存放对象时，各项值测试
        System.out.println("start:临界值" + threshold.get(map));
        System.out.println("start:size" + size.get(map));
        System.out.println("start:容量" + capacity.invoke(map));
        map.put("1", "b");
        map.put("2", "d");
        map.put("3", "f");
        System.out.println("=========");
        // 未存放对象时，各项值测试
        System.out.println("start:临界值" + threshold.get(map));
        System.out.println("start:size" + size.get(map));
        System.out.println("start:容量" + capacity.invoke(map));
        map.put("4", "b");
        map.put("5", "d");
        System.out.println("=========");
        // 未存放对象时，各项值测试
        System.out.println("start:临界值" + threshold.get(map));
        System.out.println("start:size" + size.get(map));
        System.out.println("start:容量" + capacity.invoke(map));
        map.put("6", "d");
        map.put("7", "d");
        System.out.println("=========");
        // 未存放对象时，各项值测试
        System.out.println("start:临界值" + threshold.get(map));
        System.out.println("start:size" + size.get(map));
        System.out.println("start:容量" + capacity.invoke(map));
    }
}
