package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.utils.common.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/27.
 */
public class ArrayTest {

    @Test
    public void testSearch() {
        String[] array = {"startDate", "endDate"};
        String key = "startDate";
        String key2 = "status";
        System.out.println(ArrayUtils.contains(array, key));
        System.out.println(Arrays.binarySearch(array, key2));

        searchArgs("startDate", "endDate");
    }

    private void searchArgs(String...args) {
        System.out.println(Arrays.binarySearch(args, "startDate"));
        System.out.println(Arrays.binarySearch(args, "status"));
    }

    @Test
    public void testReg() {
        String reg = "([1-9]\\d*)|([1-9])";
        Pattern pattern = Pattern.compile(reg);
    }
}
