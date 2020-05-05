package com.kerry.founder.mixed.service;

import com.kerry.founder.mixed.annotation.MixedAnnotation;
import com.kerry.founder.mixed.dao.MixedUserDao;
import com.kerry.founder.mixed.domain.MixedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
@Service("mixedUserService")
public class MixedUserServiceImpl implements MixedUserService {

    @Autowired
    private MixedUserDao mixedUserDao;

    @Override
    @MixedAnnotation()
    public void info(Integer id) {
        MixedUser info = mixedUserDao.getInfo(id);
        System.out.println(info.toString());
    }

    @Override
    public void introduction() {
        System.out.println("this is introduction...");
    }
}
