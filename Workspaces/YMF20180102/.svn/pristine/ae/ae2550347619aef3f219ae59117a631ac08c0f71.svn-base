package com.yeepay.g3.core.ymf.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/7/7
 * @Time: 上午9:54
 */
public class ValidateParamUtil {

    /**
     * 检查是否符合ip规则
     *
     * @param ip
     * @return
     */
    public static boolean isIp(String ip) {
        if (null != ip) {
            String ipReg = "(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])";
            Pattern pattern = Pattern.compile(ipReg);
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 手机号验证
     * @author ：shijing
     * 2016年12月5日下午4:34:46
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     * @author ：shijing
     * 2016年12月5日下午4:34:21
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public static void main(String args[]) {
        boolean ip = isIp("10.149.154.114");
        System.out.println(ip);

        String phone = "13900442200";
        String phone2 = "021-88889999";
        String phone3 = "88889999";
        String phone4 = "1111111111";
        //测试1
        if(isPhone(phone) || isMobile(phone)){
            System.out.println("1这是符合的");
        }
        //测试2
        if(isPhone(phone2) || isMobile(phone2)){
            System.out.println("2这是符合的");
        }
        //测试3
        if(isPhone(phone3) || isMobile(phone3)){
            System.out.println("3这是符合的");
        }
        //测试4
        if(isPhone(phone4) || isMobile(phone4)){
            System.out.println("4这是符合的");
        }else{
            System.out.println("不符合");
        }
    }
}