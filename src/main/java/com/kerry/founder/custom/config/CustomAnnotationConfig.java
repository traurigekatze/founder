package com.kerry.founder.custom.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
@Configuration
@ComponentScan("com.kerry.founder.custom")
@EnableAspectJAutoProxy
public class CustomAnnotationConfig {
}
