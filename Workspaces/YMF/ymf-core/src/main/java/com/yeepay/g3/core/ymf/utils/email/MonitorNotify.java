package com.yeepay.g3.core.ymf.utils.email;

import com.yeepay.g3.core.ymf.constants.CfgConstant;
import com.yeepay.g3.core.ymf.utils.dateutils.DateUtil;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.mail.EmailException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.util.Date;

/**
 * @Description: 监控通知
 * @Author: xiaobin.liu
 * @Date: 17/8/23
 * @Time: 下午2:12
 */
public class MonitorNotify {
    private static Logger logger = LoggerFactory.getLogger(MonitorNotify.class);

    private static final String authUsername = "yeepaysupport@yeepay.com";
    private static final String authPassword = "CMBJXccwzy_!@#$)(*&";
    private static final String fromAddress = "yeepaysupport@yeepay.com";

    private static final String title = "易码付系统通知";

    public static String HOST_INFO = "";

    static {
        HOST_INFO = hostInfo();
        System.out.print(HOST_INFO);
    }

    /**
     * 邮件通知
     *
     * @param description 描述信息
     * @param throwable   异常信息
     */
    public static void notifyEmail(String description, Throwable throwable) {
        boolean isOpen = CfgConstant.YMF_NOTITY_EMAILS_SWITCH();
        if (!isOpen) {
            return;
        }
        String[] toAddress = CfgConstant.YMF_NOTITY_EMAILS();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("描述：").append(description).append("\n");

        stringBuilder.append("时间:").append(DateUtil.formatDate(new Date())).append("\n");
        stringBuilder.append(HOST_INFO);

        stringBuilder.append(exceptionToString(throwable));
        try {
            EmailUtils.sendEmail(toAddress, title, stringBuilder.toString());
            logger.info("Send email success.");
        } catch (EmailException e) {
            logger.error("邮件发送异常：",e);
        }
    }

    /**
     * 转换异常栈信息
     *
     * @param throwable
     */
    private static String exceptionToString(Throwable throwable) {
        if (throwable != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            try {
                StringBuilder stringBuilder = new StringBuilder();
                throwable.printStackTrace(printWriter);
                String expMessage = stringWriter.toString();
                stringBuilder.append("异常信息:\n").append(expMessage);
                return stringBuilder.toString();
            } finally {
                try {
                    stringWriter.close();

                } catch (IOException e) {
                    logger.error("关闭流异常，可以忽略：",e);
                }
                printWriter.close();
            }
        }
        return "";
    }

    /**
     * 获取本机ip信息
     */
    private static String hostInfo() {
        InetAddress ia = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            ia = ia.getLocalHost();

            String localname = ia.getHostName();
            String localip = ia.getHostAddress();
            stringBuilder.append("主机名称是：").append(localname).append("\n");
            stringBuilder.append("主机的ip是:").append(localip).append("\n");
        } catch (Exception e) {
            logger.error("获取本机ip失败(忽略)",e);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        notifyEmail("nihao", new NullPointerException());
        MonitorNotify.notifyEmail("nihao", null);
    }

}
