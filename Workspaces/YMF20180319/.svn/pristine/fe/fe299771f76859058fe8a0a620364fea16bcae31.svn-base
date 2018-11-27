package com.yeepay.g3.core.ymf.utils.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;



/**
 * 发送邮件的工具类
 * 指定邮件服务器，密码，发送地址，发送内容等信息，就可以发送邮件了
 * @author junning.li
 *
 */
public class EmailUtils {
	/**
	 *
	 * @param protocol 邮件协议，默认填入“smtp”
	 * @param host 邮件服务器
	 * @param username 如果需要认证（一般情况下必须），则为认证用户名
	 * @param password 同上，密码
	 * @param props 具体的发送参数,默认包含：
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.timeout", "25000");
	 * @param contentType 邮件头的contentType定义，类似于HTML同一元素
	 * @param content 邮件内容
	 * @param subject 邮件标题
	 * @param from 发件人
	 * @param to 收件人
	 * @param cc 抄送
	 * @param bcc 密送
	 * @param sentDate 发送时间
	 * @throws MessagingException
	 */
	public static void send(String protocol, String host, String username,
							String password, Properties props, String contentType,
							Object content, String subject, String[] from, String[] to,
							String[] cc, String[] bcc, Date sentDate) throws MessagingException {
		if (from == null || from.length < 1) {
			throw new IllegalArgumentException("no send from specified");
		}
		if (to == null || to.length < 1) {
			throw new IllegalArgumentException("no send to specified");
		}

		Session sendMailSession = Session.getInstance(props);
		Message message = new MimeMessage(sendMailSession);
		message.setSubject(subject);
		InternetAddress[] fromAddr = new InternetAddress[from.length];
		for (int i = 0; i < from.length; i++) {
			fromAddr[i] = new InternetAddress(from[i]);
		}
		message.addFrom(fromAddr);

		for (int i = 0; i < to.length; i++) {
			message.addRecipient(RecipientType.TO, new InternetAddress(to[i]));
		}
		if (cc != null && cc.length > 0) {
			for (int i = 0; i < cc.length; i++) {
				message.addRecipient(RecipientType.CC, new InternetAddress(
						cc[i]));
			}
		}
		if (bcc != null && bcc.length > 0) {
			for (int i = 0; i < bcc.length; i++) {
				message.addRecipient(RecipientType.BCC, new InternetAddress(
						bcc[i]));
			}
		}
		message.setSentDate(sentDate);
		message.setContent(content, "text/html; charset=GBK");
		Transport transport = sendMailSession.getTransport(protocol);
		transport.connect(host, username, password);
		transport.sendMessage(message, message.getAllRecipients());
	}

	public static void send(String host, String username, String password, String[] from, String contentType, String content,
							String subject, String[] to, String[] cc, String[] bcc, Date date,
							Object[] args) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", "25000");
		if(args!=null && args.length>0){
			content = MessageFormat.format(content, args);
		}
		if(from == null){
			from = new String[] { username };
		}
		send("smtp", host, username, password, props, contentType,content,
				subject, from, to, cc, bcc, date);
	}

	/**
	 * add by jun.yu
	 * @title 通过邮件发送附件
	 * @param filePath 文件路径
	 * @param fileName 文件名
	 * @param to 收件人
	 * @param title 主题
	 * @param content 内容
	 * @throws EmailException
	 */
	public static void sendFile(String filePath,String fileName,String to,String title,String content) throws EmailException{
//      创建一个Email附件
		EmailAttachment emailattachment = new EmailAttachment();
		emailattachment.setPath(filePath);
		emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
		emailattachment.setName(fileName);

//      创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		multipartemail.setHostName("smtp.yeepay.com");
		multipartemail.addTo(to);
		multipartemail.setFrom("support@yeepay.com", "support");
		multipartemail.setAuthentication("support@yeepay.com", "Z8j1c3");
		multipartemail.setSubject(title);
		multipartemail.setMsg(content);

		//添加附件
		multipartemail.attach(emailattachment);
		//发送邮件
		multipartemail.send();
	}

	public static void main(String[] args) throws Exception {



		//sendEmail("support@yeepay.com", "rui.zeng@yeepay.com", "POS订单同步异常", "测试内容", null);
		//send("smtp.yeepay.com", "support@yeepay.com", "Z8j1c3", new String[] {"zixiong.hu@yeepay.com"}, "text/html; charset=GBK", "斯蒂芬斯蒂芬", "开通子商户申请", new String[] {"zixiong.hu@yeepay.com"}, null, null, new Date(), new Object[] {"1", "2"});
//  System.out.println("ok");
//
//  sendFile("d:\\1.txt", "xiao.txt", "haiyang.hu@yeepay.com", "发送附件主题", "发送附件内容");

  /*String toAddress1="xin.jin@yeepay.com,lei-zhao@yeepay.com";
  String toCcAddress1="haiyang.hu@yeepay.com,xin.jin@yeepay.com";
  String fromAddress="haiyang.hu@yeepay.com";
  String uthUsername="support@yeepay.com";
  String authPassword="Z8j1c3";
  String toAddress [] = toAddress1.split(",");
  String toCcAddress [] = toCcAddress1.split(",");
  send("d:\\1.txt","xiao.txt",toAddress,toCcAddress,fromAddress,uthUsername,authPassword,"测试","请查看");*/
//  String toAddress1="huawei.zhang@yeepay.com";
////  String toCcAddress1="";
//  String fromAddress="support@yeepay.com";
//  String uthUsername="support@yeepay.com";
//  String authPassword="Z8j1c3";
//
////  sendmail(toAddress1,toCcAddress1,fromAddress,uthUsername,authPassword,"测试","aa");
//  String[] toCcAddress1 = new String[]{"rong.zhu@yeepay.com","rui.zeng@yeepay.com"};
//
//  sendEmail(toAddress1,toCcAddress1,fromAddress,uthUsername,authPassword,"测试","aa");

	}

	public static void send(String filePath,String attachName,String toAddress[],String toCcAddress[],
							String fromAddress,String authUsername,String authPassword,String subject,String msg) throws EmailException
	{
		//      创建一个Email附件
		EmailAttachment emailattachment = new EmailAttachment();
		emailattachment.setPath(filePath);
		emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
		emailattachment.setName(attachName);
		//      创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		multipartemail.setHostName("smtp.yeepay.com");
		for(int i=0;i<toAddress.length;i++){
			multipartemail.addTo(toAddress[i],toAddress[i]);
		}
		for(int j=0;j<toCcAddress.length;j++){
			multipartemail.addCc(toCcAddress[j], toCcAddress[j]);
		}
		multipartemail.setFrom(fromAddress);
		multipartemail.setAuthentication(authUsername, authPassword);
		multipartemail.setSubject(subject);
		multipartemail.setMsg(msg);
		//添加附件
		multipartemail.attach(emailattachment);
		//发送邮件
		multipartemail.send();
	}

	public static void sendEmail(String toAddress,String[] toCcAddress,
								 String fromAddress,String authUsername,String authPassword,String subject,String msg) throws EmailException{
//      创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		multipartemail.setHostName("smtp.yeepay.com");
		multipartemail.addTo(toAddress);
		if(toCcAddress!=null)
			for(int i=0;i<toCcAddress.length;i++){
				multipartemail.addCc(toCcAddress[i]);
			}
//     multipartemail.addCc(toCcAddress);
		multipartemail.setFrom(fromAddress);
		multipartemail.setAuthentication(authUsername, authPassword);
		multipartemail.setSubject(subject);
		multipartemail.setMsg(msg);
		//发送邮件
		multipartemail.send();
	}

	public static void sendmail(String toAddress,String toCcAddress,
								String fromAddress,String authUsername,String authPassword,String subject,String msg) throws EmailException
	{
		//      创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		multipartemail.setHostName("smtp.yeepay.com");
		multipartemail.addTo(toAddress);
		if(!toCcAddress.equals(""))
			multipartemail.addCc(toCcAddress);
		multipartemail.setFrom(fromAddress);
		multipartemail.setAuthentication(authUsername, authPassword);
		multipartemail.setSubject(subject);
		multipartemail.setMsg(msg);
		//发送邮件
		multipartemail.send();
	}

	/**
	 * 给指定邮箱发送邮件，发送邮箱是support@yeepay.com
	 * @author hongjian.shi
	 * @Title: sendEmail
	 * @Description: TODO
	 * @param @param toAddress
	 * @param @param subject
	 * @param @param msg
	 * @param @throws EmailException -
	 * @return void <return desc>
	 * @throws <throws type>
	 * Create at:   2013年12月16日 下午1:49:11
	 */
	public static void sendEmail(String[] toAddress,String subject,String msg) throws EmailException{
//		String fromAddress = "support@yeepay.com";
//		String authUsername = "support@yeepay.com";
//		String authPassword = "Z8j1c3";
		String authUsername = "yeepaysupport@yeepay.com";
		String authPassword = "CMBJXccwzy_!@#$)(*&";
		String fromAddress = "yeepaysupport@yeepay.com";
		//      创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		multipartemail.setHostName("smtp.yeepay.com");
		for (String address : toAddress) {
			multipartemail.addTo(address);
		}
		multipartemail.setFrom(fromAddress);
		multipartemail.setAuthentication(authUsername, authPassword);
		multipartemail.setSubject(subject);
		multipartemail.setMsg(msg);
		//发送邮件
		multipartemail.send();
	}
 /*public static void sendEmail(String from, String to, String title, String content, Object[] params) throws MessagingException {
     send("smtp.yeepay.com", "support@yeepay.com", "Z8j1c3",
                new String[] { from }, "text/html; charset=GBK", content,
                title, new String[] { to,"zixiong.hu@yeepay.com" },  new String[] { "zixiong.hu@yeepay.com","xin.jin@yeepay.com" },null, new Date(), params);
 }*/
}

