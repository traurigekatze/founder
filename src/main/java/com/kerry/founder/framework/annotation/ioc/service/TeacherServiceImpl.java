package com.kerry.founder.framework.annotation.ioc.service;

import org.springframework.stereotype.Service;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
@Service
public class TeacherServiceImpl implements PersonalService {

    @Override
    public void introduction() {
        System.out.println("this is teacher introduction...");
    }
}
