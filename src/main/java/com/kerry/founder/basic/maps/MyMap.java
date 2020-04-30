package com.kerry.founder.basic.maps;

/**
 * map interface
 * @author kerryhe
 * @date 2020/4/18
 */
public interface MyMap<K, V> {

    interface Entry<K, V> {

    }

    /**
     * returns the number of key-value mappings in this map
     * @return
     */
    int size();

    /**
     * return this map isEmpty
     * @return
     */
    boolean isEmpty();

    /**
     * return this map contains key
     * @param key
     * @return
     */
    boolean containKey(Object key);

    /**
     * return value if the key mapping
     * @param key
     * @return
     */
    V get(Object key);

    /**
     * put a key-value into this map
     * @param k
     * @param v
     */
    void put(K k, V v);

    /**
     * remove a key if this map contains
     * @param key
     */
    void remove(Object key);

    /**
     * removes all of the mappings from this map
     */
    void clear();

}
