package com.kerry.founder.mixed;

import com.kerry.founder.mixed.config.MixedAppConfig;
import com.kerry.founder.mixed.service.AnotherService;
import com.kerry.founder.mixed.service.MixedUserService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
public class MixedApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MixedAppConfig.class);
        context.refresh();
        MixedUserService userService = context.getBean("mixedUserService", MixedUserService.class);
        userService.info(RandomUtils.nextInt(1, 999));

        MixedUserService anotherService = (MixedUserService) context.getBean("anotherService");
        anotherService.introduction();
    }

}
