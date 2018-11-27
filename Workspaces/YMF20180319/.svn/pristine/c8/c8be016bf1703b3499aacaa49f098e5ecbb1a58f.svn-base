package com.yeepay.g3.core.ymf.utils;

/**
 * Created by dongxulu on 17/2/20.
 */
public class TestThread implements Runnable  {
    @Override
    public void run() {
        System.out.println(getAcount() +"1号 i=1  thread id:"+Thread.currentThread().getId()+" name:"+Thread.currentThread().getName());
    }

    private int getAcount(){
        int i=1;
        return TestStatic.count(i);
    }
}
