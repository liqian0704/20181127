package com.yeepay.g3.facade.ymf.dto.order;

import com.yeepay.g3.facade.ymf.enumtype.sp.DownloadFileType;
import com.yeepay.g3.facade.ymf.validator.annotations.Max;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

import java.io.Serializable;

/**
 * 订单下载请求参数类
 * 注解支持校验
 */
public class OrderDownloadArgs extends OrderArgs implements Serializable {

    private static final long serialVersionUID = -7550841955485597284L;

    @NotEmpty("下载文件类型")
    @OneOf(value = "下载文件类型", type = DownloadFileType.class)
    private String fileType;

    @Max(name = "下载文件最大数量", value = 100000)
    private int maxCount = 100;

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}