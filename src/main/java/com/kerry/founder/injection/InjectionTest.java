package com.kerry.founder.injection;

import com.kerry.founder.injection.dao.InjectionDao1;
import com.kerry.founder.injection.factory.InjectionFactory;
import com.kerry.founder.injection.service.InjectionService;
import com.kerry.founder.injection.service.impl.InjectionServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * **********书山有路勤为径**********
 *
 * @author k1rry
 * @date 2020/5/16
 * **********学海无涯苦作舟**********
 */
@Slf4j
public class InjectionTest {

    private String a;

    private Integer b;

    private InjectionService injectionService;

    public InjectionTest(Integer b, String a) {
        this.a = a;
        this.b = b;
    }

    public InjectionTest(String a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
//        Field[] fields = InjectionTest.class.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getType().getName());
//        }
//        System.out.println(InjectionTest.class.getInterfaces().length);
//        Class<InjectionServiceImpl> clazz = InjectionServiceImpl.class;
//        System.out.println(clazz.getInterfaces().length > 0 ? clazz.getInterfaces()[0].getName() : clazz.getName());
//
//        if (1 == 1) {
//            return;
//        }

//        Constructor<?>[] constructors = InjectionTest.class.getConstructors();
//        Object s = "abc";
//        System.out.println(s.getClass().getName());
//        for (Constructor<?> constructor : constructors) {
//            Parameter[] parameters = constructor.getParameters();
//            for (int i = 0; i < parameters.length; i++) {
//                Parameter parameter = parameters[i];
//                System.out.println(i + "--name:"+parameter.getName());
//                System.out.println(i + "--type:"+parameter.getType());
//                System.out.println(s.getClass().getName().equals(parameter.getType().getName()));
//            }
//        }
//

        InjectionFactory factory = new InjectionFactory("spring-injection.xml");

        InjectionServiceImpl injectionService = factory.getBean("injectionService", InjectionServiceImpl.class);
        injectionService.query();
    }

}
