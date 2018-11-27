package com.yiibai.tutorial.spring.helloworld;

/**
 * Created by yp-tc-2646 on 17/11/14.
 */
public class HelloWordService {

    private HelloWorld helloword;

    public HelloWordService() {

    }

    public HelloWorld getHelloword() {
        return helloword;
    }

    public void setHelloword(HelloWorld helloword) {
        this.helloword = helloword;
    }
}
