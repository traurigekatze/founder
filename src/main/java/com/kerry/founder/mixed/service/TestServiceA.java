package com.kerry.founder.mixed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/16
 * **********学海无涯苦作舟**********
 */
@Service
public class TestServiceA {

    @Autowired
    private TestServiceB testServiceB;

    public TestServiceA() {
        System.out.println("this is testServiceA init,testServiceB:" + testServiceB);
    }

    public void doAction() {
        testServiceB.info();
    }

    public void info() {
        System.out.println("this is testServiceA info...");
    }

}
