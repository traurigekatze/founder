package com.kerry.founder.basic.maps;

import com.kerry.founder.basic.maps.MyMap.Entry;

import java.io.Serializable;
import java.util.Map;

/**
 * @author kerryhe
 * @date 2020/4/18
 */
public class MyHashMap<K,V> implements MyMap<K,V>, Serializable {

    /**
     * 默认初始大小：16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 默认加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 记录 map size，transient：让被修饰的成员属性变量不被序列化
     */
    transient int size;

    /**
     * 扩容阈值
     */
    int threshold;

    /**
     * 加载因子
     */
    final float loadFactor;

    /**
     * 第一次使用时初始化，分配的长度为2的幂
     */
    transient Node<K, V>[] table;

    static class Node<K,V> implements MyMap.Entry<K,V> {

    }

    /**
     * 构建一个空map
     */
    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    /**
     * 构建一个空map并规定初始大小
     * @param initialCapacity 初始大小
     */
    public MyHashMap(int initialCapacity) {
        this.size = initialCapacity;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    /**
     * 构建一个空map并规定初始大小和加载因子
     * @param size
     * @param loadFactor
     */
    public MyHashMap(int size, float loadFactor) {
        this.size = size;
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回传入key对应的hashCode
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        // h = key.hashCode()
        // h ^ (h >>> 16)
        // ^：如果相对应位值相同，则结果为0，否则为1
        // h >>> 16 ：按位右移补零操作符。左操作数的值按右操作数指定的位数右移，移动得到的空位以零填充。
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final Node<K, V> getNode(int hash, Object key) {
        Node<K, V>[] tab; Node<K, V> first, e; int n; K k;
        // step1: check table is not null && table.len > 0
        if ((tab = table) != null && (n = table.length) > 0
                && (first = tab[(n - 1) & hash]) != null) {

        }
        return null;
    }

    @Override
    public boolean containKey(Object key) {
        hash(key);
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public void put(Object o, Object o2) {

    }

    @Override
    public void remove(Object key) {

    }

    @Override
    public void clear() {

    }
}
