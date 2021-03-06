package com.kerry.founder.mixed.dao;

import com.kerry.founder.mixed.domain.MixedUser;
import org.apache.commons.lang3.RandomUtils;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
public class MixedUserDao {

    private MixedUserDao2 userDao2;

    private String a;

    private Integer b;

    public MixedUserDao(String a, Integer b) {
        this.a = a;
        this.b = b;
        System.out.println("a+bbb:" + a + b);
    }

    public MixedUserDao(Integer a, String b) {
        this.a = b;
        this.b = a;
        System.out.println("a+aab:" + a + b);
    }

    public void setUserDao2(MixedUserDao2 userDao2) {
        this.userDao2 = userDao2;
    }

    public MixedUser getInfo(Integer id) {
        userDao2.info();
        if (id == null) {
            return new MixedUser(1, "kerry", "1996-09-10", "man");
        }
        return id % 2 == 0 ? new MixedUser(id, "kerry", "1996-09-10", "man") : new MixedUser(id, "k1rry", "09-10-1996", "man");
    }

}
