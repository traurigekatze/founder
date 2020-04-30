package com.kerry.founder.framework.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author kerryhe
 * @date 2020/4/27
 */
public class XmlApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        XmlBean xmlBean = context.getBean("xmlBean", XmlBean.class);
        xmlBean.say();
    }

}
