package com.yeepay.g3.facade.laike.dto;

import com.yeepay.g3.facade.laike.enumtype.MsgTypeEnum;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: 消息对象
 * Author: jiawen.huang
 * Date: 16/11/18
 * Time: 14:45
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class MsgDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 消息编号
	 */
	private String msgNo;

	/**
	 * 极光ID
	 */
	private String jpushID;

	/**
	 * 消息类型
	 */
	private MsgTypeEnum type;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 发布时间
	 */
	private String pushTime;

	/**
	 * 生效时间
	 */
	private String lifeStart;

	/**
	 * 失效时间
	 */
	private String lifeEnd;

	/**
	 * 多媒体链接
	 */
	private String url1;

	/**
	 * 多媒体链接
	 */
	private String url2;

	public String getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(String msgNo) {
		this.msgNo = msgNo;
	}

	public String getJpushID() {
		return jpushID;
	}

	public void setJpushID(String jpushID) {
		this.jpushID = jpushID;
	}

	public MsgTypeEnum getType() {
		return type;
	}

	public void setType(MsgTypeEnum type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPushTime() {

		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public void setPushTime(Date pushTime) {
		this.lifeEnd = new SimpleDateFormat("yyyyMMdd HH:mm:SS.SSS").format(pushTime);
	}

	public String getLifeStart() {
		return lifeStart;
	}

	public void setLifeStart(String lifeStart) {
		this.lifeStart = lifeStart;
	}

	public void setLifeStart(Date lifeStart) {
		this.lifeStart = new SimpleDateFormat("yyyyMMdd").format(lifeStart);
	}

	public String getLifeEnd() {
		return lifeEnd;
	}

	public void setLifeEnd(String lifeEnd) {
		this.lifeEnd = lifeEnd;
	}

	public void setLifeEnd(Date lifeEnd) {
		this.lifeEnd = new SimpleDateFormat("yyyyMMdd").format(lifeEnd);
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}
}

