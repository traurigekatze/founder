package com.kerry.founder.framework.annotation.ioc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
@Controller
public class PersonalController {

    @Resource
    private PersonalService studentServiceImpl;

    @Autowired
    private PersonalService teacherServiceImpl;

    public void introduction() {
        studentServiceImpl.introduction();
        teacherServiceImpl.introduction();
    }

}
