package com.yeepay.g3.core.ymf.utils;

import java.util.Stack;

/**
 * @Description: 62进制转换
 * @Author: xiaobin.liu
 * @Date: 17/3/22
 * @Time: 下午2:56
 */
public class Base62 {
    private static String digths = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 将long转为62进制,最大支持数值8999999999999989l
     * @param id
     * @return
     */
    public static String _10_62(long id) {
        StringBuffer str = new StringBuffer("");
        Stack<Character> s = new Stack<Character>();
        long num = id;
        while (num != 0) {
            s.push(digths.charAt((int) (num % 62)));
            num /= 62;
        }
        while (!s.isEmpty()) {
            str.append(s.pop());
        }
        return str.toString();
    }

    /**
     * 将64位字符转为10进制
     * @param c
     * @return
     */
    public static String _62_10(String c) {
        if(c==null||c.isEmpty()){return "-1";}
        if(!c.matches("[0-9a-zA-Z]+")){return "-1";}
        char[] charArr2 = c.toCharArray();
        long l = 0;
        for (int i = 0; i < charArr2.length; i++) {
            l += digths.indexOf(charArr2[i]) * (Math.pow(62, (charArr2.length - i - 1)));
        }
        return String.valueOf(l);
    }

    public static void main(String[] args) {
        long a = 8999999999999989l;//最大值
        System.out.println("input:"+a);
        System.out.println("62:" +_10_62(a));
        System.out.println("10:" +_62_10(_10_62(a)));
    }

}
