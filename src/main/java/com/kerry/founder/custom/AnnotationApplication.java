package com.kerry.founder.custom;

import com.kerry.founder.custom.config.CustomAnnotationConfig;
import com.kerry.founder.custom.dao.User;
import com.kerry.founder.custom.service.UserSerivce;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author kerryhe
 * @date 2020/4/27
 */
public class AnnotationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CustomAnnotationConfig.class);
        UserSerivce userSerivce = context.getBean(UserSerivce.class);
        userSerivce.info(new User());
    }

}
