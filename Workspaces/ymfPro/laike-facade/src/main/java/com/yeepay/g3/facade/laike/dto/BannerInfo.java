package com.yeepay.g3.facade.laike.dto;

import java.io.Serializable;

/**
 * Description:
 * Author: wei.li
 * Date: 17/5/24
 * Version: 1.0
 * Copyright © 2016 YeePay.com All rights reserved.
 */
public class BannerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String homeAdImg;

    private String homoAdLink;

    public String getHomeAdImg() {
        return homeAdImg;
    }

    public void setHomeAdImg(String homeAdImg) {
        this.homeAdImg = homeAdImg;
    }

    public String getHomoAdLink() {
        return homoAdLink;
    }

    public void setHomoAdLink(String homoAdLink) {
        this.homoAdLink = homoAdLink;
    }
}
