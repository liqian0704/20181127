package com.yiibai.tutorial.spring;

import com.yiibai.tutorial.spring.helloworld.HelloWordService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.jvm.hotspot.HelloWorld;

/**
 * Created by yp-tc-2646 on 17/11/14.
 */
public class HelloProgram {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        HelloWordService service =
                (HelloWordService) context.getBean("helloWorldService");

        HelloWorld hw= service.getHelloword();

        hw.sayHello();
    }
}
