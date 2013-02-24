package org.performance.web.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AC {

    public static ApplicationContext ac = new ClassPathXmlApplicationContext("/applicationContext.xml");

    public static <T> T getBean(Class<T> clazz) {
        return ac.getBean(clazz);
    }

}
