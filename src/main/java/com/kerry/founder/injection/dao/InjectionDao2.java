package com.kerry.founder.injection.dao;

import org.apache.commons.lang3.RandomUtils;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/16
 * **********学海无涯苦作舟**********
 */
public class InjectionDao2 implements InjectDao {

    @Override
    public Integer random() {
        int number = RandomUtils.nextInt(1, 999);
        System.out.println(String.format("this is injectionDao2 random：%s...", number));
        return number;
    }
}
