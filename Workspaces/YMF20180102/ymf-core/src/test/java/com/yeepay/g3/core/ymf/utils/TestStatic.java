package com.yeepay.g3.core.ymf.utils;

/**
 * Created by dongxulu on 17/2/20.
 */
public class TestStatic {


    public static  void main(String args[]){

        for(int i=0;i<50;i++){
           new Thread(new TestThread()).start();
           new Thread(new TestThread2()).start();
        }

    }
    public void add(){

    }

    static int count(int i){
        if(i%2==0){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int j=i+1;
        return j;
    }

}
