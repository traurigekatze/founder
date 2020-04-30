package com.kerry.founder.basic.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author kerryhe
 * @date 2020/4/23
 */
@Slf4j
public class Worker implements Runnable {

    private final CountDownLatch startSignal;

    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }
    @Override
    public void run() {
        try {
            startSignal.await();
            doWork();
        } catch (InterruptedException e) {
            log.warn("worker await...");
        } finally {
            doneSignal.countDown();
        }
    }

    void doWork() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.warn("worker work error...");
        }
        log.info("worker {} do work is done", Thread.currentThread().getName());
    }
}
