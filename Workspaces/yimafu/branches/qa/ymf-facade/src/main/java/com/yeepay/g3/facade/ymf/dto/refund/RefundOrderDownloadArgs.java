package com.yeepay.g3.facade.ymf.dto.refund;

import com.yeepay.g3.facade.ymf.enumtype.sp.DownloadFileType;
import com.yeepay.g3.facade.ymf.validator.annotations.Max;
import com.yeepay.g3.facade.ymf.validator.annotations.NotEmpty;
import com.yeepay.g3.facade.ymf.validator.annotations.OneOf;

import java.io.Serializable;

/**
 * Title: 退款接口下载请求参数实体 不校验page
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 16/8/23.
 */
public class RefundOrderDownloadArgs extends RefundOrderArgs implements Serializable {

    private static final long serialVersionUID = -7501178701775867273L;
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
