package com.yeepay.g3.core.ymf;

import mybatis.generator.MyBatisGeneratorTool;

/**
 * Title: mybatis自动生成工具
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/11.
 */
public class MBG {

    private static final String PATH = "ymf-core/src/test/resources/mbg/";

    /**
     * mybatis generator main
     * @param args
     */
    public static void main(String args[]) {
        generate("ymfGeneratorConfig.xml");
    }

    private static void generate(String name) {
        MyBatisGeneratorTool.generate(PATH + name);
    }

}
