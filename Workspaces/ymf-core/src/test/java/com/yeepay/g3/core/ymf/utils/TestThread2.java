package com.yeepay.g3.core.ymf.utils;

/**
 * Created by dongxulu on 17/2/20.
 */
public class TestThread2 implements Runnable  {
    @Override
    public void run() {
        System.out.println(getAcount() +" 2号 i=2 thread id:"+Thread.currentThread().getId()+" name:"+Thread.currentThread().getName());
    }

    private int getAcount(){
        int i=2;
        return TestStatic.count(i);
    }
}
