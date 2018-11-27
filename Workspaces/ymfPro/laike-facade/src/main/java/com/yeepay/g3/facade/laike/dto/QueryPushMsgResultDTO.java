package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhaoyu.cui
 * @Date: 16/11/10
 * @Time: 下午2:57
 */
public class QueryPushMsgResultDTO implements Serializable {

    private String messageNo;

    private String title;

    private String content;

    private String pushTime;

    public String getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(String messageNo) {
        this.messageNo = messageNo;
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
}
