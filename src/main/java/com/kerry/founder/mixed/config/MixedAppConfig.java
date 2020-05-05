package com.kerry.founder.mixed.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/5
 * **********学海无涯苦作舟**********
 */
@Configuration
@ComponentScan("com.kerry.founder.mixed.service")
@ImportResource(value = "spring-mixed.xml")
public class MixedAppConfig {
}
