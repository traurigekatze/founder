package com.kerry.founder.basic.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author kerryhe
 * @date 2020/4/23
 */
@Slf4j
public class Driver {

    public static void main(String[] args) {
        doWork();
    }

    /**
     * do work
     */
    private static void doWork() {
        int worker = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(worker);
        log.info("worker start work...");
        for (int i = 0; i < worker; i++) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }
        startSignal.countDown();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            log.warn("worker await error:{}", e.getMessage(), e);
        }
        log.info("work is done...");
    }

}
