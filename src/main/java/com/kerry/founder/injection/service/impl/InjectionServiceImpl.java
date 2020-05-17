package com.kerry.founder.injection.service.impl;

import com.kerry.founder.injection.dao.InjectDao;
import com.kerry.founder.injection.dao.InjectionDao1;
import com.kerry.founder.injection.service.InjectionService;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/16
 * **********学海无涯苦作舟**********
 */
public class InjectionServiceImpl implements InjectionService {

    private InjectDao injectionDao1;

    private String name;

    @Override
    public void query() {
        Integer val = injectionDao1.random();
        System.out.println(String.format("random int val:%s...", val));
        System.out.println(String.format("name:%s", name));
    }
}
