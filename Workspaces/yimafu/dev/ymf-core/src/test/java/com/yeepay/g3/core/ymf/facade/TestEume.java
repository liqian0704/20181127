package com.yeepay.g3.core.ymf.facade;

import com.yeepay.g3.facade.ymf.enumtype.trade.TrxCode;

/**
 * Created by dongxulu on 16/11/22.
 */
public class TestEume {

    public static void main(String args[]){

        System.out.println(TrxCode.T1000.getMsg());

        String[] a ={"123","11","22"};
        for(String i:a){
            System.out.println(i);
        }
    }
}
