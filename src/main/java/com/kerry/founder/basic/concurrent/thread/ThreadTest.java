package com.kerry.founder.basic.concurrent.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author kerryhe
 * @date 2020/4/24
 */
@Slf4j
public class ThreadTest {

    static final Object obj = new Object();

    private static boolean flag = false;

    public static void main(String[] args) throws Exception {

        Thread consume = new Thread(new Consume(), "Consume");
        Thread produce = new Thread(new Produce(), "Produce");
        consume.start();
        Thread.sleep(1000);
        produce.start();
        try {
            produce.join();
            consume.join();
        } catch (InterruptedException e) {
            log.warn("thread join error:{}", e.getMessage(), e);
        }
    }

    static class Produce implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                log.info("enter produce...");
                try {
                    // step1:模拟生产过程
                    TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(1500, 3000));
                    log.info("do produce...");
                    flag = true;
                    // step2:通知消费者
                    obj.notify();
                    // step3:模拟其他耗时操作
                    TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(600, 1500));
                } catch (InterruptedException e) {
                    log.warn("timeunit sleep error:{}", e.getMessage(), e);
                }
                log.info("exit produce...");
            }
        }
    }

    static class Consume implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                log.info("enter consume...");
                log.info("wait flag 1:{}", flag);
                // 判断条件是否满足，若不满足则等待
                while (!flag) {
                    try {
                        log.info("produce not ok, do wait...");
                        obj.wait();
                        log.info("end wait...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("wait flag 2:{}", flag);
                log.info("do consume...");
                log.info("exit consume...");
            }
        }
    }


}
