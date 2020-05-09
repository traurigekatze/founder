package com.kerry.founder.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kerryhe
 * @date 2020/5/8
 */
public class TestSynHandler {

    private static void syn() {

        ArrayList<String> list = new ArrayList<>(1000);
        for (int i = 0; i < 5000; i++) {
            list.add("aaa-" + i);
        }
        System.out.println("list.size==" + list.size());

        List<String> stirs = Collections.synchronizedList(new ArrayList<>(1000));
        CopyOnWriteArrayList copy = new CopyOnWriteArrayList<String>();

        long start = System.currentTimeMillis();
        list.parallelStream().forEach(str -> stirs.add(str));
        System.out.println("stirs use time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        list.parallelStream().forEach(str -> copy.add(str));
        System.out.println("copy use time:" + (System.currentTimeMillis() - start));

        System.out.println("stirs.size==" + stirs.size());
        System.out.println("copy.size==" + copy.size());

        start = System.currentTimeMillis();
        AtomicInteger j = new AtomicInteger(0);
        stirs.forEach(str -> j.incrementAndGet());
        System.out.println("stirs use time:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        AtomicInteger i = new AtomicInteger(0);
        copy.forEach(str -> i.incrementAndGet());
        System.out.println("copy use time:" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        syn();
    }
}
