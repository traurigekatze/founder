package com.kerry.founder.framework.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
@Configuration
public class JavaConfig {
    @Bean
    public JavaBean javaBean() {
        return new JavaBean();
    }
}
