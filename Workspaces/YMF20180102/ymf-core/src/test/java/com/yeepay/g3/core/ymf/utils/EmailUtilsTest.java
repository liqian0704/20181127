package com.yeepay.g3.core.ymf.utils;

import com.yeepay.g3.core.ymf.utils.email.EmailUtils;
import com.yeepay.g3.core.ymf.utils.email.MonitorNotify;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

/**
 * @Description:
 * @Author: xiaobin.liu
 * @Date: 17/8/23
 * @Time: 上午11:49
 */
public class EmailUtilsTest {

    @Test
    public void testSend() {
        String toAddress = "xiaobin.liu@yeepay.com";
        String fromAddress = "yeepaysupport@yeepay.com";
        String uthUsername = "yeepaysupport@yeepay.com";
        String authPassword = "CMBJXccwzy_!@#$)(*&";

        //抄送内容
//        String[] toCcAddress = {"yuanyuan.zhang-1@yeepay.com",
//                "rong.zhu@yeepay.com", "jiwei.lv@yeepay.com"};

        String title = "异常通知邮件。";
        String content = "异常内容：法兰克福计算机课李丹凤是冷的附近收款单";
        try {
            EmailUtils.sendEmail(toAddress, null, fromAddress, uthUsername, authPassword, title, content);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSend2() {
        String[] toAddress = {"xiaobin.liu@yeepay.com"};

        String title = "异常通知邮件。测试用的";
        String content = "异常内容：法兰克福计算机课李丹凤是冷的附近收款单：" + new NullPointerException();
        try {
            EmailUtils.sendEmail(toAddress, title, content);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSend3() {
        //        notifyEmail("nihao", new NullPointerException());
        MonitorNotify.notifyEmail("nihao", null);
    }



}
